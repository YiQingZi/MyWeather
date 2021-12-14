package com.gzx.qweather.Constant;

public enum UrlEnum {

    WEATHER_HTTP("https://api.qweather.com"),
    WEATHER_DEV_HTTP("https://devapi.qweather.com"),
    GEO_HTTP("https://geoapi.qweather.com"),
    CITY_DETAILED("/v2/city/lookup?"),
    WEATHER_NOW("/v7/weather/now?"),

    WEATHER_3D("/v7/weather/3d?"),
    WEATHER_7D("/v7/weather/7d?"),
    WEATHER_10D("/v7/weather/10d?"),
    WEATHER_15D("/v7/weather/15d?"),

    WEATHER_24H("/v7/weather/24h?"),
    WEATHER_72H("/v7/weather/72h?"),
    WEATHER_168H("/v7/weather/168h?")

    ;


    private final String URL;

    UrlEnum(String url) {
        this.URL = url;
    }

    public String getURL() {
        return URL;
    }
}
