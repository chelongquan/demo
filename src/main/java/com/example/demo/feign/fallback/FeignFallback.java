/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-11-10 20:00
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.example.demo.feign.fallback;

import com.example.demo.entity.OrgUser;
import com.example.demo.feign.Feign;
import com.google.common.collect.Lists;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @date 2020-11-10 20:00
 * @since demo
 * @author seeyon-chelq
 */
@Component
public class FeignFallback implements Feign {
	
	@Override
	public List<OrgUser> query(String name) {
		return Lists.newArrayList(new OrgUser(0,"fallback"));
	}
}
