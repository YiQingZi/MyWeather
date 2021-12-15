package com.gzx.qweather;

import com.gzx.qweather.Bean.WeatherLifeBean;

import java.util.List;

public interface GetWeatherLife {

    void onERROR(String errorCode);
    void onSUCCESS(List<WeatherLifeBean> weatherLifeBeanList);

}
