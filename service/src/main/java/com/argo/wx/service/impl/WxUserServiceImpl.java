package com.argo.wx.service.impl;

import com.argo.core.annotation.Model;
import com.argo.core.base.BaseUser;
import com.argo.core.exception.ServiceException;
import com.argo.db.template.ServiceMSTemplate;
import com.argo.service.annotation.RmiService;
import com.argo.wx.WxAccessToken;
import com.argo.wx.WxMappers;
import com.argo.wx.WxUser;
import com.argo.wx.service.WxMenuService;
import com.argo.wx.service.WxUserService;
import com.argo.wx.service.WxUserTx;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 3/15/15.
 */
@Model(WxUser.class)
@RmiService(serviceInterface=WxMenuService.class)
public class WxUserServiceImpl extends ServiceMSTemplate implements WxUserService {


    @Override
    public WxUser findByWxId(String openId) {
        String sql = "select * from wx_user where openId = ?";
        List<WxUser> list = this.jdbcTemplateS.query(sql, WxMappers.WxUser_ROWMAPPER, openId);
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    @WxUserTx
    public boolean bindUser(BaseUser user, WxAccessToken wxAccessToken) throws ServiceException {
        WxUser wxUser = this.findByWxId(wxAccessToken.getOpenId());
        Long userId = null;
        if (null != user){
            userId = user.getUid();
        }
        if (null == wxUser){
            wxUser = new WxUser();
            wxUser.setOpenId(wxAccessToken.getOpenId());
            wxUser.setOpenToken(wxAccessToken.getToken());
            wxUser.setCreateTime(new Date());
            wxUser.setStatusId(0);
            if (null != userId){
                wxUser.setBindTime(new Date());
                wxUser.setStatusId(1);
            }
            Long id = this.add(wxUser);
            return id > 0;
        }else{
            if (null != userId){
                wxUser.setOpenToken(wxAccessToken.getToken());
                wxUser.setUserId(userId);
                wxUser.setBindTime(new Date());
                wxUser.setStatusId(1);
                return this.updateEntity(wxUser);
            }
        }
        return false;
    }

    @Override
    @WxUserTx
    public boolean removeBind(WxAccessToken wxAccessToken) {
        String sql = "update wx_user set statusId=?, rmBindTime=? where openId=?";
        int ret = this.jdbcTemplateM.update(sql, 2, new Date(), wxAccessToken.getOpenId());
        return ret > 0;
    }
}
