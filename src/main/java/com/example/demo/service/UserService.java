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
package com.example.demo.service;


import com.example.demo.entity.OrgUser;

import java.util.List;

/**
 *
 * @date 2020-04-21 16:26
 * @since demo
 * @author seeyon-chelq
 */
public interface UserService {
	
	void create(String name);
	
	List<OrgUser> query(String name);
	
	OrgUser select(int id);
	
	OrgUser selectByFlag(String cacheflag,int id);
	
	void lock(int id);
}
