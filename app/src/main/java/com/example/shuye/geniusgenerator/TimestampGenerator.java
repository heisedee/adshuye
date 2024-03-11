package com.example.shuye.geniusgenerator;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampGenerator {

    private static final ZoneId BEIJING_ZONE_ID = ZoneId.of("Asia/Shanghai"); // 北京时区ID
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 日期时间格式

    private TimestampGenerator() {
        // 私有构造函数，防止实例化，因为这是一个工具类
    }

    /**
     * 获取当前时间的毫秒时间戳。
     *
     * @return 当前时间的毫秒时间戳
     */
    public static long getCurrentTimestampMillis() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 将给定的毫秒时间戳转换为北京时间，并格式化为字符串。
     *
     * @param timestampMillis 要转换的毫秒时间戳
     * @return 格式化为"yyyy-MM-dd HH:mm:ss"的北京时间字符串
     */
    public static String formatToBeijingTime(long timestampMillis) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestampMillis), BEIJING_ZONE_ID);
        return zonedDateTime.format(FORMATTER);
    }

    /**
     * 获取当前时间的北京时间表示，精确到秒。
     *
     * @return 当前时间的北京时间字符串表示（"yyyy-MM-dd HH:mm:ss"）
     */
    public static String getCurrentBeijingTime() {
        return formatToBeijingTime(getCurrentTimestampMillis());
    }
}
