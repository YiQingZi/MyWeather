package com.gzx.qweather;

import com.gzx.qweather.Bean.CityInfoBean;

public interface GetCityCallBack {

    void onERROR(String errorCode);
    void onSUCCESS(CityInfoBean cityInfoBean);

}
