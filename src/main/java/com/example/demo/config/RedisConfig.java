/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-11-13 18:03
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author seeyon-chelq
 * @since demo
 */
@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		//自定义RedisTemplate<String, Object>
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		//Json序列化配置
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//String序列化
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//key采用String序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash采用String序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		//value采用jackson序列化方式
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//hash的value序列化采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	/**
	 * 基于SpringBoot2 对 RedisCacheManager 的自定义配置
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		//设置CacheManager的值序列化方式为json序列化
		RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
		RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		
		//设置默认超过时期是1天
		defaultCacheConfig.entryTtl(Duration.ofDays(1));
		//初始化RedisCacheManager
		return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
	}
}
