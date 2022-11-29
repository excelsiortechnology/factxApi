package cn.factx.api.core;


import cn.factx.api.util.RequestMethod;

/**
 * 指令列表
 */
public enum Command {
    //新闻
    rss(RequestMethod.QUERY, "/api/v2/news/rss/%s", "按用户订阅内容推荐新闻"),
    newsFeed(RequestMethod.QUERY, "/api/v2/news/feed/%s", "按用户画像推荐新闻"),
    listShareNews(RequestMethod.QUERY, "/api/v2/private/user/mark/share/news", "用户分享新闻"),
    findNewsByKeyword(RequestMethod.QUERY, "/api/v2/news/findByKeyword/%s", "按关键词搜索新闻"),
    findNewsById(RequestMethod.QUERY, "/api/v2/news/findByNewsId", "按新闻编码搜索新闻"),
    findNewsByStockId(RequestMethod.QUERY, "/api/v2/stock/news/findByStockIds", "按股票编码搜索新闻"),
    findNewsByFavEntity(RequestMethod.QUERY, "/api/v2/news/findByFavEntity", "按用户关注实体搜索新闻"),
    getNewsOfEntity(RequestMethod.QUERY, "/api/v2/entity/news/%s", "按编码获取主体新闻"),
    getRelationOfEntity(RequestMethod.QUERY, "/api/v2/entity/relations/%s", "按编码获取主体相关主体"),
    getEntityDailyCount(RequestMethod.QUERY, "/api/v2/entity/count/%s", "按编码获取主体每天数量"),

    //用户操作
    isFavUser(RequestMethod.QUERY, "/api/v2/news/user/isFavUser", "是否关注用户"),
    findUserBySId(RequestMethod.QUERY, "/api/v2/news/users/findBySId", "按sId查询用户"),
    findUserByPwd(RequestMethod.QUERY, "/api/v2/news/users/findByPwd", "按密码登录获取用户信息"),
    submitNewword(RequestMethod.QUERY, "/api/v2/news/newword", "提交关键词"),
    addEvent(RequestMethod.CREATE, "/api/v2/private/event", "提交用户事件"),
    listLikeNews(RequestMethod.QUERY, "/api/v2/private/user/mark/like/news", "用户点赞新闻"),
    listFavNews(RequestMethod.QUERY, "/api/v2/private/user/mark/fav/news", "用户收藏新闻"),
    listFavWord(RequestMethod.QUERY, "/api/v2/private/user/mark/fav/word", "获取收藏的关键词"),
    addFavWord(RequestMethod.CREATE, "/api/v2/private/user/mark/fav/word", "收藏关键词"),
    removeFavWord(RequestMethod.REMOVE, "/api/v2/private/user/mark/fav/word", "取消收藏关键词"),
    listFavIndustries(RequestMethod.QUERY, "/api/v2/private/user/favorite/industry", "查询用户关注行业"),
    addFavIndustry(RequestMethod.CREATE, "/api/v2/private/user/favorite/industry", "添加用户关注行业"),
    removeFavIndustry(RequestMethod.REMOVE, "/api/v2/private/user/favorite/industry", "删除用户关注行业"),
    listFavUsers(RequestMethod.QUERY, "/api/v2/private/user/favorite/user", "获取关注的用户"),
    addFavUser(RequestMethod.CREATE, "/api/v2/private/user/favorite/user", "取消关注用户"),
    removeFavUser(RequestMethod.REMOVE, "/api/v2/private/user/favorite/user", "取消关注用户"),
    isFavEntity(RequestMethod.QUERY, "/api/v2/private/user/favorite/entity", "查询实体是否已经收藏"),
    listFavEntities(RequestMethod.QUERY, "/api/v2/private/user/favorite/entities", "获取关注的实体"),
    addFavEntity(RequestMethod.CREATE, "/api/v2/private/user/favorite/entity", "收藏实体"),
    removeFavEntity(RequestMethod.REMOVE, "/api/v2/private/user/favorite/entity", "取消关注实体"),
    listStockRanks(RequestMethod.QUERY, "/api/v2/news/ranks", "股票榜单"),
    ;

    public final RequestMethod method;
    public final String uri;
    public final String desc;

    Command(RequestMethod method, String uri, String desc) {
        this.method = method;
        this.uri = uri;
        this.desc = desc;
    }
}