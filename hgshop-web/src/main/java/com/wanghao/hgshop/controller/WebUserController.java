package com.wanghao.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.common.HgShopConstant;
import com.wanghao.hgshop.pojo.Cart;
import com.wanghao.hgshop.pojo.Order;
import com.wanghao.hgshop.pojo.SpuEsVo;
import com.wanghao.hgshop.pojo.User;
import com.wanghao.hgshop.service.CartService;
import com.wanghao.hgshop.service.OrderService;
import com.wanghao.hgshop.service.WebUserService;
import com.wanghao.hgshop.utils.ElSearchUtil;

@Controller
@RequestMapping("user")
public class WebUserController {

	@Reference
	WebUserService webUserService;
	
	@Reference
	CartService cartService;
	
	@Reference
	OrderService orderService;
	
	@Autowired
	ElSearchUtil<SpuEsVo> elSearchUtils;
	/**
	 * 进入登录界面
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		
		return "user/login";
	}
	
	/**
	 * 进入注册界面
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		
		return "user/register";
	}

	/**
	 * 接收注册界面提交的数据
	 * @param user
	 * @param m
	 * @return
	 */
	
	@RequestMapping("register")
	public String register(User user,Model m) {
		User u = webUserService.register(user);
		if(u==null) {
			m.addAttribute("err", "注册失败");
			//原路返回
			return "user/register";
		}
		//注册成功则重定向到进入登录界面
		return "redirect:toLogin";
	}
	/**
	 * 接收登录界面的数据
	 * @param user
	 * @param m
	 * @return
	 */
	@RequestMapping("login")
	public String login(User user,Model m,HttpServletRequest request) {
		User u = webUserService.login(user);
		if(u==null) {
			m.addAttribute("err", "账号或密码错误");
			//原路返回
			return "user/login";
		}
		request.getSession().setAttribute(HgShopConstant.USERKEY, u);
		
		//登录成功则重定向到用户列表界面
		return "redirect:/user/home";
	}
	
	
	/**
	 * 加入购物车
	 * @param skuId  
	 * @param buyNum 购买数量
	 * @return
	 */
	@RequestMapping(value="addCart",produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String addCart(HttpServletRequest request,
			int skuId, int buyNum) {
		
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USERKEY);
		if(loginUser==null) {
			return "亲，您尚未登录，不能加入购物车哦";
		}
		
		int result = cartService.addCart(loginUser.getUid(),skuId,buyNum);
		return result>0?"success":"添加失败";
	}
	
	@RequestMapping("home")
	public String home() {
		return "/user/index";
	}
	/**
	 * 进入个人中心
	 * @param request
	 * @return
	 */
	public String home(HttpServletRequest request) {
		return "user/home";
		
	}
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("cartlist")
	public String cartlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page) {
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "您尚未登陆");
			return "error";
		}
		PageInfo<Cart> cartList = cartService.list(loginUser.getUid(), page);
		request.setAttribute("pageInfo", cartList);
		return "user/cartlist";
		
	}
	
	/**
	 * 
	 * @param request
	 * @param cartIds 
	 * @param address  邮寄地址
	 * @return
	 */
	@RequestMapping("addorder")
	@ResponseBody
	public String addorder(HttpServletRequest request,
			@RequestParam("cartIds[]") int[] cartIds,String address){
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "您尚未登陆");
			return "error";
		}
		System.out.println("cartIds is " + cartIds);
		int result = cartService.createOrder(loginUser.getUid(),address, cartIds);
		return result>0?"success":"添加失败";
	}
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("orderlist")
	public String orderlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page) {
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "您尚未登陆");
			return "error";
		}
		PageInfo<Order> list = orderService.list(loginUser.getUid(), page);
		request.setAttribute("pageInfo", list);
		return "user/orderlist";
		
	}
	
}
