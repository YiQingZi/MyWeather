package com.gzx.qweather.Utils;

import com.gzx.qweather.HttpUtil;
import com.gzx.qweather.SignUtil;
import com.gzx.qweather.Constant.RequestParametersEnum;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Map;

public class UrlUtil {

    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, UrlUtil.class.getName());

    public static String getUrl(String http, Map<String,String> param){
        HiLog.debug(TAG,""+http+","+param.toString());

        if (null == param){
            return null;
        }
        String sign = SignUtil.getSignature(param,param.get(RequestParametersEnum.KEY.getParam()));
        if (null == sign){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(http);
        for (String s : param.keySet()){
            if (!s.equals(RequestParametersEnum.KEY.getParam())){
                stringBuilder.append(s);
                stringBuilder.append("=");
                stringBuilder.append(param.get(s));
                stringBuilder.append("&");
            }
        }
        stringBuilder.append("sign=");
        stringBuilder.append(sign);
        return stringBuilder.toString();
    }
}
