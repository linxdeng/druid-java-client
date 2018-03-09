package com.haho.druid.entity;

import org.springframework.util.StringUtils;

public class SumAggregator implements Aggregator {
    /* 聚合类型 */
    private String type;

    /* 聚合后的列名 */
    private String name;

    /* 聚合列 */
    private String fieldName;

    public SumAggregator(String column) {
        this.name = column;
    }

    public SumAggregator() {

    }

    public SumAggregator longSum() {
        this.type = "longSum";
        return this;
    }

    public SumAggregator doubleSum() {
        this.type = "dobleSum";
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public SumAggregator fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }


    @Override
    public String toString() {
        return getJSON();
    }

    @Override
    public String getJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"").append(type).append("\",");
        if (!StringUtils.isEmpty(name)) {
            name = this.fieldName;
        }
        json.append("\"name\":\"").append(name).append("\",");
        if (!StringUtils.isEmpty(fieldName)) {
            json.append("\"fieldName\":\"").append(fieldName).append("\",");
        }
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("}");
        return json.toString();
    }
}
