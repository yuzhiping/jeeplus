package com.jeeplus.weixin.utils.cache;

import java.util.ArrayList;
import java.util.List;

import com.jeeplus.weixin.utils.SerializeUtil;
import com.jeeplus.weixin.utils.config.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtil {
	
	    protected static Logger logger = Logger.getLogger(RedisUtil.class);  
     
	    //Redis服务器IP  
	    private static String REDIS_IP = Property.getProperty("REDIS_IP");
	      
	    //Redis的端口号  
	    private static String REDIS_PORT = Property.getProperty("REDIS_PORT"); 
	      
	    //访问密码  
 	    private static String AUTH = Property.getProperty("REDIS_PORT","auth");   
	      
	    //可用连接实例的最大数目，默认值为8；  
	    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
	    private static String MAX_ACTIVE = Property.getProperty("MAX_ACTIVE");
	      
	    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。  
	    private static String MAX_IDLE =  Property.getProperty("MAX_IDLE");   
	      
	    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；  
	    private static String MAX_WAIT = Property.getProperty("MAX_WAIT");
	  
	    //超时时间  
	    private static String TIMEOUT = Property.getProperty("TIMEOUT"); 
	      
	    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
	    private static String TEST_ON_BORROW = Property.getProperty("TEST_ON_BORROW");  
 
	    
	    private static JedisPool jedisPool = null;   // 非切片连接池
	      
		private static ShardedJedisPool shardedJedisPool= null;   // 切片连接池
	    
	    /** 
	     * redis过期时间,以秒为单位 
	     */  
	    public final static int EXRP_HOUR = 60*60;          //一小时  
	    public final static int EXRP_DAY = 60*60*24;        //一天  
	    public final static int EXRP_MONTH = 60*60*24*30;   //一个月  
	      
	    /** 
	     * 初始化Redis初始化非切片池    
	     */  
	    private static void initialPool(){  
	        try {  
	            JedisPoolConfig config = new JedisPoolConfig();  
                config.setMaxTotal( Integer.valueOf(MAX_ACTIVE));  
                config.setMaxIdle( Integer.valueOf(MAX_IDLE));  
                config.setMaxWaitMillis( Integer.valueOf(MAX_WAIT));  
                config.setTestOnBorrow(Boolean.valueOf(TEST_ON_BORROW));
	            jedisPool = new JedisPool(config, REDIS_IP, Integer.valueOf(REDIS_PORT),  Integer.valueOf(TIMEOUT));  
	        } catch (Exception e) {  
	            logger.error("First create JedisPool error : "+e);  
	            try{  
	                //如果第一个IP异常，则访问第二个IP  
	                JedisPoolConfig config = new JedisPoolConfig();  
	                config.setMaxTotal( Integer.valueOf(MAX_ACTIVE));  
	                config.setMaxIdle( Integer.valueOf(MAX_IDLE));  
	                config.setMaxWaitMillis( Integer.valueOf(MAX_WAIT));  
	                config.setTestOnBorrow(Boolean.valueOf(TEST_ON_BORROW));  
	                jedisPool = new JedisPool(config, REDIS_IP, Integer.valueOf(REDIS_PORT), Integer.valueOf(TIMEOUT));  
	            }catch(Exception e2){  
	                logger.error("Second create JedisPool error : "+e2);  
	            }  
	        }  
	    }  

		/**
		 * 初始化切片池
		 */
		private static void initialShardedPool() {
			// 池基本配置
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(5);
			config.setTestOnBorrow(false);
			// slave链接
			List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
			shards.add(new JedisShardInfo(REDIS_IP, Integer.valueOf(REDIS_PORT), "master"));

			// 构造池
			shardedJedisPool = new ShardedJedisPool(config, shards);
		}
	      
	    /** 
	     * 在多线程环境同步初始化 
	     */  
	    private static synchronized void poolInit() {  
	        if (jedisPool == null) {    
	            initialPool();  
	        }  
	        if(shardedJedisPool == null){
	        	
	        	initialShardedPool();
	        }
	    }  
	  
	      
	    /** 
	     * 同步获取Jedis实例 
	     * @return Jedis 
	     */  
	    public synchronized static Jedis getJedis() {    
	        if (jedisPool == null) {    
	            poolInit();  
	        }  
	        Jedis jedis = null;  
	        try {    
	            if (jedisPool != null) {    
	                jedis = jedisPool.getResource();   
	            }  
	        } catch (Exception e) {    
	            logger.error("Get jedis error : "+e);  
	        }finally{  
	            returnResource(jedis);  
	        }  
	        return jedis;  
	    }    
	      
	      
	    /** 
	     * 释放jedis资源 
	     * @param jedis 
	     */  
	    public static void returnResource(final Jedis jedis) {  
	        if (jedis != null && jedisPool !=null) {  
	        	   jedisPool.returnResourceObject(jedis);  
	        }  
	    }  
	      
	      
	    /** 
	     * 设置 String 
	     * @param key 
	     * @param value 
	     */  
	    public static void setString(String key ,String value){  
	    	setString(key,value);
	    }  
	      
	    /** 
	     * 设置 过期时间 
	     * @param key 
	     * @param seconds 以秒为单位 
	     * @param value 
	     */  
	    public static void setString(String key ,int seconds,String value){  
	        try {  
	            value = StringUtils.isEmpty(value) ? "" : value;
	            getJedis().setex(key, seconds, value);  
	        } catch (Exception e) {  
	            logger.error("Set keyex error : "+e);  
	        }  
	    }  
 
	    /** 
	     * 获取String值 
	     * @param key 
	     * @return value 
	     */  
	    public static String getString(String key){  
	        if(getJedis() == null || !getJedis().exists(key)){  
	            return null;  
	        }  
	        return getJedis().get(key);  
	    }  
	      
	    /** 
	        * 设置对象 
	        * @param key 
	        * @param obj 
	        */  
	   public static void setObject(String key ,int seconds,Object obj){  
	        try {  
	            obj = obj == null ? new Object():obj;  
	            getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	         
	   
	    /** 
        * 设置对象 
        * @param key 
        * @param obj 
        */  
   public static void setObject(String key ,Object obj){  
        try {  
            obj = obj == null ? new Object():obj;  
            getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
	   
	       /** 
	        * 获取对象 
	        * @param key 
	        * @return Object 
	        */  
	    public static Object getObject(String key){  
	        if(getJedis() == null || !getJedis().exists(key)){  
	            return null;  
	        }  
	        byte[] data = getJedis().get(key.getBytes());  
	        return (Object)SerializeUtil.unserialize(data);  
	    }  
	      
	    /** 
	        * 设置List集合 
	        * @param key 
	        * @param list 
	        */  
	       public static void setList(String key ,List<?> list){  
	        try {  
	              
	            if(list!=null&&list.size()!=0){
	                getJedis().set(key.getBytes(), SerializeUtil.serializeList(list));  
	            }else{//如果list为空,则设置一个空  
	                getJedis().set(key.getBytes(), "".getBytes());  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	       /** 
	        * 获取List集合 
	        * @param key 
	        * @return 
	        */  
	    public static List<?> getList(String key){  
	        if(getJedis() == null || !getJedis().exists(key)){  
	            return null;  
	        }  
	        byte[] data = getJedis().get(key.getBytes());  
	        return SerializeUtil.unserializeList(data);  
	    }  
}
