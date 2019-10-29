package com.steamedfish.spring.redis.util.demo.helper;


import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RedisHelper {
    private final StringRedisTemplate stringRedisTemplate;
    private final String keyPrefix;

    /**
     * @param stringRedisTemplate 注入StringRedisTemplate
     * @param keyPrefix           统一的key前缀，自动检测并拼上末尾的冒号":"，老项目用空字符串
     */
    public RedisHelper(StringRedisTemplate stringRedisTemplate, String keyPrefix) {
        this.stringRedisTemplate = stringRedisTemplate;

        this.keyPrefix = StringUtils.isEmpty(keyPrefix) || keyPrefix.endsWith(":") ? keyPrefix : keyPrefix + ":";
    }

    public void set(String key, Object object, long expireSeconds) {
        Assert.notNull(object, "object is null");
        String value = object instanceof String ? (String) object : JSON.toJSONString(object);
        stringRedisTemplate.opsForValue().set(getKey((key)), value, expireSeconds, TimeUnit.SECONDS);
    }

    public void set(String key, Object object, long expire, TimeUnit expireUnit) {
        Assert.notNull(object, "object is null");
        String value = object instanceof String ? (String) object : JSON.toJSONString(object);
        stringRedisTemplate.opsForValue().set(getKey(key), value, expire, expireUnit);
    }

    public void set(String key, Object object) {
        Assert.notNull(object, "object is null");
        String value = object instanceof String ? (String) object : JSON.toJSONString(object);
        stringRedisTemplate.opsForValue().set(getKey(key), value);
    }

    public <T> T get(String key, Class<T> type) {
        String value = stringRedisTemplate.opsForValue().get(getKey(key));
        if (null == value) {
            return null;
        }
        return JSON.parseObject(value, type);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(getKey(key));
    }

    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                stringRedisTemplate.delete(getKey(keys[0]));
            } else {
                List<String> newKeys = new ArrayList<>();
                for (String key : keys) {
                    newKeys.add(getKey(key));
                }
                stringRedisTemplate.delete(newKeys);
            }
        }
    }

    public void delete(List<String> keys) {
        stringRedisTemplate.delete(keys.stream().map(this::getKey).collect(Collectors.toList()));
    }

    public Boolean expire(String key, long expire, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(getKey(key), expire, timeUnit);
    }

    /**
     * 获取key还有多久过期
     *
     * @return 剩余时间，单位：秒
     */
    public Long getTTL(String key) {
        return stringRedisTemplate.getExpire(getKey(key));
    }

    public boolean lock(String key, long expire, TimeUnit expireUnit) {
        key = getKey(key);
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", expire, expireUnit);
        return null != result && result;
    }

    public void unlock(String key) {
        stringRedisTemplate.delete(getKey(key));
    }

    public long increment(String key) {
        Long value = stringRedisTemplate.opsForValue().increment(getKey(key));
        return value == null ? 0 : value;
    }

    public long increment(String key, long increasedNum) {
        Long value = stringRedisTemplate.opsForValue().increment(getKey(key), increasedNum);
        return value == null ? 0 : value;
    }

    public Boolean exists(String key) {
        return stringRedisTemplate.hasKey(getKey(key));
    }

    // HASH
    public void hset(String key, String field, Object object) {
        Assert.notNull(object, "object is null");
        String value = object instanceof String ? (String) object : JSON.toJSONString(object);
        stringRedisTemplate.<String, String>opsForHash().put(getKey(key), field, value);
    }


    public String hget(String key, String field) {
        return stringRedisTemplate.<String, String>opsForHash().get(getKey(key), field);
    }

    public <T> T hget(String key, String field, Class<T> type) {
        String value = stringRedisTemplate.<String, String>opsForHash().get(getKey(key), field);
        if (null == value) {
            return null;
        }
        return JSON.parseObject(value, type);
    }

    public Map<String, String> hgetAll(String key) {
        return stringRedisTemplate.<String, String>opsForHash().entries(getKey(key));
    }

    public <T> Map<String, T> hgetAll(String key, Class<T> type) {
        Map<String, String> map = stringRedisTemplate.<String, String>opsForHash().entries(getKey(key));
        if (null == map) {
            return null;
        }
        Map<String, T> result = new HashMap<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.put(entry.getKey(), JSON.parseObject(entry.getValue(), type));
        }
        return result;
    }

    public void hdel(String key, String... fields) {
        stringRedisTemplate.opsForHash().delete(getKey(key), (Object[]) fields);
    }

    public Long hincr(String key, String field, Long num) {
        Assert.notNull(num, "num is null");
        return stringRedisTemplate.<String, String>opsForHash().increment(getKey(key), field, num);
    }

    // LIST
    public Long llen(String key) {
        return stringRedisTemplate.opsForList().size(getKey(key));
    }

    public String rpop(String key) {
        return stringRedisTemplate.opsForList().rightPop(getKey(key));
    }

    public String brpop(String key, Long expireSeconds) {
        return stringRedisTemplate.opsForList().rightPop(getKey(key), expireSeconds, TimeUnit.SECONDS);
    }

    public Long lpush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(getKey(key), value);
    }

    public Long lpush(String key, String[] values) {
        return stringRedisTemplate.opsForList().leftPushAll(getKey(key), values);
    }

    public List<String> lrange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(getKey(key), start, end);
    }

    public Long rpush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(getKey(key), value);
    }

    public Long rpush(String key, String[] values) {
        return stringRedisTemplate.opsForList().rightPushAll(getKey(key), values);
    }

    // SET
    public Long scard(String key) {
        return stringRedisTemplate.opsForSet().size(getKey(key));
    }

    public Long sadd(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(getKey(key), values);
    }

    public String spop(String key) {
        return stringRedisTemplate.opsForSet().pop(getKey(key));
    }

    // Zset
    public Boolean zadd(String key, String value, Double score) {
        Assert.notNull(key, "key must not be null");
        return stringRedisTemplate.opsForZSet().add(getKey(key), value, score);
    }

    /**
     * 获取指定确定的元素,但不返回分数 对应 {@code ZRANGE key start stop [WITHSCORES]} 命令
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return 区间内的元素
     */
    public Set<String> zrange(String key, long start, long end) {
        Assert.notNull(key, "key must not be null");
        return stringRedisTemplate.opsForZSet().range(getKey(key), start, end);
    }

    /**
     * 获取指定确定的元素,但会返回分数 对应 {@code ZRANGE key start stop [WITHSCORES]} 命令
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return 区间内的元素
     */
    public Set<ZSetOperations.TypedTuple<String>> zrangeWithScores(String key, long start, long end) {
        Assert.notNull(key, "key must not be null");
        return stringRedisTemplate.opsForZSet().rangeWithScores(getKey(key), start, end);
    }


    public Long zremove(String key, String... values) {
        Assert.notNull(key, "key must not be null");
        return stringRedisTemplate.opsForZSet().remove(getKey(key), (Object[]) values);
    }

    private String getKey(String key) {
        return keyPrefix + key;
    }


}
