package com.argo.wx.service.impl;

import com.argo.util.json.JsonUtil;
import com.argo.wx.WxConfig;
import com.argo.wx.service.WxApiClient;
import com.argo.wx.token.WxAccessToken;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

/**
 * Created by Yaming on 2014/12/18.
 */
@Service
public class WxApiClientImpl implements WxApiClient, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    private String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    private String deleteMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
    private String oauth2AccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private String wxViewUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    private String wxJsTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    private WxAccessToken accessToken;
    private int wait = 0;
    private String appId;
    private String appSecret;
    private String domain;

    @Override
    public void afterPropertiesSet() throws Exception {
        WxConfig.load();
        this.domain = WxConfig.instance.getAppdomain();
        this.appId = WxConfig.instance.getAppId();
        this.appSecret = WxConfig.instance.getSecret();
        boolean enabled = WxConfig.instance.isEnabled();
        if (enabled) {
            accessTokenUrl = String.format(accessTokenUrl, this.appId, this.appSecret);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        loadToken();
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }

                    try {
                        Thread.sleep(wait * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public WxAccessToken getOAuth2AccessToken(String code){
        String url = String.format(this.oauth2AccessTokenUrl, this.appId, this.appSecret, code);
        try {
            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(url));
            Map map = consumeResponse(response);
            if (map.get("access_token") != null) {
                WxAccessToken token = new WxAccessToken();
                token.setToken(map.get("access_token") + "");
                Double d = (Double) map.get("expires_in");
                token.setExpireIn(d.intValue());
                token.setOpenId(map.get("openid") + "");
                return token;
            }else{
                logger.error("获取OAuth2 AccessToken错误. code={}", code);
                return null;
            }
        } catch (IOException e) {
            logger.error("获取OAuth2 AccessToken错误. code=" + code, e);
            return null;
        }
    }

    @Override
    public String formatOAuthViewUrl(String url){
        try {
            String jumpUrl = String.format("%s/m/com.argo.wx/view?_to_=%s", this.domain, java.net.URLEncoder.encode(url, "UTF-8"));
            String tmp = String.format(wxViewUrl, this.appId, java.net.URLEncoder.encode(jumpUrl, "UTF-8"));
            return tmp;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    public String formatViewUrl(String url){
        String tmp = String.format("%s%s", this.domain, url);
        return tmp;
    }

    @Override
    public boolean deleteMenu(){
        String url = String.format(this.deleteMenuUrl, this.accessToken.getToken());
        try {
            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(url));
            Map map = consumeResponse(response);
            String errmsg = map.get("errmsg") + "";
            if ("ok".equalsIgnoreCase(errmsg)){
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            logger.error("deleteMenu错误.", e);
            return true;
        }
    }

    @Override
    public boolean pushMenu(Map menu){
        if (this.accessToken == null){
            try {
                this.loadToken();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }
        String json = JsonUtil.toJson(menu);
        if (logger.isDebugEnabled()) {
            logger.debug("PUSH Menu to: {}", json);
        }
        String url = String.format(this.createMenuUrl, this.accessToken.getToken());
        HttpPost httpPost = new HttpPost(url);
        StringEntity s = new StringEntity(json, "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(s);
        try {
            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
            Map map = consumeResponse(response);
            String msg = map.get("errmsg") + "";
            if ("ok".equalsIgnoreCase(msg)){
                return true;
            }
            return false;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public synchronized void loadToken() throws IOException {
        CloseableHttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(accessTokenUrl));
        Map map = consumeResponse(response);
        if (map.get("access_token") != null){
            this.accessToken = new WxAccessToken();
            this.accessToken.setToken(map.get("access_token") + "");
            Double d = (Double)map.get("expires_in");
            this.accessToken.setExpireIn(d.intValue());
            logger.error("Access Token: " + this.accessToken);
            this.wait = 3600;

            response = HttpClientBuilder.create().build().execute(new HttpGet(String.format(wxJsTicketUrl, this.accessToken.getToken())));
            map = consumeResponse(response);
            if (map.get("ticket") != null){
                this.accessToken.setJsapiTicket(map.get("ticket") + "");
            }

        }else{
            this.wait = 600;
            logger.error("Access Token Error.");
        }
    }

    /**
     * 转换返回结果
     * @param response
     * @return
     */
    private Map consumeResponse(HttpResponse response) {
        HttpEntity entity = null;
        InputStream stream = null;
        try {
            entity = response.getEntity();
            stream = entity.getContent();
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        if (stream == null){
            return null;
        }

        StringBuffer document = new StringBuffer();
        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(stream, Consts.UTF_8));
            while ((line = reader.readLine()) != null) {
                document.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        String str =  document.toString();
        logger.info("HttpResponse: " + str);
        Map map = JsonUtil.asMap(str);
        return map;
    }

    @Override
    public WxAccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(WxAccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
