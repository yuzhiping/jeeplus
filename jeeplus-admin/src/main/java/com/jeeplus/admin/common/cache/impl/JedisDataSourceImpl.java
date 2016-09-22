package com.jeeplus.admin.common.cache.impl;

import com.jeeplus.admin.common.cache.JedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-09-22 13:27.
 */
@Repository("jedisDS")
public class JedisDataSourceImpl implements JedisDataSource {
    private static final Logger LOG= LoggerFactory.getLogger(JedisDataSourceImpl.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            LOG.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != shardJedis)
                shardJedis.close();
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        shardedJedis.close();
    }

}
