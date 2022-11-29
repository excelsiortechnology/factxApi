package cn.factx.demo;

import cn.factx.api.NewsApi;

public class Demo {
    protected static final long appId = 0;          //请填写使用者的appId
    protected static final String appKey = "";      //请填写使用者的appkey

    public static void main(String[] args) {
        NewsApi api = new NewsApi(appId, appKey);

        String response = api.rssFeed("user1","2022-10-01",1,10);
        System.out.println("response = " + response);
    }
}
