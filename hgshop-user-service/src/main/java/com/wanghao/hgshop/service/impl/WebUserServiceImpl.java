package com.wanghao.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanghao.hgshop.dao.UserMapper;
import com.wanghao.hgshop.pojo.User;
import com.wanghao.hgshop.service.WebUserService;
@Service(interfaceClass=WebUserService.class)
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User register(User user) {
		// 添加明文转换后的密码
		user.setPassword(DigestUtils.md5Hex(user.getUsername()));
		//添加成功
		if(userMapper.add(user)>0) {
			return userMapper.getById(user.getUid());
		}
		return null;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		// 添加明文转换后的密码
		user.setPassword(DigestUtils.md5Hex(user.getUsername()));
		return userMapper.login(user);
	}


}
