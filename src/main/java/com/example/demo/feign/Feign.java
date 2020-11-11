/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-11-10 20:00
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.example.demo.feign;

import com.example.demo.entity.OrgUser;
import com.example.demo.feign.fallback.FeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @date 2020-11-10 20:00
 * @since demo
 * @author seeyon-chelq
 */
//@FeignClient(url="http://127.0.0.1:8081",value="http://127.0.0.1:8081")
@FeignClient(name="service-config", fallback = FeignFallback.class)
public interface Feign {
	@GetMapping("/home/query")
	List<OrgUser> query(@RequestParam("name") String name);
}
