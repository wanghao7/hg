package com.wanghao.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.pojo.Spec;

public interface SpecService {

	PageInfo<Spec> list(String name,int page);

	int add(Spec spec);
	
	int update(Spec spec);
	
	int delete(int id);
	
	//查找规格,通过id返回对象,用于回显
	Spec findById(int id);
	
	//批量删除
	int deleteBatch(int[] id);

	List<Spec> listAll();

	
}
