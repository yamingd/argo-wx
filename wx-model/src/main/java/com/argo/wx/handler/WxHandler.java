package com.argo.wx.handler;

import com.argo.wx.response.WxResponse;

/**
 * Created by Yaming on 2014/12/18.
 */
public interface WxHandler<T> {

    String getMsgType();
    WxResponse execute(T message);
}
