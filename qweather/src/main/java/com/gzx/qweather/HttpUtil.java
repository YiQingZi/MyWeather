package com.gzx.qweather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzx.qweather.Bean.WeatherNow;
import com.gzx.qweather.Constant.BaseBeanEnum;
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
import java.util.Map;

import static com.gzx.qweather.Constant.ResponseCode.*;

public class HttpUtil {

    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, HttpUtil.class.getName());

    public static class GetThread extends Thread{

        private Context context;
        private QWeather.NowWeatherCallback nowWeatherCallback;
        private String url;

        public GetThread(Context context, QWeather.NowWeatherCallback nowWeatherCallback, String url) {
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
                switch (jsonObject.get(BaseBeanEnum.CODE.getKey()).toString()){
                    case SUCCESS:
                        break;
                    case ERROR_KEY_INVALID:
                        nowWeatherCallback.onERROR(ERROR_KEY_INVALID);
                        return;
                    case ERROR_PARAMETER_INVALID:
                        nowWeatherCallback.onERROR(ERROR_PARAMETER_INVALID);
                        return;
                    case ERROR_PERMISSION_DENIED:
                        nowWeatherCallback.onERROR(ERROR_PERMISSION_DENIED);
                        return;
                    case ERROR_QPM:
                        nowWeatherCallback.onERROR(ERROR_QPM);
                        return;
                    case ERROR_SERVER_API:
                        nowWeatherCallback.onERROR(ERROR_SERVER_API);
                        return;
                    case ERROR_SUCCESS_NO_DATA:
                        nowWeatherCallback.onERROR(ERROR_SUCCESS_NO_DATA);
                        return;
                    case ERROR_UPPER_LIMIT:
                        nowWeatherCallback.onERROR(ERROR_UPPER_LIMIT);
                        return;
                    case ERROR_UNKNOWN_AREA:
                        nowWeatherCallback.onERROR(ERROR_UNKNOWN_AREA);
                        return;
                    default:
                        nowWeatherCallback.onERROR(ERROR_UNKNOWN_CODE);
                        return;
                }
            }catch (Exception e){
                // api error 请求结果没有 code key
                HiLog.error(TAG,"JSON.switch.getkey  --->" + e);
                nowWeatherCallback.onERROR(ERROR_SERVER_API);
                return;
            }

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
            try {
                WeatherNow weatherNow = new WeatherNow();
                weatherNow.setTemp(map.get("temp"));
                weatherNow.setText(map.get("text"));
                //todo 添加天气各种信息
                nowWeatherCallback.onSUCCESS(weatherNow);
            }catch (Exception e){
                // api error 实时天气map 缺少相关key
                HiLog.error(TAG,"JSON.map.get --->" + e);
                nowWeatherCallback.onERROR(ERROR_SERVER_API);
            }
        }
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
