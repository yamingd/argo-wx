package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxMessageBuilder {

    public static WxMessage build(InputStream is) throws JDOMException, IOException {
        // the SAXBuilder is the easiest way to create the JDOM2 objects.
        SAXBuilder jdomBuilder = new SAXBuilder();
        // jdomDocument is the JDOM2 Object
        Document document = jdomBuilder.build(is);
        String type = document.getRootElement().getChildText("MsgType");
        WxMessage message = newMessage(type);
        if (message != null) {
            message.parseFrom(document);
        }
        return message;
    }

    private static WxMessage newMessage(String type){
        if (type.equalsIgnoreCase(WxMessageTypeEnum.Event)){
            return new WxEvent();
        }else if (type.equalsIgnoreCase(WxMessageTypeEnum.Image)){
            return new WxImageMessage();
        }else if (type.equalsIgnoreCase(WxMessageTypeEnum.Link)){
            return new WxLinkMessage();
        }else if (type.equalsIgnoreCase(WxMessageTypeEnum.Text)){
            return new WxTextMessage();
        }else if (type.equalsIgnoreCase(WxMessageTypeEnum.Video)){
            return new WxVideoMessage();
        }else if (type.equalsIgnoreCase(WxMessageTypeEnum.Voice)){
            return new WxVoiceMessage();
        }
        return null;
    }
}
