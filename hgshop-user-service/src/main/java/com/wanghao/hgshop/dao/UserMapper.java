package com.wanghao.hgshop.dao;

import com.wanghao.hgshop.pojo.User;

public interface UserMapper {

	User addUser(User user);

	int add(User user);

	User getById(Integer uid);

	User login(User user);

}
