package cn.factx.api;

import cn.factx.api.core.Command;
import cn.factx.api.core.Core;
import cn.factx.api.model.user.UserEvent;
import cn.factx.api.model.user.UserMark;
import cn.factx.api.util.Pair;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsApi {
    private final long appId;                 //请填写使用者的appId
    private final String appKey;              //请填写使用者的appkey

    public NewsApi(long appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    /**
     * 获取用户关注的信息流
     *
     * @param userId   用户ID
     * @param date     指定日期
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String rssFeed(String userId, String date, int page, int pageSize) {
        Command command = Command.rss;
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("date", date));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String uri = String.format(command.uri, userId);
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 按用户兴趣推荐新闻信息流
     *
     * @param userId 用户ID
     * @return
     */
    public String newsFeed(String userId) {
        Command command = Command.newsFeed;
        List<Pair> param = new ArrayList<>();
        String uri = String.format(command.uri, userId);
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 用关键词搜索新闻
     *
     * @param userId   用户ID
     * @param keyword  关键词
     * @param pageSize 每次查询返回的记录数，不填默认20条，最高为100条
     * @param before   查询该时间之前的新闻，如果不填，默认为当前时间
     * @return
     */
    public String findNewsByKeyword(String userId, String keyword, int pageSize, Long before) {
        Command command = Command.findNewsByKeyword;
        String uri = String.format(command.uri, userId);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("keyword", keyword));
        param.add(new Pair("limit", String.valueOf(pageSize)));
        if (before != null) {
            param.add(new Pair("before", String.valueOf(before)));
        }
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 用新闻编码搜索新闻
     *
     * @param userId 用户ID
     * @param newsId 新闻ID
     * @param mode   结果模式。1代表普通新闻，2代表加html颜色标记
     * @return
     */
    public String findNewsById(String userId, long newsId, int mode) {
        Command command = Command.findNewsById;
        String uri = command.uri;
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("newsId", String.valueOf(newsId)));
        param.add(new Pair("userId", userId));
        param.add(new Pair("mode", String.valueOf(mode)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 按股票代码查询最新新闻
     *
     * @param userId   用户ID
     * @param stockId  关键词
     * @param pageSize 每次查询返回的记录数，不填默认20条，最高为100条
     * @return
     */
    public String getNewsByStockIds(String userId, String stockId, int pageSize) {
        Command command = Command.findNewsByStockId;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("stockId", stockId));
        param.add(new Pair("userId", userId));
        param.add(new Pair("limit", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 查询预定义行业
     */
    public String listFavIndustries(String userId) {
        Command command = Command.listFavIndustries;
        List<Pair> param = new ArrayList<>();
        String uri = String.format(command.uri);
        param.add(new Pair("userId", userId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 添加用户关注行业
     */
    public String addFavIndustry(String userId,String industry) {
        Command command = Command.addFavIndustry;
        List<Pair> param = new ArrayList<>();
        String uri = String.format(command.uri);
        param.add(new Pair("userId", userId));
        param.add(new Pair("industry", industry));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 删除用户关注行业
     */
    public String removeFavIndustry(String userId,String industry) {
        Command command = Command.removeFavIndustry;
        List<Pair> param = new ArrayList<>();
        String uri = String.format(command.uri);
        param.add(new Pair("userId", userId));
        param.add(new Pair("industry", industry));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 获取用户关注的用户
     *
     * @param userId 用户ID
     * @return
     */
    public String listFavUsers(String userId) {
        Command command = Command.listFavUsers;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 取消收藏用户
     *
     * @param userId 用户ID
     * @param favUserId   被关注的用户ID
     * @return
     */
    public String addFavUser(String userId, String favUserId) {
        Command command = Command.addFavUser;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("favUserId", favUserId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 取消收藏用户
     *
     * @param userId 用户ID
     * @param favUserId   被关注的用户ID
     * @return
     */
    public String removeFavUser(String userId, String favUserId) {
        Command command = Command.removeFavUser;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("favUserId", favUserId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 检查用户是否已经关注该实体
     *
     * @param userId   用户ID
     * @param entityId 实体编码
     * @return
     */
    public String isFavEntity(String userId, String entityId) {
        Command command = Command.isFavEntity;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("entityId", entityId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 获取用户关注的实体
     *
     * @param userId 用户ID
     * @return
     */
    public String listFavEntities(String userId) {
        Command command = Command.listFavEntities;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 增加关注实体
     *
     * @param userId 用户ID
     * @param entityId   实体ID
     * @return
     */
    public String addFavEntity(String userId, String entityId) {
        Command command = Command.addFavEntity;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("entityId", entityId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 取消关注实体
     *
     * @param userId 用户ID
     * @param entityId   实体ID
     * @return
     */
    public String removeFavEntity(String userId, String entityId) {
        Command command = Command.removeFavEntity;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("entityId", entityId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 按用户关注实体查询最新新闻
     *
     * @param userId   用户ID
     * @param fitler   筛选范围。all:全部，event：只看事件，company：只看企业，people：只看人物
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String getNewsByFavEntity(String userId, String fitler, int page, int pageSize) {
        Command command = Command.findNewsByFavEntity;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("f", fitler));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 按实体编码查询最新新闻
     *
     * @param entityId 实体编码
     * @param userId   用户ID
     * @param period   日期范围。day:1天内，week：1周内，month：1个月，year：1年内
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String getNewsOfEntity(String entityId, String userId, String period, int page, int pageSize) {
        Command command = Command.getNewsOfEntity;
        String uri = String.format(command.uri, entityId);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("t", period));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 按实体编码查询相关实体
     *
     * @param entityId 实体编码
     * @return
     */
    public String getEntityRelation(String entityId) {
        Command command = Command.getRelationOfEntity;
        String uri = String.format(command.uri, entityId);

        List<Pair> param = new ArrayList<>();
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 按实体编码查询每天热度
     *
     * @param entityId 实体编码
     * @return
     */
    public String getEntityDailyCount(String entityId) {
        Command command = Command.getEntityDailyCount;
        String uri = String.format(command.uri, entityId);

        List<Pair> param = new ArrayList<>();
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    public String findUserByPwd(String userId,String password) {
        Command command = Command.findUserByPwd;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("p", password));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    public String isFavUser(String userId1,String userId2) {
        Command command = Command.isFavUser;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId1", userId1));
        param.add(new Pair("userId2", userId2));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    public String findUserBySId(String sId) {
        Command command = Command.findUserBySId;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("sId", sId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    public String submitNewword(String userId, String word) {
        Command command = Command.submitNewword;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("word", word));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 记录用户对新闻对标记
     *
     * @param userId 用户ID
     * @param newsId 新闻ID
     * @param mark   用户动作（点赞、收藏等）
     * @return
     */
    public String addUserEvent(String userId, long newsId, UserMark mark) {
        Command command = Command.addEvent;
        String uri = String.format(command.uri);

        Gson gson = new Gson();
        //其他参数
        List<UserEvent> events = new ArrayList<>();
        List<Pair> param = new ArrayList<>();
        Map<String, String> map = new HashMap();
        map.put("newsId", String.valueOf(newsId));
        events.add(new UserEvent(userId, mark.name(), gson.toJson(map), System.currentTimeMillis()));
        String response = Core.submit(command.method, uri, appId, appKey, param, gson.toJson(events));
        return response;
    }

    /**
     * 查询用户分享新闻
     *
     * @param userId   用户ID
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String listShareNews(String userId, String ownerId, int page, int pageSize) {
        Command command = Command.listShareNews;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("ownerId", ownerId));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 查询用户收藏新闻
     *
     * @param userId   用户ID
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String listFavNews(String userId, int page, int pageSize) {
        Command command = Command.listFavNews;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 查询用户点赞新闻
     *
     * @param userId   用户ID
     * @param page     页数
     * @param pageSize 每页结果条数，最大不超过100条
     * @return
     */
    public String listLikeNews(String userId, int page, int pageSize) {
        Command command = Command.listLikeNews;
        String uri = String.format(command.uri);
        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("p", String.valueOf(page)));
        param.add(new Pair("s", String.valueOf(pageSize)));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

//
//    /**
//     * 收藏实体
//     *
//     * @param userId   用户ID
//     * @param entityId 实体编码
//     * @return
//     */
//    public String addFavoritedEntity(String userId, String entityId) {
//        Command command = Command.addFavEntity;
//        String uri = String.format(command.uri);
//
//        List<Pair> param = new ArrayList<>();
//        param.add(new Pair("userId", userId));
//        param.add(new Pair("entityId", entityId));
//        String response = Core.submit(command.method, uri, appId, appKey, param, null);
//        return response;
//    }
//
//    /**
//     * 取消收藏实体
//     *
//     * @param userId   用户ID
//     * @param entityId 实体编码
//     * @return
//     */
//    public String removeFavEntity(String userId, String entityId) {
//        Command command = Command.removeFavEntity;
//        String uri = String.format(command.uri);
//
//        List<Pair> param = new ArrayList<>();
//        param.add(new Pair("userId", userId));
//        param.add(new Pair("entityId", entityId));
//        String response = Core.submit(command.method, uri, appId, appKey, param, null);
//        return response;
//    }

    /**
     * 获取用户收藏的关键词
     *
     * @param userId 用户ID
     * @return
     */
    public String listFavWord(String userId) {
        Command command = Command.listFavWord;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 收藏关键词
     *
     * @param userId 用户ID
     * @param word   关键词
     * @return
     */
    public String addFavWord(String userId, String word) {
        Command command = Command.addFavWord;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("word", word));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

    /**
     * 取消收藏关键词
     *
     * @param userId 用户ID
     * @param word   关键词
     * @return
     */
    public String removeFavWord(String userId, String word) {
        Command command = Command.removeFavWord;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        param.add(new Pair("userId", userId));
        param.add(new Pair("word", word));
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }
    /**
     * 查询股票榜单
     */
    public String listStockRanks() {
        Command command = Command.listStockRanks;
        String uri = String.format(command.uri);

        List<Pair> param = new ArrayList<>();
        String response = Core.submit(command.method, uri, appId, appKey, param, null);
        return response;
    }

}
