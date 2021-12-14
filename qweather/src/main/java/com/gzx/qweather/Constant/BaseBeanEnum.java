package com.gzx.qweather.Constant;

public enum BaseBeanEnum {


    CODE("code"),
    UPDATE_TIME("updateTime"),
    FX_LINK("fxLink"),
    LOCATION("location"),
    NOW("now"),
    DAILY("daily"),
    HOURLY("hourly")

    ;


    private final String type;

    BaseBeanEnum(String type) {
        this.type = type;
    }

    public String getKey() {
        return type;
    }

}
