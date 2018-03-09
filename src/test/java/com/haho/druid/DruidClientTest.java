package com.haho.druid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.haho.druid.entity.Aggregator;
import com.haho.druid.entity.AndFilter;
import com.haho.druid.entity.CardinalityAggregator;
import com.haho.druid.entity.DruidResponse;
import com.haho.druid.entity.GranularityEnum;
import com.haho.druid.entity.NotFilter;
import com.haho.druid.entity.SelectorFilter;
import com.haho.druid.entity.SumAggregator;
import com.haho.druid.query.MetadataQuery;
import com.haho.druid.query.SelectQuery;
import com.haho.druid.query.TimeseriesQuery;
import com.haho.druid.query.TopNQuery;
import com.haho.druid.utils.DruidClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidClientTest {

    @Test
    public void testDatasourceQuery() {
        // datasource查询
        MetadataQuery dataSourceMetadataQuery = new MetadataQuery();
        dataSourceMetadataQuery.querySegment().setDataSource("member-consume-statics-test");
        DruidClient druidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse aa = druidClient.execute(dataSourceMetadataQuery);
        System.out.println(aa);
    }

    @Test
    public void testTimeSeriesQuery() {
        // timeSeries查询:查询商户
        TimeseriesQuery timeseriesQuery = new TimeseriesQuery();
        timeseriesQuery.setDataSource("member-consume-statics-test");
        timeseriesQuery.setGranularity(GranularityEnum.ALL.getValue());
        timeseriesQuery.setIntervals("2018-02-26T00:00:00+08:00/2018-02-26T23:59:59+08:00");
        timeseriesQuery.setFilter(new AndFilter().add(new SelectorFilter().dimension("memberId").value("45642442"))
                        .add(new NotFilter().field(new SelectorFilter().dimension("payChannel").value("6"))));
        timeseriesQuery.setAggregations(new Aggregator[] {new SumAggregator("orderNum").fieldName("count").longSum(), new SumAggregator("paySum")
                        .fieldName("payFee").longSum(), new CardinalityAggregator("membercount").fields("memberId")});
        DruidClient druidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse aa = druidClient.execute(timeseriesQuery);
        System.out.println(aa);
    }

    @Test
    public void testTimeSeriesStatQuery() {
        // timeSeries查询：统计汇总日报表
        TimeseriesQuery timeseriesQuery = new TimeseriesQuery();
        timeseriesQuery.setDataSource("member-consume-statics-test");
        timeseriesQuery.setGranularity(GranularityEnum.DAY.getValue());
        timeseriesQuery.setIntervals("2018-02-01T00:00:00+08:00/2018-02-26T23:59:59+08:00");
        timeseriesQuery.setFilter(new AndFilter().add(new SelectorFilter().dimension("shopId").value("451535")));
        timeseriesQuery.setAggregations(new Aggregator[] {new SumAggregator("orderNum").fieldName("count").longSum(), new SumAggregator("paySum")
                        .fieldName("payFee").longSum(), new CardinalityAggregator("membercount").fields("memberId")});
        DruidClient druidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse aa = druidClient.execute(timeseriesQuery);
        System.out.println(aa);
    }

    @Test
    public void testTopNQueryQuery() {
        // topN查询：统计明细日报表
        TopNQuery topNQuery = new TopNQuery();
        topNQuery.setDataSource("member-consume-statics-test");
        topNQuery.setGranularity(GranularityEnum.DAY.getValue());
        topNQuery.setIntervals("2018-02-01T00:00:00+08:00/2018-02-26T23:59:59+08:00");
        topNQuery.setDimension("tradeId");
        topNQuery.setThreshold("3");
        topNQuery.setMetric("membercount");
        topNQuery.setFilter(new AndFilter().add(new SelectorFilter().dimension("shopId").value("451535")));
        topNQuery.setAggregations(new Aggregator[] {new SumAggregator("orderNum").fieldName("count").longSum(), new SumAggregator("paySum")
                        .fieldName("payFee").longSum(), new CardinalityAggregator("membercount").fields("memberId")});
        DruidClient topNDruidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse topNResponse = topNDruidClient.execute(topNQuery);
        System.out.println(topNResponse);
    }

    @Test
    public void testSelectQuery() {
        // select查询：明细报表
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.setDataSource("member-consume-statics-test");
        selectQuery.setGranularity(GranularityEnum.ALL.getValue());
        selectQuery.setIntervals("1000/3000");
        selectQuery.setPagingSpec(10);
        selectQuery.setFilter(new AndFilter().add(new SelectorFilter().dimension("shopId").value("451535")));
        selectQuery.setSkipEmptyBuckets(true);
        DruidClient selectDruidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse selectResponse = selectDruidClient.execute(selectQuery);
        System.out.println(selectResponse);
    }

}
