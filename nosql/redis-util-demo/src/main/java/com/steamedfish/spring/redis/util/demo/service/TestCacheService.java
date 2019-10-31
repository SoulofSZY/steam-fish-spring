package com.steamedfish.spring.redis.util.demo.service;

import com.steamedfish.spring.redis.util.demo.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/10/30
 * @since 1.0.0
 */
@Service
@Slf4j
public class TestCacheService {

    @Cacheable(cacheManager = "innerCacheManager", cacheNames = AppCache.TEST_KEY1, key = "'test1_' + #testId", sync = true)
    public String testInnerApp(String testId) {
        log.info("------非缓存-----------------");
        return "test1";
    }

    @CacheEvict(cacheNames = AppCache.TEST_KEY1)
    public void reloadTest1() {
    }
}