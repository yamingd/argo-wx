package com.argo.wx.handler;

import com.google.common.collect.Maps;
import com.argo.wx.message.WxMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Yaming on 2014/12/18.
 */
@Component
public class WxHandlerFactory {

    private Map<String, WxHandler> handlers = Maps.newHashMap();

    public void add(WxHandler handler){
        handlers.put(handler.getMsgType().toLowerCase(), handler);
    }

    public WxHandler get(WxMessage message){
        String type = message.getMsgType();
        if (message.getMsgType().equalsIgnoreCase("event")){
            type = message.getMsgTypeKey();
        }
        return handlers.get(type.toLowerCase());
    }
}
