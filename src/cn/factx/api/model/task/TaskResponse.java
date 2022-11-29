package cn.factx.api.model.task;

import java.util.List;
import java.util.Map;

public class TaskResponse {
    int code;
    String status;
    String message;
    String time;
    Data data;

    class Data {
        List<TaskInfo> list;
        Map<String, String> extra;
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}