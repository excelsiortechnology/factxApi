package cn.factx.api;

public enum RequestMethod {
    GET("查询"),
    PUT("更新"),
    POST("创建"),
    DELETE("删除");

    public final String desc;

    RequestMethod(String desc) {
        this.desc = desc;
    }
}