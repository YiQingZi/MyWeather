package com.gzx.qweather.Constant;

public enum UrlEnum {

    WEATHER_HTTP("https://api.qweather.com"),
    WEATHER_DEV_HTTP("https://devapi.qweather.com"),
    GEO_HTTP("https://geoapi.qweather.com"),
    WEATHER_NOW("/v7/weather/now?"),
    CITY_DETAILED("/v2/city/lookup?");

    private final String URL;

    UrlEnum(String url) {
        this.URL = url;
    }

    public String getURL() {
        return URL;
    }
}
