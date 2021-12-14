package com.gzx.qweather.Bean;


public class BaseBean {

    public String code;
    public String upDataTime;
    public String fxLink;
    public String now;


    public void setCode(String var){
        this.code = var;
    }

    public String getCode(){
        return this.code;
    }

    public void setUpDataTime(String var){
        this.upDataTime = var;
    }

    public String getUpDataTime(){
        return this.upDataTime;
    }

    public void setFxLink(String var){
        this.fxLink = var;
    }

    public String getFxLink(){
        return this.fxLink;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getNow() {
        return now;
    }
}
