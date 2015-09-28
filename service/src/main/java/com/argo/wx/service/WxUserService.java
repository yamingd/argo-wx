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
     * @param openId
     * @return
     */
    WxUser findByWxId(String openId);

    /**
     * 添加绑定
     * @param user
     * @param wxAccessToken
     * @return
     */
    boolean bindUser(UserIdentity user, WxAccessToken wxAccessToken) throws ServiceException;

    /**
     * 取消绑定
     * @param wxAccessToken
     * @return
     */
    boolean removeBind(WxAccessToken wxAccessToken);
}
