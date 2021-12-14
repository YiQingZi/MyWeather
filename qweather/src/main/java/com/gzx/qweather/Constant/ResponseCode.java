package com.gzx.qweather.Constant;

public class ResponseCode {

    public final static String SUCCESS = "200";   //请求成功
    public final static String ERROR_SUCCESS_NO_DATA = "204";   //请求成功，但你查询的地区暂时没有你需要的数据。
    public final static String ERROR_PARAMETER_INVALID = "400";   //请求错误，可能包含错误的请求参数或缺少必选的请求参数。
    public final static String ERROR_KEY_INVALID = "401";   //认证失败，可能使用了错误的KEY、数字签名错误、KEY的类型错误（如使用SDK的KEY去访问Web API）。
    public final static String ERROR_UPPER_LIMIT = "402";   //超过访问次数或余额不足以支持继续访问服务，你可以充值、升级访问量或等待访问量重置。
    public final static String ERROR_PERMISSION_DENIED = "403";   //无访问权限，可能是绑定的PackageName、BundleID、域名IP地址不一致，或者是需要额外付费的数据。
    public final static String ERROR_UNKNOWN_AREA = "404";   //查询的数据或地区不存在。
    public final static String ERROR_QPM = "429";   //超过限定的QPM（每分钟访问次数），请参考QPM说明
    public final static String ERROR_SERVER_API = "500";   //无响应或超时，接口服务异常请联系我们
    public final static String ERROR_HTTP_GET = "-1";   //http get 失败
    public final static String ERROR_UNKNOWN_CODE = "-2";   //未知 code

}
