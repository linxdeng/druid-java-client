package com.haho.druid.query;

import java.util.ArrayList;

import com.haho.druid.entity.AggregationQuery;
import com.haho.druid.entity.DruidQuery;

public class TopNQuery extends AggregationQuery implements DruidQuery {
    /* 必填 */
    private String dimension;

    /** 必填 */
    private String threshold;

    /** 必填 */
    private String metric;

    public TopNQuery() {
        super.setContext(new ArrayList<String>());
        super.setQueryType("topN");
    }

    @Override
    public String getSendUrl() {
        return DruidQuery.QUERY_URL;
    }

    @Override
    public String getQueryJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(super.parse()).append(",");
        json.append("\"dimension\":\"").append(dimension).append("\",");
        json.append("\"threshold\":\"").append(threshold).append("\",");
        json.append("\"metric\":").append(metric).append(",");
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("}");
        return json.toString();
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = "\"" + metric + "\"";
    }

    public void setNumericMetric(String metricName) {
        // 返回的是一个json格式
        this.metric = "{\"type\":\"numeric\",\"metric\":\"" + metricName + "\"}";
    }
}
