package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchunlin
 * @version V1.0
 * @Title: RedisService
 * @Package com.bootdo.common.redis
 * @Description: TODO
 * @date 2018/12/24 14:50
 */
@Component
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name="stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Resource(name="hashOperations")
    HashOperations<String, String, String> hashOpsStr;

    @Resource(name="listOperations")
    ListOperations<String, String> listOpsStr;


    /**
     * 根据指定key获取String
     * @param key
     * @return
     */
    public String getStr(String key){
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    public void setStr(String key, String val){
        valOpsStr.set(key,val);
    }

    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    public void setStr(String key, String val, long time, TimeUnit timeUnit){
        valOpsStr.set(key, val, time, timeUnit);
//        stringRedisTemplate.expire(key, time, timeUnit);
    }
    /**
     * 删除指定key
     * @param key
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 哈希 添加
     * @param key
     */
    public void hmSet(String key, Map<String,String> map){
        hashOpsStr.putAll(key,map);
    }

    public void hmSet(String key, String column,String value){
        Map map=new HashMap();
        map.put(column, value);
        hashOpsStr.putAll(key,map);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public String hmGet(String key, String hashKey){
        return hashOpsStr.get(key,hashKey);
    }

    public Map<String,String> hmGetAll(String key){
        return hashOpsStr.entries(key);
    }
    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,String v){
        listOpsStr.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<String> lRange(String k, long l, long l1){
        return listOpsStr.range(k,l,l1);
    }
}
