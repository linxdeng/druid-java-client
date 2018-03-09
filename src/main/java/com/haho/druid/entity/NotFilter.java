package com.haho.druid.entity;

public class NotFilter implements Filter {
    private String type;

    private Filter field;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NotFilter() {
        this.type = "not";
    }

    public Filter getField() {
        return field;
    }

    public NotFilter field(Filter field) {
        this.field = field;
        return this;
    }

    @Override
    public String getJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"").append(type).append("\"");
        if (field != null) {
            json.append(",");
            json.append("\"field\":").append(field.getJSON());
        }
        json.append("}");
        return json.toString();
    }

    @Override
    public String toString() {
        return getJSON();
    }
}
