package com.gzx.qweather;

public interface GetCityCallBack {

    void onERROR(String errorCode);
    void onSUCCESS(String id, String name, String adm1, String adm2);

}
