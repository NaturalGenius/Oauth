package com.zhuliang.oauth.util;


import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

     private static final String ZONED_DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        private static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
        private static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
        private static final String TIME_FORMATTER_PATTERN = "HH:mm:ss";

        private static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
                ZONED_DATE_TIME_FORMATTER_PATTERN);
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
                DATE_TIME_FORMATTER_PATTERN);
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
                DATE_FORMATTER_PATTERN);
        private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(
                TIME_FORMATTER_PATTERN);

        private static final long DAY_LONG = 24 * 60 * 60 * 1000;//一天的毫秒数

        /**
         * 根据预设格式 <b>yyyy-MM-dd HH:mm:ss</b> 转换当前日期为{@link String}类型的日期
         *
         * @return {@link String}类型的日期
         */
        public static String now() {
            return LocalDateTime.now().format(DATE_TIME_FORMATTER);
        }

        /**
         * 根据自定义格式转换当前日期为{@link String}类型的日期
         *
         * @param pattern 自定义的日期格式
         * @return {@link String}类型的日期
         */
        public static String now(String pattern) {
            return Instant.now().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(
                    pattern));
        }

        /**
         * 根据预设格式 <b>yyyy-MM-dd</b> 转换当前日期为{@link String}类型的日期
         *
         * @return {@link String}类型的日期
         */
        public static String today() {
            return LocalDate.now().format(DATE_FORMATTER);
        }

        /**
         * 根据自定义格式转换当前日期为{@link String}类型的日期
         *
         * @param pattern 自定义的日期格式
         * @return {@link String}类型的日期
         */
        public static String today(String pattern) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * 根据预设格式 <b>yyyy-MM-dd HH:mm:ss</b> 转换{@link String}类型的日期为{@link Date}类型。
         *
         * @param date {@link Date}类型的日期
         * @return {@link String}类型的日期
         */
        public static String format(Date date) {
            return format(date, DATE_TIME_FORMATTER);
        }

        
        public static String formatDate(Date date) {
            return format(date, DATE_FORMATTER);
        }
        /**
         * 根据自定义格式转换指定日期为{@link String}类型的日期
         *
         * @param date 日期
         * @param pattern 日期格式
         * @return
         */
        public static String format(Date date, String pattern) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return format(date, formatter);
        }

        /**
         * 根据自定义格式转换指定日期的毫秒数为{@link String}类型的日期
         *
         * @param millis 毫秒数
         * @param pattern 日期格式
         * @return {@link String}类型的日期
         */
        public static String format(long millis, String pattern) {
            Date date = new Date(millis);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return format(date, formatter);
        }

        /**
         * 根据指定格式转换指定日期为{@link String}类型的日期
         *
         * @param date 指定的日期
         * @param formatter 日期格式
         * @return {@link String}类型的日期
         */
        public static String format(Date date, DateTimeFormatter formatter) {
            return date.toInstant().atZone(ZoneId.systemDefault()).format(formatter);
        }

        /**
         * 使用预设格式转换 {@link String} 为 {@link Date} 类型， 预设格式有三种，优先级是 yyyy-MM-dd HH:mm:ss > yyyy-MM-dd >
         * HH:mm:ss
         *
         * @param strDate 日期字符串
         * @return {@link Date} 类型的时间
         */
        public static Date parse(String strDate) {
            try {
                return parse(strDate, DATE_TIME_FORMATTER);
            } catch (Exception e) {
                try {
                    return parse(strDate, ZONED_DATE_TIME_FORMATTER);
                } catch (Exception e1) {
                    try {
                        return parse(strDate, DATE_FORMATTER);
                    } catch (Exception e2) {
                        try {
                            return parse(strDate, TIME_FORMATTER);
                        } catch (Exception e3) {
                            throw new IllegalArgumentException("非法的日期格式");
                        }
                    }
                }
            }
        }

        /**
         * 使用自定义的格式转换 {@link String} 为 {@link Date} 类型
         * 
         * @param strDate 日期字符串
         * @param pattern 日期格式
         * @return {@link Date}类型的时间
         */
        public static Date parse(String strDate, String pattern) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return parse(strDate, formatter);
        }

        /**
         * 根据预设格式 <b>yyyy-MM-dd</b> 减去指定天数，并返回{@link String}类型的日期。
         * 
         * @param days 要减去的天数
         * @return {@link String}类型的日期
         */
        public static String minusDays(long days) {
            return LocalDateTime.now().minusDays(days).format(DATE_FORMATTER);
        }

        public static String minusDays(long days, String pattern) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.now().minusDays(days).format(formatter);
        }

        /**
         * 根据预设格式 <b>yyyy-MM-dd</b> 加上指定天数，并返回{@link String}类型的日期。
         * 
         * @param days 要加上的天数
         * @return {@link String}类型的日期
         */
        public static String plusDays(long days) {
            return LocalDateTime.now().plusDays(days).format(DATE_FORMATTER);
        }


        private static Date parse(String strDate, DateTimeFormatter formatter) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(strDate, formatter);
                Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
                return Date.from(instant);
            } catch (Exception e) {
                try {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate, formatter);
                    Instant instant = zonedDateTime.toInstant();
                    return Date.from(instant);
                } catch (Exception e1) {
                    try {
                        LocalDate localDate = LocalDate.parse(strDate, formatter);
                        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                        return Date.from(instant);
                    } catch (Exception e2) {
                        try {
                            LocalTime localTime = LocalTime.parse(strDate, formatter);
                            Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId
                                    .systemDefault()).toInstant();
                            return Date.from(instant);
                        } catch (Exception e3) {
                            throw new IllegalArgumentException("非法的日期格式");
                        }
                    }
                }
            }
        }

        public static Date startOfDay(String strDate) {
            LocalDate localDate = LocalDate.parse(strDate, DATE_FORMATTER);
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }
        public static Date startOfDay(Date date) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }

        public static Date endOfDay(String strDate) {
            LocalDate localDate = LocalDate.parse(strDate, DATE_FORMATTER);
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1)
                    .toInstant();
            return Date.from(instant);
        }
        public static Date endOfDay(Date date) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1)
                    .toInstant();
            return Date.from(instant);
        }

        public static long plusAndGetStartOfDayMillis(long days) {
            return LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant()
                    .toEpochMilli();
        }

        public static long plusAndGetEndOfDayMillis(long days) {
            return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).plusDays(days + 1).minusNanos(1)
                    .toInstant().toEpochMilli();
        }

        public static Date plus(int day) {
            return Date.from(LocalDateTime.now().plusDays(day).atZone(ZoneId.systemDefault()).toInstant());
        }
        public static Date plus(Date date, int day) {
            return Date.from(date2LocalDate(date).plusDays(day).atZone(ZoneId.systemDefault()).toInstant());
        }
        
        public static LocalDateTime date2LocalDate(Date date) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        public static LocalDateTime date2LocalDateTime(Date date) {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }

        /**
         * 获取当月第一天
         * @return
         */
        public static Date getMouthStartDay() {
            return Date.from(LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        /**
         * 计算两个时间相隔多少天
         * @param startTime
         * @param endTime
         * @return
         */
        public static int getDifferDays(Date startTime, Date endTime) {
            long starttime = startOfDay(startTime).getTime();
            long time = startOfDay(endTime).getTime();
            return (int) ((time - starttime) / DAY_LONG);
        }
        
        
        public static Date max(Date date1, Date date2) {
            return date1.after(date2) ? date1:date2;
        }
        public static Date min(Date date1, Date date2) {
            return date1.after(date2) ? date2:date1;
        }
        /**
         * 获取该天是周几
         * @param date
         * @return
         */
        public static String getDayOfWeek(Date date) {
            DayOfWeek dayOfWeek = date2LocalDate(date).getDayOfWeek();
            switch (dayOfWeek) {
            case MONDAY:
                return "星期一";
            case TUESDAY:
                return "星期二";
            case WEDNESDAY:
                return "星期三";
            case THURSDAY:
                return "星期四";
            case FRIDAY:
                return "星期五";
            case SATURDAY:
                return "星期六";
            case SUNDAY:
                return "星期日";
            default:
                return "";
            }
        }
    
}
