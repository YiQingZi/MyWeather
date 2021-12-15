package com.gzx.qweather;

import com.gzx.qweather.Bean.CityInfoBean;
import com.gzx.qweather.Bean.WeatherDailyBean;
import com.gzx.qweather.Bean.WeatherLifeBean;
import com.gzx.qweather.Bean.WeatherNowBean;
import com.gzx.qweather.Constant.RequestParametersEnum;
import com.gzx.qweather.Constant.UrlEnum;
import com.gzx.qweather.Utils.HttpUtil;
import com.gzx.qweather.Utils.UrlUtil;
import ohos.app.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QWeather {

    private final String privateKey;
    private final String publicId;
    private Context context;
    private Map<String,String> requestParameters;
    private Map<String,String> cityRequestParameters;
    private Map<String,String> lifeRequestParameters;

    public QWeather(Context context, String publicId, String privateKey) {
        this.context = context;
        this.publicId = publicId;
        this.privateKey = privateKey;
    }

    private QWeather(String publicId, String privateKey) {
        this.publicId = publicId;
        this.privateKey = privateKey;
    }

    public void setWeatherLifeParams(String cityId,String lang,String[] type,String gzip){
        this.lifeRequestParameters = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : type){
            if (null != s){
                if (!s.equals("")){
                    stringBuilder.append(s).append(",");
                }
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        lifeRequestParameters.put(RequestParametersEnum.LOCATION.getParam(),cityId);
        lifeRequestParameters.put(RequestParametersEnum.LANG.getParam(),lang);
        lifeRequestParameters.put(RequestParametersEnum.TYPE.getParam(), stringBuilder.toString());
        lifeRequestParameters.put(RequestParametersEnum.GZIP.getParam(),gzip);

        lifeRequestParameters.put(RequestParametersEnum.KEY.getParam(),this.privateKey);
        lifeRequestParameters.put(RequestParametersEnum.PUBLIC_ID.getParam(),this.publicId);
        lifeRequestParameters.put(RequestParametersEnum.T.getParam(), Integer.toString((int)(System.currentTimeMillis() / 1000)));
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

    public void GetCityInfoCallBack(CityCallBack cityCallBack) throws SecurityException{
        String url = UrlUtil.getUrl(UrlEnum.GEO_HTTP.getURL()+UrlEnum.CITY_DETAILED.getURL(),cityRequestParameters);
        HttpUtil.GetCityInfoThread getCityInfoThread = new HttpUtil.GetCityInfoThread(context,cityCallBack,url);
        getCityInfoThread.start();
    }

    public void GetOneNowCallBack(WeatherCallback nowWeatherCallback) throws SecurityException{
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.WEATHER_NOW.getURL(),requestParameters);
        HttpUtil.GetNowWeatherThread getNowWeatherThread = new HttpUtil.GetNowWeatherThread(context,nowWeatherCallback,url);
        getNowWeatherThread.start();
    }

    public void GetOne3DayCallBack(WeatherCallback weatherCallback) throws SecurityException{
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.WEATHER_3D.getURL(),requestParameters);
        HttpUtil.GetNowWeatherThread getNowWeatherThread = new HttpUtil.GetNowWeatherThread(context,weatherCallback,url);
        getNowWeatherThread.start();
    }

    public void GetOne24HayCallBack(WeatherCallback weatherCallback) throws SecurityException{
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.WEATHER_24H.getURL(),requestParameters);
        HttpUtil.GetNowWeatherThread getNowWeatherThread = new HttpUtil.GetNowWeatherThread(context,weatherCallback,url);
        getNowWeatherThread.start();
    }

    public void GetOneNowWeatherLife(WeatherLifeCallBack weatherLifeCallBack){
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.INDICES_1D.getURL(),lifeRequestParameters);
        HttpUtil.GetLifeThread getLifeThread = new HttpUtil.GetLifeThread(context,weatherLifeCallBack,url);
        getLifeThread.start();

    }

    public void GetOne3DayWeatherLife(WeatherLifeCallBack weatherLifeCallBack){
        String url = UrlUtil.getUrl(UrlEnum.WEATHER_DEV_HTTP.getURL()+UrlEnum.INDICES_3D.getURL(),lifeRequestParameters);
        HttpUtil.GetLifeThread getLifeThread = new HttpUtil.GetLifeThread(context,weatherLifeCallBack,url);
        getLifeThread.start();
    }


    /** 各种生活指数回调，运动、钓鱼等 */
    public static abstract class WeatherLifeCallBack implements GetWeatherLife{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(List<WeatherLifeBean> weatherLifeBeanList) {

        }
    }

    /** 实时、小时、逐天 天气回调 */
    public static abstract class WeatherCallback implements GetWeatherCallBack {
        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onNowSUCCESS(WeatherNowBean weatherNowBean) {

        }

        @Override
        public void onDailySUCCESS(List<WeatherDailyBean> weatherDailyBeanList) {

        }

        @Override
        public void onHourlySUCCESS(List<WeatherNowBean> hourlyWeather) {

        }
    }

    /** 城市信息回调，注意同名城市可能同时多次回调，请做城市信息判断 */
    public static abstract class CityCallBack implements GetCityCallBack{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(CityInfoBean cityInfoBean) {

        }
    }

}
