package com.argo.wx.web.weixin;

import com.argo.db.exception.EntityNotFoundException;
import com.argo.security.UserIdentity;
import com.argo.security.exception.PasswordInvalidException;
import com.argo.security.exception.UnauthorizedException;
import com.argo.security.service.AuthorizationService;
import com.argo.service.ServiceException;
import com.argo.web.JsonResponse;
import com.argo.wx.model.WxMenu;
import com.argo.wx.model.WxUser;
import com.argo.wx.service.WxApiClient;
import com.argo.wx.service.WxMenuService;
import com.argo.wx.service.WxUserService;
import com.argo.wx.token.WxAccessToken;
import com.argo.wx.token.WxJsSignToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://jingyan.baidu.com/article/48206aeae7aa24216ad6b3f3.html
 * Created by $User on 2014-12-05 09:32.
 */

@Controller
@RequestMapping("/wx")
public class WxViewController extends WxBaseController {

    @Override
    public boolean needLogin() {
        return false;
    }

    @Autowired
    private WxMenuService wxMenuService;

    @Autowired
    private WxApiClient wxApiClient;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private AuthorizationService authorizationService;


    @RequestMapping(value="to/{id}")
    public ModelAndView to(ModelAndView modelAndView, @PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        try {
            WxMenu wxMenu = this.wxMenuService.find(null, id);
            String url = this.wxApiClient.formatOAuthViewUrl(wxMenu.getPageUrl());
            logger.info("to: " + url);
            modelAndView.setView(new RedirectView(url));
        } catch (EntityNotFoundException e) {
            modelAndView.setViewName("/wx/index");
        }
        return modelAndView;
    }

    @RequestMapping(value="view")
    public ModelAndView view(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcd983566d32442bc&redirect_uri=http://192.168.1.1/weixin/weixin.do?action=viewtest&response_type=code&scope=snsapi_base&state=1#wechat_redirect
        String code = request.getParameter("code");
        String to = request.getParameter("_to_");
        if (!StringUtils.isBlank(code)){
            WxAccessToken wxAccessToken = wxApiClient.getOAuth2AccessToken(code);
            logger.info("code={}, to={}, accessToken={}", code, to, wxAccessToken);
            if (wxAccessToken != null){
                WxUser user = wxUserService.findByWxId(wxAccessToken.getOpenId());
                if (user != null){
                    this.rememberUser(request, response, user.getUserId());
                    modelAndView.addObject("currentUser", user);
                    if (to != null) {
                        modelAndView.setView(new RedirectView(to));
                    }else{
                        modelAndView.setViewName("/wx/index");
                    }
                }else{
                    try {
                        UserIdentity user0 = this.getCurrentUser();
                        if (null != user0 && user0.getIdentityId() > 0){
                            try {
                                this.postBind(user0, wxAccessToken);
                                if (to != null) {
                                    modelAndView.setView(new RedirectView(to));
                                }else{
                                    modelAndView.setViewName("/wx/index");
                                }
                            } catch (ServiceException e) {
                                logger.error(e.getMessage(), e);
                                modelAndView.setViewName("/wx/index");
                            }
                        }
                    } catch (UnauthorizedException e) {

                    }
                    String url = "/m/wx/view";
                    if (null != request.getQueryString()){
                        url = url + "?" + request.getQueryString();
                    }
                    WxJsSignToken jsSignToken = new WxJsSignToken(url, wxApiClient.getAccessToken().getJsapiTicket());
                    logger.info("WxJsSignToken: {}", jsSignToken);
                    modelAndView.addObject("ticket", jsSignToken);
                    modelAndView.addObject("page_title", "会员绑定");
                    modelAndView.addObject("wxAccessToken", wxAccessToken);
                    modelAndView.addObject("to", to);
                    modelAndView.setViewName("/wx/bind");
                }
            }else {
                modelAndView.setViewName("/wx/index");
            }
        }else{
            if (to != null) {
                modelAndView.setView(new RedirectView(to));
            }else{
                modelAndView.setViewName("/wx/index");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value="bind", method= RequestMethod.POST)
    @ResponseBody
    public JsonResponse bind(ModelAndView modelAndView,
                             @RequestParam(value = "name", defaultValue = "") String uname,
                             @RequestParam(value = "passwd", defaultValue = "") String passwd,
                             @RequestParam(value="openid", defaultValue = "") String openid,
                             @RequestParam(value="opentoken", defaultValue = "") String opentoken,
                             @RequestParam(value="to", defaultValue = "") String to,
                             HttpServletRequest request, HttpServletResponse response,
                             JsonResponse actResponse){

        UserIdentity item = null;
        try {
            item = authorizationService.verifyUserPassword(uname, passwd);
        } catch (PasswordInvalidException e) {
            logger.error(e.getMessage(), e);
            actResponse.setCode(e.getStatusCode());
            return actResponse;
        }

        WxAccessToken wxAccessToken = new WxAccessToken();
        wxAccessToken.setOpenId(openid);
        wxAccessToken.setToken(opentoken);

        //写登录Cookie
        this.rememberUser(request, response, item.getIdentityId());
        request.setAttribute("currentUser", item);

        try {
            postBind(item, wxAccessToken);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            actResponse.setCode(e.getErrorCode());
        }

        return actResponse;
    }

    private void postBind(UserIdentity item, WxAccessToken wxAccessToken) throws ServiceException {
        boolean flag = this.wxUserService.bindUser(item, wxAccessToken);
        logger.info("WxBind Result={}, openid={}, token={}", flag, wxAccessToken.getOpenId(), wxAccessToken.getToken());
    }

}
