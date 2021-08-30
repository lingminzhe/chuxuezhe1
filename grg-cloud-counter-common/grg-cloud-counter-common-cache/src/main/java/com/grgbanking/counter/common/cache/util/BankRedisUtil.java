package com.grgbanking.counter.common.cache.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Redis工具类
 *
 * @author MARK xx@grgbanking.com
 */
@Slf4j
public class BankRedisUtil {

    /**
     * 功能描述：模糊通配查询
     *
     * @param
     * @return list ：返回模糊匹配的keys
     * @author Jesson
     * @date 2021/11/28 11:12
     */
    public static List<String> getScanList(int count, String pattern) {
        ScanOptions scanOptions = ScanOptions.scanOptions().match("*"+pattern+"*").count(count).build();
        return getKeys(scanOptions);
    }

    /**
     * 根据条件扫描redis
     * @param options
     * @return
     */
    public static List<String> getKeys(ScanOptions options) {
        List<String> keys = new ArrayList<>();
        Cursor<byte[]> cursor = ((RedisTemplate) SpringUtil.getBean("redisTemplate")).getConnectionFactory().getConnection().scan(options);
        while (cursor.hasNext()) {
            keys.add(new String(cursor.next(), Charset.forName("utf-8")));
        }
        return keys;
    }

    /**
     * 根据条件扫描redis
     * @param pattern
     * @return
     */
    public static List<String> getKeys(String pattern) {
        return getKeys(ScanOptions.scanOptions().match(pattern).build());
    }

    /**
     * 根据前缀模糊匹配
     * @param prex
     * @return
     */
    public static List<String> getPreAList(String prex) {
        return getKeys(ScanOptions.scanOptions().match(prex + "*").build());
    }

    /**
     * 同时模糊匹配前缀和后缀
     * @param count
     * @param prex
     * @param suffix
     * @return
     */
    public static List<String> getPreAndSuffixList(int count, String prex, String suffix) {
        List<String> result = new ArrayList<>();
        List<String> prexList = getKeys(ScanOptions.scanOptions().match(prex + "*").count(count).build());
        if(!CollectionUtils.isEmpty(prexList)){
            prexList.forEach(item ->{
                if (item.endsWith(suffix)) {
                    result.add(item);
                }
            });
        }
        return result;
    }

    /**
     * 功能描述：在前缀筛选结果集中获取目标结果集
     *
     * @param args 为 "且" 关系
     * @return
     * @author Jesson
     * @date 2021/9/4 16:47
     */

    public static Set<String> getKeySet(String condition, String... args) {
        Set<String> result = new HashSet<>();
        ScanOptions options = ScanOptions.scanOptions().match(condition).build();
        List<String> keys = getKeys(options);
        if (!CollectionUtils.isEmpty(keys)) {
            if(args.length <= 0){
                return new HashSet<>(keys);
            }
            keys.forEach(key->{
                for (int i = 0; i < args.length; i++) {
                    Pattern pattern = Pattern.compile(args[i], Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(key);
                    if (!matcher.find()) {
                        break;
                    } else if (args.length - 1 == i) {
                        result.add(key);
                    }
                }
            });
        }
        return result;
    }

    // 参数带上*
    public static void removeAll(String realKey) {
        try {
            List<String> keys = getScanList(1000,realKey);
            ((RedisTemplate) SpringUtil.getBean("redisTemplate")).delete(keys);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }
}
