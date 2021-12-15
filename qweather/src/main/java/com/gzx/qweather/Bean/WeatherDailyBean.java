package com.gzx.qweather.Bean;

public class WeatherDailyBean {

    /** 预报日期*/
    public String fxDate;   //预报日期
    /** 日出时间 */
    public String sunrise;

    /** 日落时间 */
    public String sunset;

    /** 月升时间 */
    public String moonrise;

    /** 月落时间 */
    public String moonset;

    /** 月相名称 */
    public String moonPhase;

    /** 月相图标代码，图标可通过天气状况和图标下载 */
    public String moonPhaseIcon;

    /** 预报当天最高温度 */
    public String tempMax;

    /** 预报当天最低温度 */
    public String tempMin;

    /** 预报白天天气状况的图标代码，图标可通过天气状况和图标下载 */
    public String iconDay;

    /** 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述 */
    public String textDay;

    /** 预报夜间天气状况的图标代码，图标可通过天气状况和图标下载 */
    public String iconNight;

    /** 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述 */
    public String textNight;

    /** 预报白天风向360角度 */
    public String wind360Day;

    /** 预报白天风向 */
    public String windDirDay;

    /** 预报白天风力等级 */
    public String windScaleDay;

    /** 预报白天风速，公里/小时 */
    public String windSpeedDay;

    /** 预报夜间风向360角度 */
    public String wind360Night;

    /** 预报夜间当天风向 */
    public String windDirNight;

    /** 预报夜间风力等级 */
    public String windScaleNight;

    /** 预报夜间风速，公里/小时*/
    public String windSpeedNight;

    /** 预报当天总降水量，默认单位：毫米 */
    public String precip;

    /** 紫外线强度指数 */
    public String uvIndex;

    /** 相对湿度，百分比数值 */
    public String humidity;

    /** 大气压强，默认单位：百帕 */
    public String pressure;

    /** 能见度，默认单位：公里 */
    public String vis; //能见度，默认单位：公里

    /** 云量，百分比数值 */
    public String cloud; //云量，百分比数值


    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public void setMoonPhaseIcon(String moonPhaseIcon) {
        this.moonPhaseIcon = moonPhaseIcon;
    }

    public void setIconDay(String iconDay) {
        this.iconDay = iconDay;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public void setIconNight(String iconNight) {
        this.iconNight = iconNight;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setWind360Day(String wind360Day) {
        this.wind360Day = wind360Day;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public void setWind360Night(String wind360Night) {
        this.wind360Night = wind360Night;
    }

    public void setWindDirDay(String windDirDay) {
        this.windDirDay = windDirDay;
    }

    public void setWindScaleDay(String windScaleDay) {
        this.windScaleDay = windScaleDay;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setWindDirNight(String windDirNight) {
        this.windDirNight = windDirNight;
    }

    public void setWindScaleNight(String windScaleNight) {
        this.windScaleNight = windScaleNight;
    }

    public void setWindSpeedDay(String windSpeedDay) {
        this.windSpeedDay = windSpeedDay;
    }

    public void setWindSpeedNight(String windSpeedNight) {
        this.windSpeedNight = windSpeedNight;
    }

    public String getVis() {
        return vis;
    }

    public String getPressure() {
        return pressure;
    }

    public String getPrecip() {
        return precip;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getCloud() {
        return cloud;
    }

    public String getFxDate() {
        return fxDate;
    }

    public String getIconDay() {
        return iconDay;
    }

    public String getIconNight() {
        return iconNight;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public String getMoonPhaseIcon() {
        return moonPhaseIcon;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getWind360Day() {
        return wind360Day;
    }

    public String getWind360Night() {
        return wind360Night;
    }

    public String getWindDirDay() {
        return windDirDay;
    }

    public String getWindDirNight() {
        return windDirNight;
    }

    public String getWindScaleDay() {
        return windScaleDay;
    }

    public String getWindScaleNight() {
        return windScaleNight;
    }

    public String getWindSpeedDay() {
        return windSpeedDay;
    }

    public String getWindSpeedNight() {
        return windSpeedNight;
    }
}
