package com.haho.druid.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.haho.druid.entity.DruidQuery;
import com.haho.druid.entity.Filter;

public class SelectQuery implements DruidQuery {
    /**
     * 查询类型，必填
     */
    private String queryType;
    /**
     * 查询数据，相当于table，必填
     */
    private String dataSource;
    /**
     * 查询时间范围，必填
     */
    private String intervals;
    /**
     * 查询结果排序，非必填，没有则默认false
     */
    private Boolean descending;
    /**
     * 过滤器，非必填
     */
    private Filter filter;
    /**
     * 显示的维度列，非必填,没有则显示全部
     */
    private String[] dimensions;
    /**
     * 显示的计算列,非必填，没填则显示全部
     */
    private String[] metrics;
    /**
     * 聚合粒度
     */
    private String granularity;
    /**
     * 分页参数
     */
    private String pagingSpec;
    /**
     * 配置信息
     */
    private List<String> context;

    public SelectQuery() {
        this.context = new ArrayList<>();
        this.queryType = "select";
    }

    @Override
    public String getSendUrl() {
        return DruidQuery.QUERY_URL;
    }

    @Override
    public String getQueryJSON() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"queryType\":\"").append(queryType).append("\",");
        json.append("\"dataSource\":\"").append(dataSource).append("\",");
        json.append("\"granularity\":\"").append(granularity).append("\",");
        if (descending != null) {
            json.append("\"descending\":\"").append(descending).append("\",");
        }
        if (filter != null) {
            json.append("\"filter\":").append(filter.getJSON()).append(",");
        }
        if (dimensions != null && dimensions.length > 0) {
            json.append("\"dimensions\":[");
            for (String dimension : dimensions) {
                json.append(dimension).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("],");
        }
        if (metrics != null && metrics.length > 0) {
            json.append("\"metrics\":[");
            for (String metric : metrics) {
                json.append(metric).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("],");
        }
        json.append("\"pagingSpec\":").append(pagingSpec).append(",");
        if (!CollectionUtils.isEmpty(context)) {
            json.append("\"context\":{");
            for (String contex : context) {
                json.append(contex).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("},");
        }
        json.append("\"intervals\":[\"").append(intervals).append("\"]");
        json.append("}");
        return json.toString();
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(String[] dimensions) {
        this.dimensions = dimensions;
    }

    public String[] getMetrics() {
        return metrics;
    }

    public void setMetrics(String[] metrics) {
        this.metrics = metrics;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getPagingSpec() {
        return pagingSpec;
    }

    public SelectQuery setPagingSpecJSON(String pagingIdentifiersJson, Integer threshold, Boolean fromNext) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"pagingIdentifiers\":");
        json.append(pagingIdentifiersJson).append(",");
        json.append("\"threshold\":").append(threshold).append(",");
        json.append("\"fromNext\":").append(fromNext);
        json.append("}");
        this.pagingSpec = json.toString();
        return this;
    }

    public SelectQuery setPagingSpec(Integer threshold) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"pagingIdentifiers\":");
        json.append("{}").append(",");
        json.append("\"threshold\":").append(threshold).append(",");
        json.append("\"fromNext\":").append(true);
        json.append("}");
        this.pagingSpec = json.toString();
        return this;
    }

    public List<String> getContext() {
        return context;
    }

    public void setContext(List<String> context) {
        this.context = context;
    }

    /** 是否跳过空的值 */
    public void setSkipEmptyBuckets(Boolean bucket) {
        this.context.add("\"skipEmptyBuckets\": \"" + bucket + "\"");
    }

}
