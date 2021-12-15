package com.gzx.qweather.Bean;

public class WeatherLifeBean {

    /** 预报日期 */
    public   String DATE	= "date";	//预报日期
    /** 生活指数类型ID */
    public   String TYPE	= "type";	//生活指数类型ID
    /** 生活指数类型的名称 */
    public   String NAME	= "name";	//生活指数类型的名称
    /** 生活指数预报等级 */
    public   String LEVEL	= "level";	//生活指数预报等级
    /** 生活指数预报级别名称 */
    public   String CATEGORY	= "category";	//生活指数预报级别名称
    /** 生活指数预报的详细描述，可能为空 */
    public   String TEXT	= "text";	//生活指数预报的详细描述，可能为空

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public void setLEVEL(String LEVEL) {
        this.LEVEL = LEVEL;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public String getDATE() {
        return DATE;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public String getNAME() {
        return NAME;
    }

    public String getTEXT() {
        return TEXT;
    }

    public String getTYPE() {
        return TYPE;
    }
}
