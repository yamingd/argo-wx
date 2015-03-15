package com.argo.wx.response;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxImageTextResponse extends WxResponse {

    /**
     * 图文消息个数，限制为10条以内
     */
    private List<WxImageTextItem> articles;

    public WxImageTextResponse() {
        this.setMsgType("news");
        this.articles = new ArrayList<WxImageTextItem>();
    }

    public List<WxImageTextItem> getArticles() {
        return articles;
    }

    public void setArticles(List<WxImageTextItem> articles) {
        this.articles = articles;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        doc.getRootElement().addContent(new Element("ArticleCount").setText(this.articles.size() + ""));
        Element element = new Element("Articles");
        for (WxImageTextItem item : this.articles){
            item.appendTo(element);
        }
        doc.getRootElement().addContent(element);
        return doc;
    }
}
