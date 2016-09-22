package com.jeeplus.admin.common.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-09-22 13:46.
 */
public interface JedisDataSource {

    /**
     * 取得redis的客户端
     * @return
     */
    ShardedJedis getRedisClient();

    /**
     * 将资源返还给pool
     * @param shardedJedis
     */
    void returnResource(ShardedJedis shardedJedis);

    /**
     * 出现异常后，将资源返还给pool
     * @param shardedJedis
     * @param broken
     */
    void returnResource(ShardedJedis shardedJedis,boolean broken);


}
