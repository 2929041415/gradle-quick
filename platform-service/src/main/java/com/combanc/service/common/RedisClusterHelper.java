package com.combanc.service.common;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Set;

/**
 * redis操作工具类
 *
 * @author leijie
 */
@Component(value = "redisClusterHelper")
public class RedisClusterHelper implements RedisClusterHelperApi {

    @Resource
    private JedisCluster redisCluster;

    /**
     * 存储key 带时间
     *
     * @param key
     * @param value
     */
    @Override
    public boolean setKey(String key, String value, Integer expireTime) {
        redisCluster.set(key, value);
        redisCluster.expire(key, expireTime);
        return redisCluster.exists(key);
    }

    /**
     * 存储key
     *
     * @param key
     * @param value
     */
    @Override
    public boolean setKey(String key, String value) {
        redisCluster.set(key, value);
        return redisCluster.exists(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        return redisCluster.exists(key);
    }

    /**
     * 查询key的过期时间
     *
     * @param key
     * @return
     */
    @Override
    public long getKeyExpire(String key) {
        return redisCluster.ttl(key);
    }

    /**
     * 查询key的过期时间
     *
     * @param key
     * @return
     */
    @Override
    public boolean setKeyExpire(String key, Integer seconds) {
        redisCluster.expire(key, seconds);
        return redisCluster.exists(key);
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    @Override
    public boolean delKey(String key) {
        redisCluster.del(key);
        return redisCluster.exists(key);
    }

    /**
     * 获取key的值
     *
     * @param key
     * @return
     */
    @Override
    public String getKey(String key) {
        return redisCluster.get(key);
    }

    /**
     * 设置自增数
     *
     * @param key
     * @return
     */
    @Override
    public Long setCount(String key) {
        return redisCluster.incr(key);
    }

    /**
     * 获取自增数
     *
     * @param key
     * @return
     */
    @Override
    public String getCount(String key) {
        return redisCluster.get(key);
    }

    /**
     * 存储set
     *
     * @param key
     * @param value
     */
    @Override
    public Long setSet(String key, String value) {
        return redisCluster.sadd(key, value);
    }

    /**
     * 返回set
     *
     * @param key
     */
    @Override
    public Set<String> getSet(String key) {
        return redisCluster.smembers(key);
    }

    /**
     * 将计数器置零
     *
     * @param key
     */
    @Override
    public void setZero(String key) {
        redisCluster.getSet(key, "0");
    }
}
