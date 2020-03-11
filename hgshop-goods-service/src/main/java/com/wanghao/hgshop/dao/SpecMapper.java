package com.wanghao.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanghao.hgshop.pojo.Spec;
import com.wanghao.hgshop.pojo.SpecOption;

public interface SpecMapper {

	List<Spec> list(@Param("name")String name);

	int addSpec(Spec spec);

	int addOption(SpecOption specOption);

	int updateSpec(Spec spec);

	int deleteSpecOtions(int id);

	int deleteSpec(int id);

	Spec get(int id);

	int deleteSpecOtionsBatch(int[] ids);

	int deleteSpecBatch(int[] ids);

	List<Spec> listAll();
}
