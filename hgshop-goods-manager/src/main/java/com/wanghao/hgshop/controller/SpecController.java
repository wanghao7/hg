package com.wanghao.hgshop.controller;




import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.pojo.Spec;
import com.wanghao.hgshop.service.SpecService;

@Controller
@RequestMapping("spec")
public class SpecController {

	@Reference
	SpecService specService;
	/**
	 * 列表
	 * @param m
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model m,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="")String name) {
		System.out.println(name);
		PageInfo<Spec> pageInfo = specService.list(name, page);
		
		m.addAttribute("pageInfo", pageInfo);
		m.addAttribute("name", name);
		return  "spec/list";
	}
	/**
	 * 添加
	 * @param spec
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(Spec spec) {
//		System.out.println("spec" + spec);
		//getOptions().removeIf(x->{return x.getOptionName()=null;})
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
//		System.out.println("spec 处理后：" + spec);
		int add = specService.add(spec);
		return add>0?"success":"false";
	}
	/**
	 * 修改
	 * @param request
	 * @param spec
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Spec spec) {
		System.out.println("spec" + spec);
		//System.out.println();
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		System.out.println("spec 处理后：" + spec);
		//调用服务
		int add = specService.update(spec);  
		//return add>0?"success":"false";
		return add>0?"success":"false";
	}
	
	
	/**
	 * 修改回显 和 添加sku的前置条件
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("getSpec")
	@ResponseBody
	public Spec getSpec(HttpServletRequest request, int id){
		return specService.findById(id);
		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("delSpecBatch")
	@ResponseBody
	public String delSpecBatch(@RequestParam(name="ids[]") int[] ids) {
		
		
		int delSpecNum = specService.deleteBatch(ids);
		return delSpecNum>0?"success":"false";
	}
}
