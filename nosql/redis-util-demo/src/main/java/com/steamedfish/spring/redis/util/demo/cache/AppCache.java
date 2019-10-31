package com.steamedfish.spring.redis.util.demo.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈占用应用服务内存〉
 * 基于caffeine
 * 其他 如需动态指定过期时间 可考虑通过切面@Aspect
 * @author sunzhengyu
 * @create 2019/10/30
 * @since 1.0.0
 */
@Configuration
@EnableCaching
@Slf4j
public class AppCache {

    public static final String TEST_KEY1 = "test1";
    public static final String TEST_KEY2 = "test2";


    private String key1CaffeineSpec = "initialCapacity=5,maximumSize=5,expireAfterWrite=60s";
    private String key2CaffeineSpec = "initialCapacity=10,maximumSize=10,expireAfterWrite=10s";

    @Bean(name = "innerCacheManager")
    public CacheManager localCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        Map<String, String> cacheDefMap = new HashMap<String, String>();
        cacheDefMap.put(TEST_KEY1, key1CaffeineSpec);
        cacheDefMap.put(TEST_KEY2, key2CaffeineSpec);

        List<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for (Map.Entry<String, String> entry : cacheDefMap.entrySet()) {

            CaffeineCache caffeine = new CaffeineCache(entry.getKey(), Caffeine.from(entry.getValue()).removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(@Nullable Object key, @Nullable Object value, @NonNull RemovalCause cause) {
                    log.info("删除Caffeine缓存key:{},删除原因:{}", key, cause);
                }
            }).build());

            caches.add(caffeine);
        }

        cacheManager.setCaches(caches);
        return cacheManager;
    }


    public CacheManager redisCacheManager() {

       // RedisCache redisCache = new RedisCache();

        return null;
    }


}