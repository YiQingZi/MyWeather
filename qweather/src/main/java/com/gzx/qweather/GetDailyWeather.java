package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherNow;

public interface GetDailyWeather {

    void onERROR(String errorCode);
    void onSUCCESS(WeatherNow weatherNow);

}