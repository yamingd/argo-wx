package com.argo.wx.service.impl;

import com.argo.service.pool.AsyncTaskPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Created by user.
 */
public abstract class WxServiceBaseImpl<T extends Serializable, PK extends Comparable>
        implements InitializingBean, DisposableBean{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected AsyncTaskPool asyncTaskPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(asyncTaskPool);
        //TODO: load configuration
    }

    @Override
    public void destroy() throws Exception {

    }

}
