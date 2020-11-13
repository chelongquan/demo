/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-04-21 13:53
 *
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 * @company seeyon.com
 * @since design-center-service
 * @author seeyon-chelq
 */
package com.example.demo.controller;

import com.example.demo.entity.OrgUser;
import com.example.demo.feign.Feign;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 *
 * @dat20-04-21 13:53
 * @since demo
 * @author seeyon-chelq
 *
 *
 */
@RestController
@RequestMapping("/home")
@RefreshScope // 加了这个注解，才能立即刷新配置中心的改变值
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private Feign feign;
	
	@Value("${nacos.value}")
	private String nvalue;
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@GetMapping("/create")
	public Object create(String name) {
		userService.create(name);
		return userService.query(name);
	}
	
	@GetMapping("/query")
	public Object query(String name) {
		return userService.query(name);
	}
	
	/**
	 * 使用feign请求
	 * @param name
	 * @return
	 */
	@GetMapping("/feignQuery")
	public Object feignQuery(String name) {
		return feign.query(name);
	}
	
	/**
	 * 使用feign请求
	 * @return
	 */
	@GetMapping("/getValue")
	public Object getValue() {
		return nvalue;
	}
	
	/**
	 * 使用feign请求
	 * @return
	 */
	@GetMapping("/select")
	public Object select(Integer id) {
		return userService.select(id);
	}
	
	@GetMapping("/redis")
	public void redis(@RequestParam("key") String key, @RequestParam("value") String value) {
		ValueOperations<String,Object> operations = redisTemplate.opsForValue();
		operations.set(key, value,1, TimeUnit.MINUTES);
	}
	@GetMapping("/redisValue")
	public Object redisValue(String key) {
		ValueOperations<String,Object> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}
	
	/**
	 * 测试调用自己走不走缓存
	 * @param cacheflag
	 * @param id
	 * @return
	 */
	@GetMapping("/selectByFlag")
	public Object selectByFlag(String cacheflag,Integer id) {
		return userService.selectByFlag(cacheflag, id);
	}
	
	@GetMapping("/lock")
	public void lock(Integer id) {
		userService.lock(id);
	}
}
