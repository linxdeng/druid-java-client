package com.haho.druid.entity;

import java.util.List;

import org.springframework.util.CollectionUtils;

public class AggregationQuery {
    /** 查询类型 必填 */
    private String queryType;

    /* 源 必填 */
    private String dataSource;

    /* 粒度 必填 */
    private String granularity;

    /* 区间 必填 */
    private String intervals;

    /* 过滤条件 相关于where */
    private Filter filter;

    /** 聚合列 相关于group by */
    private Aggregator[] aggregations;

    /** 结果聚合 相当于having */
    private PostAggregation[] postAggregations;

    /**
     * 配置信息
     */
    private List<String> context;

    // 解析aggregation,返回string格式
    public String parse() {
        StringBuilder json = new StringBuilder();
        json.append("\"queryType\":\"").append(queryType).append("\",");
        json.append("\"dataSource\":\"").append(dataSource).append("\",");
        json.append("\"granularity\":\"").append(granularity).append("\",");
        if (filter != null) {
            json.append("\"filter\":").append(filter.getJSON()).append(",");
        }
        if (aggregations != null) {
            json.append("\"aggregations\":[");
            for (Aggregator aggregator : aggregations) {
                json.append(aggregator.getJSON()).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("],");
        }
        if (postAggregations != null) {
            json.append("\"postAggregations\":[");
            for (PostAggregation postAggregation : postAggregations) {
                json.append(postAggregation.getJSON()).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("],");
        }
        if (!CollectionUtils.isEmpty(context)) {
            json.append("\"context\":{");
            for (String contex : context) {
                json.append(contex).append(",");
            }
            json.deleteCharAt(json.lastIndexOf(","));
            json.append("},");
        }
        json.append("\"intervals\":[\"").append(intervals).append("\"]");
        return json.toString();
    }


    public String getQueryType() {
        return queryType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Aggregator[] getAggregations() {
        return aggregations;
    }


    public void setAggregations(Aggregator[] aggregations) {
        this.aggregations = aggregations;
    }


    public PostAggregation[] getPostAggregations() {
        return postAggregations;
    }

    public void setPostAggregations(PostAggregation[] postAggregations) {
        this.postAggregations = postAggregations;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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
