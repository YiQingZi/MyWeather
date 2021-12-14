package com.gzx.qweather;

import com.gzx.qweather.Bean.CityInfo;
import com.gzx.qweather.Bean.WeatherNow;
import com.gzx.qweather.Constant.RequestParametersEnum;
import com.gzx.qweather.Constant.UrlEnum;
import com.gzx.qweather.Utils.HttpUtil;
import com.gzx.qweather.Utils.UrlUtil;
import ohos.app.Context;

import java.util.HashMap;
import java.util.Map;

public class QWeather {

    private final String privateKey;
    private final String publicId;
    private Context context;
    private Map<String,String> requestParameters;
    private Map<String,String> cityRequestParameters;

    public QWeather(Context context, String publicId, String privateKey) {
        this.context = context;
        this.publicId = publicId;
        this.privateKey = privateKey;
    }

    private QWeather(String publicId, String privateKey) {
        this.publicId = publicId;
        this.privateKey = privateKey;
    }


    /**
     * @param cityId 和风天气定义的城市id，通过CityCallBack获取
     * @param lang 返回结果支持的语言
     * @param unit 度量衡单位
     * @param gzip 请求内容是否开启压缩
     * */
    public void setWeatherParams(String cityId,String lang,String unit,String gzip){
        this.requestParameters = new HashMap<>();
        requestParameters.put(RequestParametersEnum.LOCATION.getParam(),cityId);
        requestParameters.put(RequestParametersEnum.LANG.getParam(),lang);
        requestParameters.put(RequestParametersEnum.UNIT.getParam(),unit);
        requestParameters.put(RequestParametersEnum.GZIP.getParam(),gzip);

        requestParameters.put(RequestParametersEnum.KEY.getParam(),this.privateKey);
        requestParameters.put(RequestParametersEnum.PUBLIC_ID.getParam(),this.publicId);
        requestParameters.put(RequestParametersEnum.T.getParam(), Integer.toString((int)(System.currentTimeMillis() / 1000)));
    }

    /**
     * @param city 城市信息，经纬度(116.41,39.92)、汉字(北京)、拼音/英文(beijing)
     * @param gzip 请求内容是否开启压缩
     * @param number 最大返回匹配个数
     * @param range 搜索范围，例如中国为cn，参考ISO 3166标准
     * @param adm 该城市的上一级行政区
     * @param lang 返回结果支持的语言
     * @see com.gzx.qweather.Constant.RequestParameters
     * */
    public void setCityParams(String city,String lang,String number,String range,String adm,String gzip){
        this.cityRequestParameters = new HashMap<>();
        cityRequestParameters.put(RequestParametersEnum.LOCATION.getParam(),city);
        cityRequestParameters.put(RequestParametersEnum.LANG.getParam(),lang);
        cityRequestParameters.put(RequestParametersEnum.NUMBER.getParam(),number);
        cityRequestParameters.put(RequestParametersEnum.GZIP.getParam(),gzip);
        cityRequestParameters.put(RequestParametersEnum.RANGE.getParam(),range);
        cityRequestParameters.put(RequestParametersEnum.ADM.getParam(),adm);

        cityRequestParameters.put(RequestParametersEnum.KEY.getParam(),this.privateKey);
        cityRequestParameters.put(RequestParametersEnum.PUBLIC_ID.getParam(),this.publicId);
        cityRequestParameters.put(RequestParametersEnum.T.getParam(), Integer.toString((int)(System.currentTimeMillis() / 1000)));
    }

    public void GetCityInfoCallBack(CityCallBack cityCallBack){
        String url = UrlUtil.getUrl(UrlEnum.GEO_HTTP.getURL()+UrlEnum.CITY_DETAILED.getURL(),cityRequestParameters);
        HttpUtil.GetCityInfoThread getCityInfoThread = new HttpUtil.GetCityInfoThread(context,cityCallBack,url);
        getCityInfoThread.start();
    }

    public void GetOneNowCallBack(NowWeatherCallback nowWeatherCallback){
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.WEATHER_NOW.getURL(),requestParameters);
        HttpUtil.GetNowWeatherThread getNowWeatherThread = new HttpUtil.GetNowWeatherThread(context,nowWeatherCallback,url);
        getNowWeatherThread.start();
    }

    public static abstract class NowWeatherCallback implements GetNowWeatherCallBack{
        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(WeatherNow weatherNow) {

        }
    }

    /** 城市信息回调，注意同名城市可能同时多次回调，请做城市信息判断 */
    public static abstract class CityCallBack implements GetCityCallBack{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(CityInfo cityInfo) {

        }
    }

    public static abstract class DailyWeather implements GetDailyWeather{
        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(WeatherNow weatherNow) {

        }
    }

}
