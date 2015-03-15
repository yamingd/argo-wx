package com.argo.wx;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by $User on 2014-12-05 09:32.
 */
public class WxMappers {
	

    public static final RowMapper<WxMenu> WxMenu_ROWMAPPER = new BeanPropertyRowMapper<WxMenu>(
            WxMenu.class);

    public static final RowMapper<WxUser> WxUser_ROWMAPPER = new BeanPropertyRowMapper<WxUser>(WxUser.class);
}