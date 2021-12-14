package com.gzx.qweather;

import com.gzx.qweather.Bean.CityInfo;

public interface GetCityCallBack {

    void onERROR(String errorCode);
    void onSUCCESS(CityInfo cityInfo);

}
