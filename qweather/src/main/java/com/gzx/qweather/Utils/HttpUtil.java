package com.gzx.qweather.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzx.qweather.Bean.CityInfoBean;
import com.gzx.qweather.Bean.WeatherDailyBean;
import com.gzx.qweather.Bean.WeatherLifeBean;
import com.gzx.qweather.Bean.WeatherNowBean;
import com.gzx.qweather.Constant.BaseBeanEnum;
import com.gzx.qweather.Constant.WeatherDaily;
import com.gzx.qweather.Constant.WeatherLife;
import com.gzx.qweather.QWeather;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.net.NetHandle;
import ohos.net.NetManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.gzx.qweather.Constant.ResponseCode.*;
import static com.gzx.qweather.Constant.WeatherDaily.*;
import static com.gzx.qweather.Constant.WeatherNow.*;
import static com.gzx.qweather.Constant.CityInfo.*;


public class HttpUtil {

    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, HttpUtil.class.getName());

    private static final String isPass = "isPass";
    private static final String pass = "p";
    private static final String fail = "f";
    private static final String isCode = "isCode";


    public static class GetLifeThread extends Thread{

        private final QWeather.WeatherLifeCallBack weatherLifeCallBack;
        private final Context context;
        private final String url;

        public GetLifeThread(Context context, QWeather.WeatherLifeCallBack cityCallBack, String url) {
            super();
            this.weatherLifeCallBack = cityCallBack;
            this.context = context;
            this.url = url;
        }

        @Override
        public void run() {
            super.run();

            String s = doGET(context,url);
            if (null == s || s.equals("")){
                // http 响应码非200
                weatherLifeCallBack.onERROR(ERROR_HTTP_GET);
                return;
            }
            JSONObject jsonObject = null;
            try{
                jsonObject = JSON.parseObject(s);
            }catch (Exception e){
                // api error 请求结果不能转化为json
                HiLog.error(TAG,"JSON.parseObject  --->" + e);
                weatherLifeCallBack.onERROR(ERROR_SERVER_API);
                return;
            }

            try{
                String c = jsonObject.get(BaseBeanEnum.CODE.getKey()).toString();
                Map<String,String> code_map = checkCode(c);
                if (code_map.get(isPass).equals(fail)){
                    weatherLifeCallBack.onERROR(code_map.get(isCode));
                    return;
                }
            }catch (Exception e){
                // api error 请求结果没有 code key
                HiLog.error(TAG,"JSON.checkCode.getkey  --->" + e);
                weatherLifeCallBack.onERROR(ERROR_SERVER_API);
                return;
            }
            Object ob = jsonObject.get(BaseBeanEnum.DAILY.getKey());
            if (!(ob instanceof List)){
                HiLog.error(TAG,"<<<<< !(ob instanceof List) >>>>>");
                weatherLifeCallBack.onERROR(ERROR_SERVER_API);
                return;
            }
            List<Map<String,String>> list = null;

            try{
                list = (List<Map<String,String>>) ob;
            }catch (Exception e){
                // api error 实时天气不能转化为 Map<String, String>
                HiLog.error(TAG,"JSON.list = (List<Map<String,String>>) ob --->" + e);
                weatherLifeCallBack.onERROR(ERROR_SERVER_API);
                return;
            }
            try{
                List<WeatherLifeBean> lifeBeanList = new ArrayList<>();
                for (Map<String,String> map : list){
                    WeatherLifeBean weatherLifeBean = new WeatherLifeBean();
                    weatherLifeBean.setCATEGORY(map.get(WeatherLife.CATEGORY));
                    weatherLifeBean.setDATE(map.get(WeatherLife.DATE));
                    weatherLifeBean.setLEVEL(map.get(WeatherLife.LEVEL));
                    weatherLifeBean.setNAME(map.get(WeatherLife.NAME));
                    weatherLifeBean.setTEXT(map.get(WeatherLife.TEXT));
                    weatherLifeBean.setTYPE(map.get(WeatherLife.TYPE));
                    lifeBeanList.add(weatherLifeBean);
                }
                weatherLifeCallBack.onSUCCESS(lifeBeanList);
            }catch (Exception e){
                // api error 天气指数map 缺少相关key
                HiLog.error(TAG,"JSON.map.get --->" + e);
                weatherLifeCallBack.onERROR(ERROR_SERVER_API);
            }
        }
    }

    public static class GetCityInfoThread extends Thread{

        private final QWeather.CityCallBack cityCallBack;
        private final Context context;
        private final String url;


        public GetCityInfoThread(Context context, QWeather.CityCallBack cityCallBack, String url) {
            super();
            this.cityCallBack = cityCallBack;
            this.context = context;
            this.url = url;
        }

        @Override
        public void run() {
            super.run();

            String s = doGET(context,url);
            if (null == s || s.equals("")){
                // http 响应码非200
                cityCallBack.onERROR(ERROR_HTTP_GET);
                return;
            }
            JSONObject jsonObject = null;
            try{
                jsonObject = JSON.parseObject(s);
            }catch (Exception e){
                // api error 请求结果不能转化为json
                HiLog.error(TAG,"JSON.parseObject  --->" + e);
                cityCallBack.onERROR(ERROR_SERVER_API);
                return;
            }

            try{
                String c = jsonObject.get(BaseBeanEnum.CODE.getKey()).toString();
                Map<String,String> code_map = checkCode(c);
                if (code_map.get(isPass).equals(fail)){
                    cityCallBack.onERROR(code_map.get(isCode));
                    return;
                }
            }catch (Exception e){
                // api error 请求结果没有 code key
                HiLog.error(TAG,"JSON.checkCode.getkey  --->" + e);
                cityCallBack.onERROR(ERROR_SERVER_API);
                return;
            }

            Object ob = jsonObject.get(BaseBeanEnum.LOCATION.getKey());
            if (!(ob instanceof List)){
                HiLog.error(TAG,"<<<<< !(ob instanceof List) >>>>>");
                cityCallBack.onERROR(ERROR_SERVER_API);
                return;
            }

            List<Map<String,String>> list = null;

            try{
                list = (List<Map<String,String>>) ob;
            }catch (Exception e){
                // api error 实时天气不能转化为 Map<String, String>
                HiLog.error(TAG,"JSON.list = (List<Map<String,String>>) ob --->" + e);
                cityCallBack.onERROR(ERROR_SERVER_API);
                return;
            }
            try{
                CityInfoBean cityInfoBean = new CityInfoBean();
                for (Map<String,String> map : list){
                    cityInfoBean.setAdm1(map.get(ADM1));
                    cityInfoBean.setAdm2(map.get(ADM2));
                    cityInfoBean.setCountry(map.get(COUNTRY));
                    cityInfoBean.setFxLink(map.get(FXLINK));
                    cityInfoBean.setId(map.get(ID));
                    cityInfoBean.setIsDst(map.get(ISDST));
                    cityInfoBean.setLat(map.get(LAT));
                    cityInfoBean.setLon(map.get(LON));
                    cityInfoBean.setName(map.get(NAME));
                    cityInfoBean.setRank(map.get(RANK));
                    cityInfoBean.setType(map.get(TYPE));
                    cityInfoBean.setTz(map.get(TZ));
                    cityInfoBean.setUtcOffset(map.get(UTCOFFSET));

                    cityCallBack.onSUCCESS(cityInfoBean);
                }
            }catch (Exception e){
                // api error 实时天气map 缺少相关key
                HiLog.error(TAG,"JSON.map.get --->" + e);
                cityCallBack.onERROR(ERROR_SERVER_API);
            }
        }
    }

    public static class GetNowWeatherThread extends Thread{

        private final Context context;
        private final String url;
        private final QWeather.WeatherCallback nowWeatherCallback;

        public GetNowWeatherThread(Context context, QWeather.WeatherCallback nowWeatherCallback, String url) {
            super();
            this.context = context;
            this.nowWeatherCallback = nowWeatherCallback;
            this.url = url;
        }

        @Override
        public void run() {
            super.run();

            String s = doGET(context,url);
            if (null == s || s.equals("")){
                // http 响应码非200
                nowWeatherCallback.onERROR(ERROR_HTTP_GET);
                return;
            }
            JSONObject jsonObject = null;
            try{
                jsonObject = JSON.parseObject(s);
            }catch (Exception e){
                // api error 请求结果不能转化为json
                HiLog.error(TAG,"JSON.parseObject  --->" + e);
                nowWeatherCallback.onERROR(ERROR_SERVER_API);
                return;
            }
            try{
                String c = jsonObject.get(BaseBeanEnum.CODE.getKey()).toString();
                Map<String,String> code_map = checkCode(c);
                if (code_map.get(isPass).equals(fail)){
                    nowWeatherCallback.onERROR(code_map.get(isCode));
                    return;
                }
            }catch (Exception e){
                // api error 请求结果没有 code key
                HiLog.error(TAG,"JSON.checkCode.getkey  --->" + e);
                nowWeatherCallback.onERROR(ERROR_SERVER_API);
                return;
            }

            //Object ob = jsonObject.get(BaseBeanEnum.NOW.getKey());

            for (String key : jsonObject.keySet()){
                if (key.equals(BaseBeanEnum.NOW.getKey())){
                    Object ob = jsonObject.get(BaseBeanEnum.NOW.getKey());
                    if (!(ob instanceof Map)){
                        HiLog.error(TAG,"<<<<< !(ob instanceof Map) >>>>>");
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                        return;
                    }
                    Map<String , String> map = null;
                    try{
                        map = (Map<String, String>) ob;
                    }catch (Exception e){
                        // api error 实时天气不能转化为 Map<String, String>
                        HiLog.error(TAG,"JSON.map = (Map<String, String>) ob --->" + e);
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                        return;
                    }
                    WeatherNowBean weatherNowBean = getWeatherBean(map);
                    if (weatherNowBean == null){
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                    }else {
                        nowWeatherCallback.onNowSUCCESS(weatherNowBean);
                    }
                    break;
                }else if (key.equals(BaseBeanEnum.DAILY.getKey()) || key.equals(BaseBeanEnum.HOURLY.getKey())){
                    Object ob;
                    boolean flag = false;

                    if (key.equals(BaseBeanEnum.HOURLY.getKey())){
                        ob = jsonObject.get(BaseBeanEnum.HOURLY.getKey());
                        flag = true;
                    }else {
                        ob = jsonObject.get(BaseBeanEnum.DAILY.getKey());
                    }
                    HiLog.debug(TAG,""+ob.toString());
                    if (!(ob instanceof List)){
                        HiLog.error(TAG,"<<<<< !(ob instanceof List) >>>>>");
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                        return;
                    }
                    List<Map<String , String>> list = null;
                    try{
                        list = (List<Map<String , String>> ) ob;
                    }catch (Exception e){
                        // api error 实时天气不能转化为 Map<String, String>
                        HiLog.error(TAG,"JSON.map = (List<Map<String , String>>) ob --->" + e);
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                        return;
                    }

                    if (flag){
                        List<WeatherNowBean> weatherNowBeanList = new ArrayList<>();
                        for (Map<String , String> m : list){
                            WeatherNowBean weatherNowBean = getWeatherBean(m);
                            if (weatherNowBean == null){
                                nowWeatherCallback.onERROR(ERROR_SERVER_API);
                            }else {
                                weatherNowBeanList.add(weatherNowBean);
                            }
                        }
                        nowWeatherCallback.onHourlySUCCESS(weatherNowBeanList);
                    }else {
                        List<WeatherDailyBean> weatherDailyBeanList = new ArrayList<>();
                        for (Map<String , String> m : list){
                            WeatherDailyBean weatherDailyBean = getWeatherDailyBean(m);
                            if (weatherDailyBean == null){
                                nowWeatherCallback.onERROR(ERROR_SERVER_API);
                            }else {
                                weatherDailyBeanList.add(weatherDailyBean);
                            }
                        }
                        nowWeatherCallback.onDailySUCCESS(weatherDailyBeanList);
                    }
                    break;
                }
            }
        }
    }

    private static WeatherDailyBean getWeatherDailyBean(Map<String, String> map){
        try {
            WeatherDailyBean weatherDailyBean = new WeatherDailyBean();
            weatherDailyBean.setCloud(map.get(WeatherDaily.CLOUD));
            weatherDailyBean.setHumidity(map.get(WeatherDaily.HUMIDITY));
            weatherDailyBean.setPrecip(map.get(WeatherDaily.PRECIP));
            weatherDailyBean.setPressure(map.get(WeatherDaily.PRESSURE));
            weatherDailyBean.setVis(map.get(WeatherDaily.VIS));

            weatherDailyBean.setFxDate(map.get(FXDATE));
            weatherDailyBean.setIconDay(map.get(ICONDAY));
            weatherDailyBean.setIconNight(map.get(ICONNIGHT));
            weatherDailyBean.setMoonPhase(map.get(MOONPHASE));
            weatherDailyBean.setMoonPhaseIcon(map.get(MOONPHASEICON));
            weatherDailyBean.setMoonrise(map.get(MOONRISE));
            weatherDailyBean.setMoonset(map.get(MOONSET));
            weatherDailyBean.setSunrise(map.get(SUNRISE));
            weatherDailyBean.setSunset(map.get(SUNSET));
            weatherDailyBean.setTempMax(map.get(TEMPMAX));
            weatherDailyBean.setTempMin(map.get(TEMPMIN));
            weatherDailyBean.setTextDay(map.get(TEXTDAY));
            weatherDailyBean.setTextNight(map.get(TEXTNIGHT));
            weatherDailyBean.setUvIndex(map.get(UVINDEX));
            weatherDailyBean.setWind360Day(map.get(WIND360DAY));
            weatherDailyBean.setWind360Night(map.get(WIND360NIGHT));
            weatherDailyBean.setWindDirDay(map.get(WINDDIRDAY));
            weatherDailyBean.setWindDirNight(map.get(WINDDIRNIGHT));
            weatherDailyBean.setWindScaleDay(map.get(WINDSCALEDAY));
            weatherDailyBean.setWindScaleNight(map.get(WINDSCALENIGHT));
            weatherDailyBean.setWindSpeedDay(map.get(WINDSPEEDDAY));
            weatherDailyBean.setWindSpeedNight(map.get(WINDSPEEDNIGHT));
            return weatherDailyBean;
        }catch (Exception e){
            // api error 实时天气map 缺少相关key
            HiLog.error(TAG,"JSON.map.get --->" + e);
            return null;
        }
    }

    private static WeatherNowBean getWeatherBean(Map<String, String> map){
        try {
            WeatherNowBean weatherNowBean = new WeatherNowBean();
            weatherNowBean.setTemp(map.get(TEMP));
            weatherNowBean.setText(map.get(TEXT));
            weatherNowBean.setCloud(map.get(com.gzx.qweather.Constant.WeatherNow.CLOUD));
            weatherNowBean.setDew(DEW);
            weatherNowBean.setFeelsLike(FEELS_LIKE);
            weatherNowBean.setHumidity(com.gzx.qweather.Constant.WeatherNow.HUMIDITY);
            weatherNowBean.setIcon(ICON);
            weatherNowBean.setObsTime(OBS_TIME);
            weatherNowBean.setPrecip(com.gzx.qweather.Constant.WeatherNow.PRECIP);
            weatherNowBean.setPressure(com.gzx.qweather.Constant.WeatherNow.PRESSURE);
            weatherNowBean.setVis(com.gzx.qweather.Constant.WeatherNow.VIS);
            weatherNowBean.setWind360(WIND360);
            weatherNowBean.setWindSpeed(WIND_SPEED);
            weatherNowBean.setWindScale(WIND_SCALE);
            weatherNowBean.setWindDir(WIND_DIR);
            return weatherNowBean;
        }catch (Exception e){
            // api error 实时天气map 缺少相关key
            HiLog.error(TAG,"JSON.map.get --->" + e);
            return null;
        }
    }

    private static Map<String,String> checkCode(String code){
        Map<String,String> map = new HashMap<>();
        map.put(isPass,fail);
        switch (code){
            case SUCCESS:
                map.put(isPass,pass);
                break;
            case ERROR_KEY_INVALID:
                map.put(isCode,ERROR_KEY_INVALID);
                return map;
            case ERROR_PARAMETER_INVALID:
                map.put(isCode,ERROR_PARAMETER_INVALID);
                return map;
            case ERROR_PERMISSION_DENIED:
                map.put(isCode,ERROR_PERMISSION_DENIED);
                return map;
            case ERROR_QPM:
                map.put(isCode,ERROR_QPM);
                return map;
            case ERROR_SERVER_API:
                map.put(isCode,ERROR_SERVER_API);
                return map;
            case ERROR_SUCCESS_NO_DATA:
                map.put(isCode,ERROR_SUCCESS_NO_DATA);
                return map;
            case ERROR_UPPER_LIMIT:
                map.put(isCode,ERROR_UPPER_LIMIT);
                return map;
            case ERROR_UNKNOWN_AREA:
                map.put(isCode,ERROR_UNKNOWN_AREA);
                return map;
            default:
                map.put(isCode,ERROR_UNKNOWN_CODE);
                return map;
        }
        return map;
    }

    public static String doGET(Context context, String httpUrl){

        //TODO HTTP GET

        // 鸿蒙设备 判断网络是否链接
        HiLog.debug(TAG,"httpUrl --> "+httpUrl);

        NetManager netManager = NetManager.getInstance(context);
        if (!netManager.hasDefaultNet()) {
            return null;
        }
        // 获取 鸿蒙设备连接管理
        NetHandle netHandle = netManager.getDefaultNet();

        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            //打开链接
            URLConnection urlConnection = netHandle.openConnection(url,
                    java.net.Proxy.NO_PROXY);
            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            connection = (HttpURLConnection) urlConnection;
            connection.setRequestMethod("GET");
            connection.setReadTimeout(6000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            HiLog.error(TAG,"Http error  ---> "+e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection){
                connection.disconnect();
            }
        }
        String re = result.toString();
        HiLog.debug(TAG,"http get result ----> " + re);
        return re;
    }
}
