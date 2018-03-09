package com.haho.druid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {
    /**
     * 日期转字符串
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
        return null;
    }

    /**
     * 格式化世界标准时间,如："2018-01-01T00:00:00.000Z"
     *
     * @param timestamp
     * @return
     * @date 2018年3月2日
     * @author linxTeng
     */
    public static Date fomateTimestamp(String timestamp) {
        if (timestamp != null) {
            timestamp = timestamp.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            try {
                return format.parse(timestamp);
            } catch (Exception e) {
                // ignore
            }
        }
        return null;
    }
}
