/**
 * Copyright (c)2017-2020 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author grgbanking
 */
@Slf4j
public class DateUtil {

    /**
     * Date转字符串yyyy-MM-dd
     */
    public static String format(Date date) {
        return format(date, DatePattern.NORM_DATE_PATTERN);
    }

    /**
     * Date转字符串yyyyMMddHHmmss
     */
    public static String getYyyyMMddHHmmssStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        return format(date, DatePattern.PURE_DATETIME_PATTERN);
    }
    /**
     * Date转字符串yyyyMMddHHmmssSS
     */
    public static String getYyyyMMddHHmmssSSStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        return format(date, DatePattern.PURE_DATETIME_MS_PATTERN);
    }
    /**
     * Date转字符串yyyyMMddHHmmss
     */
    public static String getYyyyMMddStr(Date date) {
        if (date == null) {
            date = new Date();
        }
        return format(date, DatePattern.PURE_DATE_PATTERN);
    }

    /**
     * 返回当前时间的yyyyMMddHHmmss字符串
     */
    public static String getYyyyMMddHHmmssStr() {
        return getYyyyMMddHHmmssStr(new Date());
    }

    /**
     * 日期格式化为指定格式
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            date = new Date();
        }
        if (StringUtils.isBlank(pattern)) {
            /**为空时默认赋值为"yyyy-MM-dd HH:mm:ss"*/
            pattern = DatePattern.NORM_DATETIME_PATTERN;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期的格式
     */
    public static Date stringToDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("格式化日期出错", e);
        }
        return null;
    }

    public static Date parseDateTime(String dateString) {
        return stringToDate(dateString, DatePattern.NORM_DATETIME_PATTERN);
    }


    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 对日期的【秒钟】进行加/减
     *
     * @param date    日期
     * @param seconds 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将秒转换成天时分秒
     *
     * @param t
     * @return
     */
    public static String parseTimeSeconds(int t) {
        String r = "";
        int day, hour, minute, second;
        if (t >= 86400) //天,
        {
            day = Convert.toInt(t / 86400);
            hour = Convert.toInt((t % 86400) / 3600);
            minute = Convert.toInt((t % 86400 % 3600) / 60);
            second = Convert.toInt(t % 86400 % 3600 % 60);
            r = day + ("天") + hour + ("时") + minute + ("分") + second + ("秒");
        } else if (t >= 3600)//时,
        {
            hour = Convert.toInt(t / 3600);
            minute = Convert.toInt((t % 3600) / 60);
            second = Convert.toInt(t % 3600 % 60);
            r = hour + ("时") + minute + ("分") + second + ("秒");
        } else if (t >= 60)//分
        {
            minute = Convert.toInt(t / 60);
            second = Convert.toInt(t % 60);
            r = minute + ("分") + second + ("秒");
        } else {
            second = Convert.toInt(t);
            r = second + ("秒");
        }
        return r;
    }
    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }
    // ---------------------- add date ----------------------

    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }


    /**
     * 获取当前系统服务器时间，格式为传入参数格式
     *
     * @param datetime_format
     * @return
     */
    public static String getDateTime(String datetime_format) throws Exception {
        SimpleDateFormat time_format = new SimpleDateFormat(datetime_format);
        Calendar cale = Calendar.getInstance();
        return time_format.format(cale.getTime());
    }

    /**
     * 获取现在时间 
     *
     * @return返回短时间格式 yyyy-MM-dd 
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( DatePattern.NORM_DATE_PATTERN);
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = stringToDate(dateString,DatePattern.NORM_DATE_PATTERN);
        return currentTime_2;
    }

    /**
     * 获取时间月份天数 
     *
     * @return返回月份 MM
     */
    public static String getMonthDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat( "MMdd");
        return formatter.format(date);
    }

}
