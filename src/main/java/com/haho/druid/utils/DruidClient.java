package com.haho.druid.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haho.druid.entity.DruidQuery;
import com.haho.druid.entity.DruidResponse;
import com.haho.druid.load.DuidLoad;

public class DruidClient {
    private static Logger logger = LoggerFactory.getLogger(DruidClient.class);

    private String host;
    private CloseableHttpClient httpClient;

    public DruidResponse execute(DuidLoad duidLoad) {
        return post(host + duidLoad.getSendUrl(), duidLoad.getLoadJSON());
    }

    public DruidResponse execute(DruidQuery duidQuery) {
        return post(host + duidQuery.getSendUrl(), duidQuery.getQueryJSON());
    }

    public DruidClient(String host) {
        this.host = host;
        initHttp();
    }

    // 初始化http工具
    private void initHttp() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置线程数
        httpClientBuilder.setMaxConnTotal(200);
        httpClientBuilder.setMaxConnPerRoute(20);
        httpClient = httpClientBuilder.build();
    }

    // 发送post请求
    private DruidResponse post(String url, String context) {
        DruidResponse druidResponse = new DruidResponse();
        logger.debug("druid send message:host={},context={}", url, context);
        HttpResponse response = null;
        try {
            HttpPost postRequest = new HttpPost(url);
            postRequest.setHeader("Content-type", "application/json");
            // 设置超时时间，默认5s
            RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(5000).build();
            postRequest.setConfig(config);
            StringEntity message = new StringEntity(context);
            postRequest.setEntity(message);
            response = httpClient.execute(postRequest);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                druidResponse.setAttatch(responseString);
            } else {
                logger.warn("druid请求失败，失败状态：{},失败原因：{}", status.getStatusCode(), status.getReasonPhrase());
                druidResponse.error("请求失败：" + status.getStatusCode() + " " + status.getReasonPhrase());
            }
        } catch (Exception e) {
            logger.error("druid请求异常:", e);
            druidResponse.error("系统错误:" + e.getMessage() + e.getStackTrace());
        } finally {
            // 关闭无效连接
            HttpClientUtils.closeQuietly(response);
        }
        return druidResponse;
    }
}
