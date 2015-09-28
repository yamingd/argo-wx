package com.argo.wx.service.impl;

import com.argo.db.exception.EntityNotFoundException;
import com.argo.security.UserIdentity;
import com.argo.service.ServiceException;
import com.argo.wx.mapper.WxMenuMapper;
import com.argo.wx.model.WxMenu;
import com.argo.wx.service.WxApiClient;
import com.argo.wx.service.WxMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by $User on 2014-12-05 09:32.
 */
@Service
public class WxMenuServiceImpl extends WxServiceBaseImpl<WxMenu, Integer> implements WxMenuService{

    @Autowired
    private WxApiClient wxApiClient;

    @Autowired
    protected WxMenuMapper wxMenuMapper;

    @Override
    public List<WxMenu> findByParent(int parentId) {
        List<WxMenu> menus = this.wxMenuMapper.selectRows(null, "parentId=?", "parentId, orderNo", null, new Object[]{parentId});
        return menus;
    }

    @Override
    public boolean pushToWx() {

        List<WxMenu> menus = this.wxMenuMapper.selectRows(null, "statusId=?", "parentId, orderNo", null, new Object[]{1});

        if (menus.size() == 0){
            return wxApiClient.deleteMenu();
        }

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
            this.wxMenuMapper.update(null, "wxSync=?", "statusId=?", 1, 1);
            logger.info("推送菜单到微信成功.");
            return true;
        }

        return false;
    }


    @Override
    public WxMenu find(UserIdentity user, Integer id) throws EntityNotFoundException {
        return wxMenuMapper.find(null, id);
    }

    @Override
    public List<WxMenu> findList(UserIdentity user, Integer... ids) {
        return wxMenuMapper.selectRows(null, ids, true);
    }

    @Override
    public WxMenu create(UserIdentity user, WxMenu item) throws ServiceException {
        wxMenuMapper.insert(null, item);
        return item;
    }

    @Override
    public WxMenu save(UserIdentity user, WxMenu item) throws ServiceException {
        wxMenuMapper.update(null, item);
        return item;
    }

    @Override
    public boolean removeBy(UserIdentity user, Integer id) throws ServiceException {
        return wxMenuMapper.deleteBy(null, id);
    }

    @Override
    public boolean remove(UserIdentity user, WxMenu item) throws ServiceException {
        return wxMenuMapper.delete(null, item);
    }
}
