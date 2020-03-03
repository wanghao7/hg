package com.wanghao.hgshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 后台管理初始界面
 * @author hp
 *
 */
@Controller
public class IndexController {

	/**
	 * 管理员账号验证成功跳转到此方法
	 * @return
	 */
	@RequestMapping({"/","index"})
	public String index() {
		return "index";
	}
}
