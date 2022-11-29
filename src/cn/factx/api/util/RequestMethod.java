package cn.factx.api.util;

public enum RequestMethod {
    QUERY("GET","查询"),
    UPDATE("PUT","更新"),
    CREATE("POST","创建"),
    REMOVE("DELETE","删除");

    public final String desc;
    public final String method;

    RequestMethod(String method, String desc) {
        this.method = method;
        this.desc = desc;
    }
}