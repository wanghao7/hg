package com.wanghao.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.dao.CategoryMapper;
import com.wanghao.hgshop.dao.SkuMapper;
import com.wanghao.hgshop.dao.SpuMapper;
import com.wanghao.hgshop.pojo.Brand;
import com.wanghao.hgshop.pojo.Category;
import com.wanghao.hgshop.pojo.Sku;
import com.wanghao.hgshop.pojo.Spec;
import com.wanghao.hgshop.pojo.SpecOption;
import com.wanghao.hgshop.pojo.Spu;
import com.wanghao.hgshop.pojo.SpuVo;
import com.wanghao.hgshop.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	CategoryMapper catMapper;
	
	@Autowired
	SpuMapper spuMapper;
	
	@Autowired
	SkuMapper skuMapper;
	
	@Override
	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return catMapper.addBrand(brand);
	}


	@Override
	public int deleteBrand(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<Brand> listBrand(String firstChar, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Brand>(catMapper.listBrand(firstChar));
	}

	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return catMapper.add(category);
	}

	@Override
	public int updateCategory(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(int id) {
		// TODO Auto-generated method stub
		return catMapper.delete(id);
	}

	@Override
	public PageInfo<Brand> listCategory(String firstChar, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> treeCategory() {
		// TODO Auto-generated method stub
		return catMapper.tree();
	}

	@Override
	public int updateCategory(Category cat) {
		// TODO Auto-generated method stub
		return catMapper.update(cat);
	}

	@Override
	public Brand getBrand(Integer id) {
		// TODO Auto-generated method stub
		return catMapper.getBrand(id);
	}

	@Override
	public int updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return catMapper.updateBrand(brand);
	}


	@Override
	public int del(int[] ids) {
		// TODO Auto-generated method stub
		return catMapper.del(ids);
	}
	
	//spu

	@Override
	public PageInfo<Spu> listSpu(int page, SpuVo vo) {
		PageHelper.startPage(page, 5);
		
		return new PageInfo<Spu>(spuMapper.list(vo));
	}


	@Override
	public int addSpu(Spu spu) {
		// TODO Auto-generated method stub
		return spuMapper.add(spu);
	}


	@Override
	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return catMapper.listBrand("");
	}


	@Override
	public PageInfo<Sku> listSku(int page, Sku sku) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Sku>(skuMapper.list(sku));
	}


	@Override
	public Sku getSku(int id) {
		// TODO Auto-generated method stub
		return skuMapper.get(id);
	}


	@Override
	public Spu getSpu(int id) {
		// TODO Auto-generated method stub
		return spuMapper.findById(id);
	}


	@Override
	public int addSku(Sku sku) {
		// TODO Auto-generated method stub
		//先加主表
		int cnt = skuMapper.addSku(sku);
		List<SpecOption> specs = sku.getSpecs();
		for (SpecOption specOption : specs) {
			cnt += skuMapper.addSkuSpec(sku.getId(),specOption);
		}
		
		return cnt;
	}

	
}
