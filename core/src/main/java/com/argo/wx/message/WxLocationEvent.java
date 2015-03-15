package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxLocationEvent extends WxEvent {

    private Float latitude;
    private Float longitude;
    private Float precision;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    @Override
    public Element parseFrom(Document document){
        Element root = super.parseFrom(document);
        this.latitude = Float.parseFloat(root.getChildText("Latitude"));
        this.longitude = Float.parseFloat(root.getChildText("Longitude"));
        this.precision = Float.parseFloat(root.getChildText("Precision"));
        return root;
    }
}
