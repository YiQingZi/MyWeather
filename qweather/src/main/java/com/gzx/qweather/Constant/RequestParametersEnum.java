package com.gzx.qweather.Constant;

public enum RequestParametersEnum {
    KEY("key"),
    PUBLIC_ID("username"),
    T("t"),
    LOCATION("location"),
    LANG("lang"),
    RANGE("range"),
    ADM("adm"),
    NUMBER("number"),
    UNIT("unit"),
    GZIP("gzip"),
    TYPE("type")
    ;

    private final String Param;

    RequestParametersEnum(String param) {
        this.Param = param;
    }

    public String getParam() {
        return Param;
    }
}
