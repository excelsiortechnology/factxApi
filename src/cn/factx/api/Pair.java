package cn.factx.api;

public class Pair {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}