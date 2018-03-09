package com.haho.druid.entity;

public class SelectorFilter implements Filter {
    private String type;
    private String dimension;
    private String value;

    public SelectorFilter() {
        this.type = "selector";
    }

    public SelectorFilter dimension(String column) {
        this.dimension = column;
        return this;
    }

    public SelectorFilter value(String value) {
        this.value = value;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"").append(type).append("\",");
        json.append("\"dimension\":\"").append(dimension).append("\",");
        json.append("\"value\":\"").append(value).append("\"");
        json.append("}");
        return json.toString();
    }

    @Override
    public String toString() {
        return getJSON();
    }
}
