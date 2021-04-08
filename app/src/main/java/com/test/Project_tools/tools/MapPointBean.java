package com.special.PengCheng.tools;

import java.io.Serializable;

/**
 *
 */
public class MapPointBean implements Serializable {

    private String longX;
    private String lat;


    public String getLongX() {
        return longX;
    }

    public void setLongX(String longX) {
        this.longX = longX;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
