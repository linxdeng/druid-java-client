package com.haho.druid.entity;

import org.springframework.util.StringUtils;

public class CardinalityAggregator implements Aggregator {
    /* 聚合类型 */
    private String type;

    /* 聚合后的列名 */
    private String name;

    private String[] fields;

    /** 默认不传是false */
    private String byRow;

    /** 默认不传是false */
    private String round;

    public String[] getFields() {
        return fields;
    }

    public CardinalityAggregator(String name) {
        this.type = "cardinality";
        this.name = name;
    }

    public CardinalityAggregator fields(String... fields) {
        this.fields = fields;
        return this;
    }

    public CardinalityAggregator byRow(String row) {
        this.byRow = row;
        return this;
    }

    public String getByRow() {
        return byRow;
    }

    public String getRound() {
        return round;
    }

    public CardinalityAggregator byRound(String round) {
        this.round = round;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    @Override
    public String getJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"").append(type).append("\",");
        json.append("\"name\":\"").append(name).append("\",");
        if (fields != null && fields.length > 0) {
            json.append("\"fields\":[");
            for (String string : fields) {
                json.append("\"").append(string).append("\",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("],");
        }
        if (!StringUtils.isEmpty(byRow)) {
            json.append("\"byRow\":").append(byRow).append(",");
        }
        if (!StringUtils.isEmpty(round)) {
            json.append("\"round\":").append(round).append(",");
        }
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("}");
        return json.toString();
    }

    @Override
    public String toString() {
        return getJSON();
    }
}
