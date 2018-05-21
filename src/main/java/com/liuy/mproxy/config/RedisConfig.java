
package com.liuy.mproxy.config;

import com.liuy.mproxy.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 *
 * redis配置
 * @author liuy
 * @date 2017年5月5日上午11:10:08
 */

@Configuration
public class RedisConfig {
	@Bean
	public RedisUtil cacheHandler(RedisTemplate<String, String> redisTemplate) {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		return redisUtil;
	}
}
