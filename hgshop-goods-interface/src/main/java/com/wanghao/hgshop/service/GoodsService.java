package com.wanghao.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.pojo.Brand;
import com.wanghao.hgshop.pojo.Category;

/**
 * Dubbo服务接口函数返回值不能为void
 * @author hp
 *
 */
public interface GoodsService {

	int addBrand(Brand brand);
	int updateBrand(int id);
	int deleteBrand(int id);
	/**
	 * 
	 * @param firstChar  品牌的首字母
	 * @param page 页码值
	 * @return
	 */
	PageInfo<Brand> listBrand(String firstChar,int page);
	
	int addCategory(Category category);
	int updateCategory(int id);
	int deleteCategory(int id);
	
	/**
	 * 
	 * @param firstChar  品牌的首字母
	 * @param page 页码值
	 * @return
	 */
	PageInfo<Brand> listCategory(String firstChar,int page);
	
	List<Category> treeCategory();
}
