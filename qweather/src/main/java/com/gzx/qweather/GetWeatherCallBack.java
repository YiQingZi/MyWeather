package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherDailyBean;
import com.gzx.qweather.Bean.WeatherNowBean;

import java.util.List;

public interface GetWeatherCallBack {

    void onERROR(String errorCode);

    void onNowSUCCESS(WeatherNowBean weatherNowBean);

    void onDailySUCCESS(List<WeatherDailyBean> weatherDailyBeanList);

    void onHourlySUCCESS(List<WeatherNowBean> hourlyWeather);

}
