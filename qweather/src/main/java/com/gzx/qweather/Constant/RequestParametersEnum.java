package com.gzx.qweather.Constant;

public enum RequestParametersEnum {
    KEY("key"),
    PUBLIC_ID("username"),
    T("t"),
    LOCATION("location"),
    LANG("lang"),
    UNIT("unit"),
    GZIP("gzip");

    private final String Param;

    RequestParametersEnum(String param) {
        this.Param = param;
    }

    public String getParam() {
        return Param;
    }
}
