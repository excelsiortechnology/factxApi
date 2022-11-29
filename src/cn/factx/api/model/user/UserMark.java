package cn.factx.api.model.user;

public enum UserMark {
    view("浏览"),
    share("分享"),

    like("点赞"),
    unlike("取消点赞"),

    downvote("不喜欢"),
    undownvote("取消不喜欢"),

    favorite("收藏"),
    unfavorite("取消收藏");

    public final String desc;

    UserMark(String desc) {
        this.desc = desc;
    }
}