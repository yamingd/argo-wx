package com.argo.wx.web.controllers;

import com.argo.core.web.MvcController;

/**
 * Created by user on 3/15/15.
 */
public class MobileWxBaseController extends MvcController {

    @Override
    public boolean needLogin() {
        return false;
    }
}
