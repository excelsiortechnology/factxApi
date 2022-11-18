package cn.factx.demo;

import cn.factx.api.Api;

public class Demo {
    protected static final long appId = 0;                                          //请填写使用者的appId
    protected static final String appKey = "";      //请填写使用者的appkey

    public static void main(String[] args) {
        Api api = new Api(appId, appKey);

        String response = api.rssFeed("user1","2022-10-01",1,10);
        System.out.println("response = " + response);
    }
}
