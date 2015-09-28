package com.argo.wx.web.weixin;

import com.argo.web.MvcController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 3/15/15.
 */
public class WxBaseController extends MvcController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean needLogin() {
        return false;
    }
}
