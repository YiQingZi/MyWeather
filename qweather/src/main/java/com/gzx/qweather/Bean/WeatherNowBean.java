package com.gzx.qweather.Bean;

public class WeatherNowBean {


    public String obsTime;   //数据观测时间
    public String temp;   //温度，默认单位：摄氏度
    public String feelsLike;   //体感温度，默认单位：摄氏度
    public String icon;   //天气状况和图标的代码，图标可通过天气状况和图标下载
    public String text;   //天气状况的文字描述，包括阴晴雨雪等天气状态的描述
    public String wind360;   //风向360角度
    public String windDir;   //风向
    public String windScale;   //风力等级
    public String windSpeed;   //风速，公里/小时
    public String humidity;   //相对湿度，百分比数值
    public String precip;   //当前小时累计降水量，默认单位：毫米
    public String pressure;   //大气压强，默认单位：百帕
    public String vis;   //能见度，默认单位：公里
    public String cloud;   //云量，百分比数值
    public String dew;   //露点温度

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setObsTime(String obsTime) {
        this.obsTime = obsTime;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void setWind360(String wind360) {
        this.wind360 = wind360;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCloud() {
        return cloud;
    }

    public String getDew() {
        return dew;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public String getObsTime() {
        return obsTime;
    }

    public String getPrecip() {
        return precip;
    }

    public String getPressure() {
        return pressure;
    }

    public String getTemp() {
        return temp;
    }

    public String getText() {
        return text;
    }

    public String getVis() {
        return vis;
    }

    public String getWind360() {
        return wind360;
    }

    public String getWindDir() {
        return windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
