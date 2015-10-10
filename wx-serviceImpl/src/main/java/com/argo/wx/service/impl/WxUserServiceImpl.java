package com.argo.wx.service.impl;

import com.argo.db.exception.EntityNotFoundException;
import com.argo.security.UserIdentity;
import com.argo.service.ServiceException;
import com.argo.wx.mapper.WxUserMapper;
import com.argo.wx.model.WxUser;
import com.argo.wx.service.WxUserService;
import com.argo.wx.token.WxAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 3/15/15.
 */
@Service
public class WxUserServiceImpl extends WxServiceBaseImpl<WxUser, Integer> implements WxUserService {

    @Autowired
    protected WxUserMapper wxUserMapper;

    @Override
    public WxUser findByWxId(String openId) {
        List<WxUser> list;
        list = this.wxUserMapper.selectRows(null, "openId=?", null, null, new Object[]{openId});
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean bindUser(UserIdentity user, WxAccessToken wxAccessToken) throws ServiceException {
        WxUser wxUser = this.findByWxId(wxAccessToken.getOpenId());
        Integer userId = null;
        if (null != user){
            userId = user.getIdentityId();
        }
        if (null == wxUser){
            wxUser = new WxUser();
            wxUser.setOpenId(wxAccessToken.getOpenId());
            wxUser.setOpenToken(wxAccessToken.getToken());
            wxUser.setCreateTime(new Date());
            wxUser.setStatusId((byte)0);
            if (null != userId){
                wxUser.setBindTime(new Date());
                wxUser.setStatusId((byte)1);
            }
            wxUser = this.create(user, wxUser);
            return wxUser.getId() > 0;
        }else{
            if (null != userId){
                wxUser.setOpenToken(wxAccessToken.getToken());
                wxUser.setUserId(userId);
                wxUser.setBindTime(new Date());
                wxUser.setStatusId((byte)1);
                wxUser = this.save(user, wxUser);
            }
        }
        return false;
    }

    @Override
    public boolean removeBind(WxAccessToken wxAccessToken) {
        return this.wxUserMapper.update(null, "statusId=?, rmBindTime=?", "openId=?", 2, new Date(), wxAccessToken.getOpenId());
    }


    @Override
    public WxUser find(UserIdentity user, Integer id) throws EntityNotFoundException {
        return wxUserMapper.find(null, id);
    }

    @Override
    public List<WxUser> findList(UserIdentity user, Integer... ids) {
        return wxUserMapper.selectRows(null, ids, true);
    }

    @Override
    public WxUser create(UserIdentity user, WxUser item) throws ServiceException {
        wxUserMapper.insert(null, item);
        return item;
    }

    @Override
    public WxUser save(UserIdentity user, WxUser item) throws ServiceException {
        wxUserMapper.update(null, item);
        return item;
    }

    @Override
    public boolean removeBy(UserIdentity user, Integer id) throws ServiceException {
        return wxUserMapper.deleteBy(null, id);
    }

    @Override
    public boolean remove(UserIdentity user, WxUser item) throws ServiceException {
        return wxUserMapper.delete(null, item);
    }

}
