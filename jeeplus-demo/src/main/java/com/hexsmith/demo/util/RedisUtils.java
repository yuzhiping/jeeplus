package com.hexsmith.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-27 17:10.
 */
public final class RedisUtils {
    //Redis服务器IP
    private static String ADDRESS="121.42.41.190";

    //Redis的端口号
    private static int PORT = 6379;
    //访问密码
    private static String AUTH = "Joe140103...";
    //可用连接实例的最大数目，默认值为8；

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8.
    private static int MAX_IDLE = 200;
    private static int TIMEOUT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    /**
     * 初始化Jedis连接池
     */
    static {
        try {
            JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(MAX_IDLE);
            jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool=new JedisPool(jedisPoolConfig, ADDRESS, PORT, TIMEOUT,AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取jedis实例
     */
    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool!=null){
                Jedis resource =jedisPool.getResource();
                return resource;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放Jedis资源
     */
    public static void returnResource(final Jedis jedis){
        if(jedis!=null){
            jedisPool.close();
        }
    }

}
