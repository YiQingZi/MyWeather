package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherNow;

import java.util.List;

public interface GetWeatherCallBack {

    void onERROR(String errorCode);
    void onNowSUCCESS(WeatherNow weatherNow);

    void onDailySUCCESS(List<WeatherNow> dailyWeather);

    void onHourlySUCCESS(List<WeatherNow> hourlyWeather);

}
