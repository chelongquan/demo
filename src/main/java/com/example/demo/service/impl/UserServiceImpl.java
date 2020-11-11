/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-04-21 16:26
 *
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 * @company seeyon.com
 * @since design-center-service
 * @author seeyon-chelq
 */
package com.example.demo.service.impl;

import com.example.demo.entity.OrgUser;
import com.example.demo.entity.OrgUserExample;
import com.example.demo.mapper.OrgUserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @date 2020-04-21 16:26
 * @since demo
 * @author seeyon-chelq
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Resource
	private OrgUserMapper orgUserMapper;
	@Value("${server.port}")
	private int port;
	
	@Transactional
	@Override
	public void create(String name) {
		OrgUser u = new OrgUser();
		u.setName(name);
		orgUserMapper.insert(u);
	}
	
	@Override
	public List<OrgUser> query(String name) {
		OrgUserExample example = new OrgUserExample();
		List<OrgUser> result = orgUserMapper.selectByExample(example);
		log.info("port="+port);
//		int a = 1/0;
		return result;
	}
	
}
