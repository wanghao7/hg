package com.wanghao.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;

import com.wanghao.hgshop.service.UserService;

/**
 * 
 * @author hp
 *
 */
@Service(interfaceClass=UserService.class,version="1.1.1")
public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String userName, String passWord) {
		//返回布尔类型
		return "admin".equals(userName) && "123456".equals(passWord);
		
	}

}
