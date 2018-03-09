package com.haho.druid.query;

import com.haho.druid.entity.DruidQuery;

public class MetadataQuery implements DruidQuery {
    private String queryType;
    private String dataSource;
    private String intervals;

    @Override
    public String getSendUrl() {
        return DruidQuery.QUERY_URL;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getQueryJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"queryType\":\"").append(queryType).append("\",");
        json.append("\"dataSource\":\"").append(dataSource).append("\"");
        json.append("}");
        return json.toString();
    }

    public String getQueryType() {
        return queryType;
    }

    public MetadataQuery queryDataSource() {
        this.queryType = "dataSourceMetadata";
        return this;
    }

    public MetadataQuery querySegment() {
        this.queryType = "segmentMetadata";
        return this;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

}
