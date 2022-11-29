package cn.factx.api.model.user;

public class UserEvent {
    String userId;
    String type;
    String param;
    Long eventTime;

    public UserEvent(String userId, String type, String param, Long eventTime) {
        this.userId = userId;
        this.type = type;
        this.param = param;
        this.eventTime = eventTime;
    }
}