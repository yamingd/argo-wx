package com.argo.wx.service.impl;

import com.argo.core.annotation.Model;
import com.argo.core.configuration.SiteConfig;
import com.argo.core.entity.Pagination;
import com.argo.core.exception.EntityNotFoundException;
import com.argo.core.exception.ServiceException;
import com.argo.db.template.ServiceMSTemplate;
import com.argo.service.annotation.RmiService;
import com.argo.wx.WxApiClient;
import com.argo.wx.WxMappers;
import com.argo.wx.WxMenu;
import com.argo.wx.service.WxMenuService;
import com.argo.wx.service.WxMenuTx;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by $User on 2014-12-05 09:32.
 */
@Model(WxMenu.class)
@RmiService(serviceInterface=WxMenuService.class)
public class WxMenuServiceImpl extends ServiceMSTemplate implements WxMenuService{

    @Autowired
    private WxApiClient wxApiClient;

    @Override
    public WxMenu findById(Long oid) throws EntityNotFoundException {
        return super.findById(oid);
    }
    
    @WxMenuTx
    public Long add(WxMenu entity) throws ServiceException {
        return super.add(entity);
    }
    
    @WxMenuTx
    @Override
    public <T> boolean update(T arg) throws ServiceException {
        WxMenu entity = (WxMenu)arg;
        String sql = "update wx_menu set codeName=?, orderNo=?, parentId=?, title=? where id = ?";
        int ret = this.jdbcTemplateM.update(sql, entity.getCodeName(), entity.getOrderNo(), entity.getParentId(), entity.getTitle(), entity.getId());
        return ret > 0;
    }

    @Override
    @WxMenuTx
    public boolean remove(Long oid) throws ServiceException {
        return super.remove(oid);
    }

    @Override
    public Pagination<WxMenu> findAll(Pagination page){
        String sql = "select * from wx_menu order by parentId, orderNo";
        List<WxMenu> menus = this.jdbcTemplateS.query(sql, WxMappers.WxMenu_ROWMAPPER);
        page.setItems(menus);
        return page;
    }

    @Override
    public List<WxMenu> findByParent(int parentId) {
        String sql = "select * from wx_menu where parentId=? order by parentId, orderNo";
        List<WxMenu> menus = this.jdbcTemplateS.query(sql, WxMappers.WxMenu_ROWMAPPER, parentId);
        return menus;
    }

    @Override
    @WxMenuTx
    public boolean pushToWx() {
        String sql = "select * from wx_menu where statusId = 1 order by parentId, orderNo";
        List<WxMenu> menus = this.jdbcTemplateS.query(sql, WxMappers.WxMenu_ROWMAPPER);

        if (menus.size() == 0){
            return wxApiClient.deleteMenu();
        }

        String domain = SiteConfig.instance.getApp().get("domain") + "";
        List<Map> buttons = new ArrayList<Map>();

        //1. 按微信结构构造数据
        for (WxMenu item : menus){
            if (0 != item.getParentId()){
                continue;
            }
            Map button = new HashMap();
            buttons.add(button);
            button.put("name", item.getTitle());
            List<Map> subs = new ArrayList<Map>();
            button.put("sub_button", subs);
            for (WxMenu subitem : menus){
                if (item.getId().equals(subitem.getParentId())){
                    Map map = new HashMap();
                    map.put("name", subitem.getTitle());
                    if (StringUtils.isNotBlank(subitem.getPageUrl())){
                        String viewUrl = this.wxApiClient.formatViewUrl(subitem.getPageUrl());
                        if (viewUrl != null) {
                            map.put("type", "view");
                            map.put("url", viewUrl);
                        }
                    }else{
                        map.put("type", "click");
                        map.put("key", subitem.getCodeName());
                    }
                    subs.add(map);
                }
            }
        }

        Map wxmenu = new HashMap();
        wxmenu.put("button", buttons);

        boolean result = wxApiClient.pushMenu(wxmenu);

        if (result) {
            sql = "update wx_menu set wxSync=1 where statusId = 1";
            this.jdbcTemplateM.update(sql);
            logger.info("推送菜单到微信成功.");
            return true;
        }

        return false;
    }

}