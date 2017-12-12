package com.combanc.service.common;

import java.util.Set;

public interface RedisClusterHelperApi {

    boolean setKey(String key, String value, Integer expireTime);

    boolean setKey(String key, String value);

    boolean exists(String key);

    long getKeyExpire(String key);

    boolean setKeyExpire(String key, Integer seconds);

    boolean delKey(String key);

    String getKey(String key);

    Long setCount(String key);

    String getCount(String key);

    Long setSet(String key, String value);

    Set<String> getSet(String key);

    void setZero(String key);

}
