package com.argo.wx.service;

import com.argo.annotation.RmiService;
import com.argo.security.UserIdentity;
import com.argo.service.ServiceException;
import com.argo.wx.model.WxUser;
import com.argo.wx.token.WxAccessToken;


/**
 * Created by user on 3/15/15.
 */
@RmiService
public interface WxUserService extends WxServiceBase<WxUser, Integer> {

    /**
     * 根据wxid读取用户
     * @param openId 用户标示
     * @return WxUser 目标记录
     */
    WxUser findByWxId(String openId);

    /**
     * 添加绑定
     * @param user 当前用户
     * @param wxAccessToken 访问wx的Access Token
     * @return boolean 是否绑定成功
     * @throws ServiceException ServiceException
     */
    boolean bindUser(UserIdentity user, WxAccessToken wxAccessToken) throws ServiceException;

    /**
     * 取消绑定
     * @param wxAccessToken 访问wx的Access Token
     * @return boolean 是否解除绑定成功
     */
    boolean removeBind(WxAccessToken wxAccessToken);
}
