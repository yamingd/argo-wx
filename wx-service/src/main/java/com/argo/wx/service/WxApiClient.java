package com.argo.wx.service;

import com.argo.annotation.RmiService;
import com.argo.wx.token.WxAccessToken;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yamingd on 9/28/15.
 */
@RmiService
public interface WxApiClient {

    /**
     * 获取OAuth2 AccessToken
     * @param code 授权Code
     * @return WxAccessToken
     */
    WxAccessToken getOAuth2AccessToken(String code);

    /**
     * 格式化成OAuth2的访问URL
     * @param url 目标URL
     * @return String
     */
    String formatOAuthViewUrl(String url);

    /**
     * 格式化成普通的访问URL
     * @param url 目标URL
     * @return String
     */
    String formatViewUrl(String url);

    /**
     * 删除菜单
     * @return boolean
     */
    boolean deleteMenu();

    /**
     * 推送菜单到微信
     * @param menu 菜单
     * @return boolean
     */
    boolean pushMenu(Map menu);

    /**
     * 获取微信API访问令牌.
     * @throws java.io.IOException 抛出IOException
     */
    void loadToken() throws IOException;

    /**
     * 读取Wx的令牌
     * @return WxAccessToken
     */
    WxAccessToken getAccessToken();
}
