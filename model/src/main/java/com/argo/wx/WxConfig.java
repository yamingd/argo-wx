package com.argo.wx;

import com.argo.yaml.YamlTemplate;

import java.io.IOException;

/**
 * Created by yamingd on 9/28/15.
 */
public class WxConfig {

    /**
     *
     */
    private String appdomain;
    /**
     *
     */
    private String token;
    /**
     *
     */
    private String appId;
    /**
     *
     */
    private String secret;
    /**
     *
     */
    private boolean enabled;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAppdomain() {
        return appdomain;
    }

    public void setAppdomain(String appdomain) {
        this.appdomain = appdomain;
    }

    private static final String confName = "wx.yaml";

    public static WxConfig instance = null;

    /**
     * 加载配置信息
     * @throws IOException
     */
    public synchronized static void load() throws IOException {
        if (instance != null){
            return;
        }

        try {
            WxConfig.instance = load(confName);
        } catch (IOException e) {
            WxConfig.instance = new WxConfig();
            throw e;
        }
    }

    /**
     * 加载配置信息
     * @throws IOException
     */
    public static WxConfig load(String confName) throws IOException {
        WxConfig config = YamlTemplate.load(WxConfig.class, confName);
        return config;
    }
}
