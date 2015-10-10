package com.argo.wx.token;

import org.msgpack.annotation.MessagePackMessage;

/**
 * Created by Yaming on 2014/12/18.
 */
@MessagePackMessage
public class WxAccessToken {

    private String token;
    private Integer expireIn;
    private String openId;
    private String scope;
    private String jsapiTicket;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    @Override
    public String toString() {
        return "WxAccessToken{" +
                "token='" + token + '\'' +
                ", expireIn=" + expireIn +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", jsapiTicket='" + jsapiTicket + '\'' +
                '}';
    }
}
