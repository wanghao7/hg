package com.wanghao.hgshop.dao;

import java.util.List;

import com.wanghao.hgshop.pojo.Spu;
import com.wanghao.hgshop.pojo.SpuVo;

public interface SpuMapper {

	int add(Spu spu);

	int deleteSpuBatch(int[] ids);

	int update(Spu spu);

	List<Spu> list(SpuVo vo);

	int delete(int id);
	
	Spu findById(int id);
}
