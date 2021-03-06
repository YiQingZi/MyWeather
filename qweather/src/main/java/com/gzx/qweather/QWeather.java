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
     * @param cityId ???????????????????????????id?????????CityCallBack??????
     * @param lang ???????????????????????????
     * @param unit ???????????????
     * @param gzip ??????????????????????????????
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
     * @param city ????????????????????????(116.41,39.92)?????????(??????)?????????/??????(beijing)
     * @param gzip ??????????????????????????????
     * @param number ????????????????????????
     * @param range ??????????????????????????????cn?????????ISO 3166??????
     * @param adm ??????????????????????????????
     * @param lang ???????????????????????????
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


    /** ????????????????????????????????????????????? */
    public static abstract class WeatherLifeCallBack implements GetWeatherLife{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(List<WeatherLifeBean> weatherLifeBeanList) {

        }
    }

    /** ???????????????????????? ???????????? */
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

    /** ?????????????????????????????????????????????????????????????????????????????????????????? */
    public static abstract class CityCallBack implements GetCityCallBack{

        @Override
        public void onERROR(String errorCode) {

        }

        @Override
        public void onSUCCESS(CityInfoBean cityInfoBean) {

        }
    }

}
