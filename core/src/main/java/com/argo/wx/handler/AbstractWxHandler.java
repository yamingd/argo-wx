package com.argo.wx.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yaming on 2014/12/18.
 */
public abstract class AbstractWxHandler<T> implements WxHandler<T>, InitializingBean {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxHandlerFactory factory;

    @Override
    public void afterPropertiesSet() throws Exception {
        factory.add(this);
    }

}
