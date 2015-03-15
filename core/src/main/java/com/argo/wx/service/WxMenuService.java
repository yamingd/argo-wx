package com.argo.wx.service;

import com.argo.core.entity.Pagination;
import com.argo.db.template.ServiceBase;
import com.argo.wx.WxMenu;

import java.util.List;

/**
 * Created by $User on 2014-12-05 09:32.
 */
public interface WxMenuService extends ServiceBase  {
	
	Pagination<WxMenu> findAll(Pagination page);

    /**
     * 读取子菜单列表
     * @param parentId
     * @return
     */
    List<WxMenu> findByParent(int parentId);

    /**
     * 推送更新到微信
     * @return
     */
    boolean pushToWx();
}