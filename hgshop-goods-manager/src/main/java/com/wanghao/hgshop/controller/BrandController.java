package com.wanghao.hgshop.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.pojo.Brand;
import com.wanghao.hgshop.pojo.Spec;
import com.wanghao.hgshop.service.GoodsService;

@Controller
@RequestMapping("brand")
public class BrandController {

	@Reference
	GoodsService goodsService;
	
	
	@RequestMapping("list")
	public String list(Model m,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="")String name) {
		System.out.println(name);
		PageInfo<Brand> pageInfo = goodsService.listBrand(name, page);
		
		m.addAttribute("pageInfo", pageInfo);
		m.addAttribute("name", name);
		return  "brand/list";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(Brand brand) {
		int add = goodsService.addBrand(brand);
		return add>0?"success":"false";
	}
	@RequestMapping("getBrand")
	@ResponseBody
	public Brand getBrand(Integer id) {
		Brand brand = goodsService.getBrand(id);
		return brand;
	}
	@RequestMapping("update")
	@ResponseBody
	public String update(Brand brand) {
		System.err.println(brand);
		int add = goodsService.updateBrand(brand);
		return add>0?"success":"false";
	}
	
	@RequestMapping("delBrand")
	@ResponseBody
	//(@RequestParam(name="ids[]") int[] ids) {
	public String del(@RequestParam(name="ids[]")int[] ids) {
		
		int add = goodsService.del(ids);
		return add>0?"success":"false";
	}
}
