package com.wanghao.hgshop.service;

import com.wanghao.hgshop.pojo.User;

public interface WebUserService {

	//注册
	User register(User user);
	
	//登录
	User login(User user);
}
