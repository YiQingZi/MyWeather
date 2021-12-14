package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherNow;
import com.gzx.qweather.Constant.RequestParametersEnum;
import com.gzx.qweather.Constant.UrlEnum;
import com.gzx.qweather.Utils.UrlUtil;
import ohos.app.Context;

import java.util.HashMap;
import java.util.Map;

public class QWeather {

    private String privateKey;
    private String publicId;
    private Context context;
    private NowWeatherCallback nowWeatherCallback;
    private Map<String,String> requestParameters;

    public QWeather(Context context, String publicId, String privateKey) {
        this.context = context;
        this.publicId = publicId;
        this.privateKey = privateKey;
    }

    public QWeather(String publicId, String privateKey) {
        this.publicId = publicId;
        this.privateKey = privateKey;
    }

//    KEY("key"),
//    LOCATION("location"),
//    LANG("lang"),
//    UNIT("unit"),
//    GZIP("gzip");

    public void setParams(String cityId,String lang,String unit,String gzip){
        this.requestParameters = new HashMap<>();
        requestParameters.put(RequestParametersEnum.LOCATION.getParam(),cityId);
        requestParameters.put(RequestParametersEnum.LANG.getParam(),lang);
        requestParameters.put(RequestParametersEnum.UNIT.getParam(),unit);
        requestParameters.put(RequestParametersEnum.GZIP.getParam(),gzip);
        requestParameters.put(RequestParametersEnum.KEY.getParam(),this.privateKey);
        requestParameters.put(RequestParametersEnum.PUBLIC_ID.getParam(),this.publicId);
        requestParameters.put(RequestParametersEnum.T.getParam(), Integer.toString((int)(System.currentTimeMillis() / 1000)));
    }

    public void registerNowCallBack(NowWeatherCallback nowWeatherCallback){
        this.nowWeatherCallback = nowWeatherCallback;
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.WEATHER_NOW.getURL(),requestParameters);
        HttpUtil.GetThread getThread = new HttpUtil.GetThread(context,nowWeatherCallback,url);
        getThread.start();
    }


    public static abstract class NowWeatherCallback implements GetNowWeatherCallBack{
        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(WeatherNow weatherNow) {

        }
    }

    public static abstract class CityCallBack implements GetCityCallBack{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(String id, String name, String adm1, String adm2) {

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
