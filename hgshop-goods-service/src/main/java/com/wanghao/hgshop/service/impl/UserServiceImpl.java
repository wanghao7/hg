package com.wanghao.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanghao.hgshop.config.AdminProperties;
import com.wanghao.hgshop.service.UserService;

/**
 * 
 * @author hp
 *
 */
@Service(interfaceClass=UserService.class,version="1.1.1")
public class UserServiceImpl implements UserService{

	@Autowired
	AdminProperties adminProperties;
	
	
	/**
	 * 后台登录
	 */
	@Override
	public boolean login(String userName, String passWord) {
		//返回布尔类型
		return (adminProperties.getAdminName().equals(userName) 
				&& adminProperties.getPassword().equals(passWord));
		
	}

}
