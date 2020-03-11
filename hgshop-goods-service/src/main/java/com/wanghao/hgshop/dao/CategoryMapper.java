package com.wanghao.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.wanghao.hgshop.pojo.Brand;
import com.wanghao.hgshop.pojo.Category;

/**
 * 
 * @author hp
 *
 */
public interface CategoryMapper {

	 List<Category> tree();

	int add(Category category);

	int update(Category cat);

	int delete(int id);


	List<Brand> listBrand(@Param("firstChar")String firstChar);

	int addBrand(Brand brand);

	Brand getBrand(@Param("id")Integer id);

	Category getCategory(@Param("id")Integer id);
	
	@Update("update hg_brand set name=#{name},first_char=#{firstChar} where id=#{id}")
	int updateBrand(Brand brand);

	int del(int[] ids);
}
