package com.liuy.mproxy.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 
 * @Title: RedisUtil.java
 * @Description: <功能详细描述>
 * @author liuy
 * @date 2017年5月5日下午12:10:38
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisUtil {

	private static RedisTemplate<String, String> redisTemplate;

	/**
	 * 
	 * 获取value
	 * 
	 * @Title: get
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:17:48
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置K/V，有效期为永久
	 * 
	 * @Title: set
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:18:24
	 * @param key
	 * @param value
	 * @see [类、类#方法、类#成员]
	 */
	public static void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 
	 * 设置K/V，有效期单位为秒
	 * 
	 * @Title: set
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:21:50
	 * @param key
	 * @param value
	 * @param second
	 * @see [类、类#方法、类#成员]
	 */
	public static void set(String key, String value, long second) {
		redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
	}

	/**
	 * 有序集合添加
	 * @param key
	 * @param value
	 * @param score
	 */
	public static void zAdd(String key, String value, double score) {
		redisTemplate.opsForZSet().add(key,value,score);
	}

	/**
	 * 有序集合获取
	 * @param key
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<String>> rangeByScore(String key, long l1, long l2){
		ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
		Set<ZSetOperations.TypedTuple<String>> typedTuples = zset.reverseRangeWithScores(key, l1, l2);
		return typedTuples;
	}
	public static Double getScore(String key, Object o){
		ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
		return zset.score(key,o);
	}
	public static void setIincrby(String key, String o,double s){
		ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
		zset.incrementScore(key, o, s);
	}
	/**
	 * 
	 * 删除Key
	 * 
	 * @Title: del
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:22:35
	 * @param key
	 * @see [类、类#方法、类#成员]
	 */
	public static void del(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Key是否存在
	 * 
	 * @Title: exists
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:22:49
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 
	 * 设置有效期，单位秒
	 * 
	 * @Title: expire
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:23:11
	 * @param key
	 * @param second
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Boolean expire(String key, long second) {
		return redisTemplate.expire(key, second, TimeUnit.SECONDS);
	}

	/**
	 * 设置Key有效期截止到具体date
	 * 
	 * @Title: expireAt
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:23:37
	 * @param key
	 * @param date
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	/**
	 * 
	 * 增加值
	 * 
	 * @Title: increment
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:27:58
	 * @param key
	 * @param step
	 *            步长
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Long increment(String key, long step) {
		return redisTemplate.opsForValue().increment(key, step);
	}

	/**
	 * 增加值，步长为1
	 * 
	 * @Title: increment
	 * @Description: <功能详细描述>
	 * @author liuy
	 * @date 2017年5月5日 上午10:29:35
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Long increment(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}

	public void setRedisTemplate(RedisTemplate<String, String> rt) {
		redisTemplate = rt;
	}
}
