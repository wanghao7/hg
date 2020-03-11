package com.wanghao.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.pojo.Brand;
import com.wanghao.hgshop.pojo.Category;
import com.wanghao.hgshop.pojo.Sku;
import com.wanghao.hgshop.pojo.Spu;
import com.wanghao.hgshop.pojo.SpuVo;

/**
 * Dubbo服务接口函数返回值不能为void
 * @author hp
 *
 */
public interface GoodsService {

	int addBrand(Brand brand);
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
	
	int updateCategory(Category cat);
	
	Brand getBrand(Integer id);
	int updateBrand(Brand brand);
	int del(int[] ids);
	
	//spu
	PageInfo<Spu>  listSpu(int page,SpuVo vo);
	int addSpu(Spu spu);
	List<Brand> getAllBrands( );
	//sku
	PageInfo<Sku> listSku(int page, Sku sku);
	Sku getSku(int id);
	Spu getSpu(int spuId);
	int addSku(Sku sku);
}
