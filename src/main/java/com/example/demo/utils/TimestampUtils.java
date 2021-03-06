package com.example.demo.utils;

import java.time.*;

/**
 * 包含时间戳与LocalDate和LocalDateTime的转换
 *
 * @author
 * @date 2018-08-06 12:10
 */
public final class TimestampUtils {

    public static final ZoneOffset localZoneOffset = ZoneOffset.ofHours(8);

    private TimestampUtils() {

    }

    public static LocalDate timestampToLocalDate(long timestamp) {
        return timestampToLocalDateTime(timestamp).toLocalDate();
    }

    public static long localDateToTimestamp(LocalDate localDate) {
        return localDateTimeToTimestamp(localDate.atStartOfDay());
    }

    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of(localZoneOffset.getId()));
    }

    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(localZoneOffset).toEpochMilli();
    }

    public static LocalDateTime LocalDateToLocalDateTime(LocalDate localDate){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant=localDate.atStartOfDay().atZone(zone).toInstant();
        /*Date date=Date.from(instant);*/
        return LocalDateTime.ofInstant(instant, zone);
    }

}
