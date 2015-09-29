package com.argo.wx.web.weixin;

import com.argo.wx.WxConfig;
import com.argo.wx.handler.WxHandler;
import com.argo.wx.handler.WxHandlerFactory;
import com.argo.wx.message.WxMessage;
import com.argo.wx.message.WxMessageBuilder;
import com.argo.wx.response.WxResponse;
import com.argo.wx.service.WxMenuService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * http://mp.weixin.qq.com/wiki/17/2d4265491f12608cd170a95559800f2d.html
 * Created by $User on 2014-12-05 09:32.
 */

@Controller
@RequestMapping("/wx")
public class WxConnectController extends WxBaseController {

    @Autowired
    private WxHandlerFactory handlerFactory;

    @Override
    public boolean needLogin() {
        return false;
    }

    @Autowired
    private WxMenuService wxmenuService;

    @RequestMapping(value="connect")
    public void connect(HttpServletRequest request, HttpServletResponse response){
        String TOKEN = WxConfig.instance.getToken();//Token
        String signature = request.getParameter("signature");//SHA1加密字符串
        String timestamp = request.getParameter("timestamp");//时间
        String nonce = request.getParameter("nonce");//随机数
        String echoStr = request.getParameter("echostr");//随机字符串
        if (logger.isDebugEnabled()){
            logger.debug("signature:{}, timestamp:{}, nonce:{}, echoStr:{}", signature, timestamp, nonce, echoStr);
        }
        try {

            if(StringUtils.isNotBlank(echoStr)){
                String[] a = {TOKEN,timestamp,nonce};
                Arrays.sort(a);//数组排序
                String str = StringUtils.join(a, "");
                String echo = DigestUtils.sha1Hex(str);//SHA1加密
                logger.info("server sign:{}, wx-signature:{}", echo, signature);
                if(echo.equals(signature)){
                    response.getWriter().print(echoStr);
                }else{
                    response.getWriter().print("123");
                }
            }else{
                this.handlePostRequest(request, response);
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }finally {
            try {
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            InputStream is = request.getInputStream();
            WxMessage message = WxMessageBuilder.build(is);
            if (logger.isDebugEnabled()){
                logger.debug("Got wx message: {}", message);
            }
            if (message != null){
                WxHandler handler = handlerFactory.get(message);
                logger.info("Got wx message handler: {}", handler);
                if (handler != null){
                    WxResponse wxResponse = handler.execute(message);
                    if (wxResponse != null) {
                        XMLOutputter xmOut = new XMLOutputter();
                        Document xml = wxResponse.toXml();
                        xmOut.output(xml, response.getWriter());
                        if (logger.isDebugEnabled()){
                            logger.debug("Got wx response: {}", wxResponse);
                        }
                    }
                }
            }
        } catch (IOException e) {
            response.getWriter().print("500");
        } catch (JDOMException e) {
            response.getWriter().print("500");
        }
    }

}
