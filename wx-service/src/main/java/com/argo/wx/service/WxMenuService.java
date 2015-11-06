package com.argo.wx.service;

import com.argo.annotation.RmiService;
import com.argo.wx.model.WxMenu;

import java.util.List;

/**
 * Created by $User on 2014-12-05 09:32.
 */
@RmiService
public interface WxMenuService extends WxServiceBase<WxMenu, Integer> {
    /**
     * 读取子菜单列表
     * @param parentId 一级菜单id
     * @return List
     */
    List<WxMenu> findByParent(int parentId);

    /**
     * 推送更新到微信
     * @return boolean
     */
    boolean pushToWx();
}
