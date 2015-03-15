package com.argo.wx.service;

import com.argo.core.base.BaseUser;
import com.argo.core.exception.ServiceException;
import com.argo.db.template.ServiceBase;
import com.argo.wx.WxAccessToken;
import com.argo.wx.WxUser;

/**
 * Created by user on 3/15/15.
 */
public interface WxUserService extends ServiceBase {

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
    boolean bindUser(BaseUser user, WxAccessToken wxAccessToken) throws ServiceException;

    /**
     * 取消绑定
     * @param wxAccessToken
     * @return
     */
    boolean removeBind(WxAccessToken wxAccessToken);
}
