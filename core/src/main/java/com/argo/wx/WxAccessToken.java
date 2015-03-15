package com.argo.wx;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxAccessToken {

    private String token;
    private Integer expireIn;
    private String openId;
    private String scope;

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

    @Override
    public String toString() {
        return "WxAccessToken{" +
                "token='" + token + '\'' +
                ", expireIn=" + expireIn +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
