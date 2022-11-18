package cn.factx.api;

import java.util.ArrayList;
import java.util.List;

public class Api {
    private final long appId;                 //请填写使用者的appId
    private final String appKey;              //请填写使用者的appkey

    public Api(long appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    /**
     * 获取用户关注的信息流
     * @param userId 自定义的用户ID，可以多个
     * @param date 指定日期
     * @param page 页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String rssFeed(String userId,String date,int page,int pageSize) {
        Command command = Command.rssFeed;
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("date", date));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String uri = String.format(command.uri, userId);
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
}
