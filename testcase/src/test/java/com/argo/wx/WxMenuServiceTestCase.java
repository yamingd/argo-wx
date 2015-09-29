package com.argo.wx;

import com.argo.wx.model.WxMenu;
import com.argo.wx.service.WxMenuService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by yamingd on 9/29/15.
 */
public class WxMenuServiceTestCase {

    WxMenuService wxMenuService;

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-rmi0.xml");
        wxMenuService = context.getBean(WxMenuService.class);
    }

    @Test
    public void testCreate() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            WxMenu wxMenu = new WxMenu();
            wxMenu.setTitle("menu: " + i);
            wxMenu.setCreateTime(new Date());

            wxMenu = wxMenuService.create(null, wxMenu);
//            Assert.assertNotNull(wxMenu);
//            Assert.assertNotNull(wxMenu.getId());

        }

        long ts = System.currentTimeMillis() - start;
        System.out.println(ts); // 18997 ms
    }

    @Test
    public void testCreateErrorWithTx() throws Exception {
        WxMenu wxMenu = new WxMenu();
        wxMenu.setTitle("menu:menu: menu: menu: menu: menu: menu: menu: menu: menu: menu: menu: ");
        wxMenu.setCreateTime(new Date());

        wxMenu = wxMenuService.create(null, wxMenu);
    }

    @Test
    public void testUpdate() throws Exception {
        WxMenu wxMenu = new WxMenu();
        wxMenu.setId(17);
        wxMenu.setTitle("menu:menu:17");
        wxMenu.setUpdateTime(new Date());
        wxMenu.setOrderNo((short)10);
        wxMenu = wxMenuService.save(null, wxMenu);
    }
}
