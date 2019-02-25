package com.mask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mask.service.impl.OrderServiceImpl;

@Component
public class RedisLock {

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 加锁
	 * @param key
	 * @param value 当前时间+超时时间
	 * @return
	 */
	public boolean lock(String key, String value) {
		if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
			return true;
		}
		String currentValue = redisTemplate.opsForValue().get(key);
		// 如果锁过期
		if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
			// 获取上一个锁的时间
			String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
			if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
				return true;
			}
		}

		return false;
	}

	public void unlock(String key, String value) {
		try {
			String currentValue = redisTemplate.opsForValue().get(key);
			if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
				redisTemplate.opsForValue().getOperations().delete(key);
			}
		} catch (Exception e) {
			log.error("【redis分布式锁】解锁异常，{}", e);
		}
	}
}
