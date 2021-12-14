package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherLife;

public interface GetWeatherLife {

    void onERROR(String errorCode);
    void onSUCCESS(WeatherLife weatherLife);

}
