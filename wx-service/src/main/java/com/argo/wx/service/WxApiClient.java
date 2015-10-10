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
     * @param code
     * @return WxAccessToken
     */
    WxAccessToken getOAuth2AccessToken(String code);

    /**
     * 格式化成OAuth2的访问URL
     * @param url
     * @return String
     */
    String formatOAuthViewUrl(String url);

    /**
     * 格式化成普通的访问URL
     * @param url
     * @return
     */
    String formatViewUrl(String url);

    /**
     * 删除菜单
     * @return boolean
     */
    boolean deleteMenu();

    /**
     * 推送菜单到微信
     * @param menu
     * @return boolean
     * @throws java.io.IOException
     */
    boolean pushMenu(Map menu);

    /**
     * 获取微信API访问令牌.
     * @throws java.io.IOException
     */
    void loadToken() throws IOException;

    /**
     * 读取Wx的令牌
     * @return
     */
    WxAccessToken getAccessToken();
}
