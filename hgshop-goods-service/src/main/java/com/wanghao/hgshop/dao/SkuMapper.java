package com.wanghao.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanghao.hgshop.pojo.Sku;
import com.wanghao.hgshop.pojo.SpecOption;

public interface SkuMapper {

	List<Sku> list(Sku sku);
	
	Sku get(int id);
	
	// 添加sku
	int addSku(Sku sku);
	//添加对应sku的属性值
	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);

}
