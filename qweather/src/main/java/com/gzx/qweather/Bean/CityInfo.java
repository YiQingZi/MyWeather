package com.gzx.qweather.Bean;

public class CityInfo {

    /** 地区/城市名称 */
    public String name;  //	地区/城市名称
    /** 地区/城市ID */
    public String id;  //		地区/城市ID
    /** 地区/城市纬度 */
    public String lat;  //		地区/城市纬度
    /** 地区/城市经度 */
    public String lon;  //		地区/城市经度
    /** 地区/城市的上级行政区划名称 */
    public String adm2;  //		地区/城市的上级行政区划名称
    /** 地区/城市所属一级行政区域 */
    public String adm1;  //		地区/城市所属一级行政区域
    /** 地区/城市所属国家名称 */
    public String country;  //		地区/城市所属国家名称
    /** 地区/城市所在时区 */
    public String tz;  //		地区/城市所在时区
    /** 地区/城市目前与UTC时间偏移的小时数，参考详细说明 */
    public String utcOffset;  //		地区/城市目前与UTC时间偏移的小时数，参考详细说明
    /** 地区/城市是否当前处于夏令时1 表示当前处于夏令时0 表示当前不是夏令时 */
    public String isDst;  //		地区/城市是否当前处于夏令时1 表示当前处于夏令时0 表示当前不是夏令时
    /** 地区/城市的属性 */
    public String type;  //		地区/城市的属性
    /** 地区评分 */
    public String rank;  //		地区评分
    /** 该地区的天气预报网页链接，便于嵌入你的网站或应用*/
    public String fxLink;  //		该地区的天气预报网页链接，便于嵌入你的网站或应用

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsDst(String isDst) {
        this.isDst = isDst;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getAdm1() {
        return adm1;
    }

    public String getAdm2() {
        return adm2;
    }

    public String getCountry() {
        return country;
    }

    public String getFxLink() {
        return fxLink;
    }

    public String getId() {
        return id;
    }

    public String getIsDst() {
        return isDst;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public String getType() {
        return type;
    }

    public String getTz() {
        return tz;
    }

    public String getUtcOffset() {
        return utcOffset;
    }
}
