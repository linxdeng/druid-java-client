package com.haho.druid.load;

public class StreamLoad implements DuidLoad {
    private String dataSource;
    private String context;
    private String sendUrl;

    public StreamLoad(String dataSource, String context) {
        this.sendUrl = "/v1/post/" + dataSource;
        this.dataSource = dataSource;
        this.context = context;
    }

    @Override
    public String getSendUrl() {
        return sendUrl;
    }

    @Override
    public String getLoadJSON() {
        return context;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public StreamLoad setContext(String context) {
        this.context = context;
        return this;
    }
}
