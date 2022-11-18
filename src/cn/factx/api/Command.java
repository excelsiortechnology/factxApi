package cn.factx.api;


/**
 * 指令列表
 */
public enum Command {
    //新闻
    rssFeed(RequestMethod.GET, "/api/v2/news/rss/%s", "新闻聚合"),
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