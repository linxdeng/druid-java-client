druid在这里是一个实时的查询和分析系统，特别适合按照时间进行聚合查询的场景。但是druid的api当前版本只能使用http请求。在使用上不是很方便，本项目对相关的请求接口做了进一步的封装,底层还是使用http请求durid的api.
使用例子：

 // datasource查询
public void testDatasourceQuery() {
        MetadataQuery dataSourceMetadataQuery = new MetadataQuery();
        dataSourceMetadataQuery.querySegment().setDataSource("member-consume-statics-test");
        DruidClient druidClient = new DruidClient("http://10.10.50.225:8082");
        DruidResponse aa = druidClient.execute(dataSourceMetadataQuery);
        System.out.println(aa);
    }

 // timeSeries查询:查询商户
    @Test
    public void testTimeSeriesQuery() {
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

官方文档请参照：http://druid.io/docs/latest/design/
