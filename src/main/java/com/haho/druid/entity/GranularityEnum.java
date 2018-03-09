package com.haho.druid.entity;

public enum GranularityEnum {
    // , "全部"
    ALL("all"),
    // , "年"
    YEAR("year"),
    // , "月"
    MONTH("month"),
    // , "日"
    DAY("day"),
    // , "星期"
    WEEK("week"),
    // , "小时"
    HOUR("hour"),
    // ,"分钟"
    MINUTE("minute"),
    // , "秒"
    SECOND("second");

    private String value;

    GranularityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
