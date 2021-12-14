package com.gzx.qweather.Utils;


import ohos.app.Context;
import ohos.location.Location;
import ohos.location.Locator;


public class LocationUtil {

    private Locator locator;


    public LocationUtil(Context context) {
        this.locator = new Locator(context);
    }

    /**
     * 快速获取系统缓存的定位信息,将其作为请求参数 location
     * */
    public String getCachedLocation() throws SecurityException{
        Location location = locator.getCachedLocation();
        return location.getLongitude()+","+location.getLatitude();
    }

}
