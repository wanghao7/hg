package com.wanghao.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanghao.hgshop.service.UserService;
/**
 * 管理员登录界面后台验证
 * @author hp
 *
 */
@Controller
public class UserController {

	@Reference(timeout=2000,version="1.1.1")
	UserService userService;
	
	@RequestMapping("login")
	public String login(String name,String password) {
		if(userService.login(name, password)) {
			return "redirect:/";//跳转到indexController
		}else {
			return "login";
		}
		
	}
}
