package com.wanghao.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanghao.hgshop.dao.SpecMapper;
import com.wanghao.hgshop.pojo.Spec;
import com.wanghao.hgshop.pojo.SpecOption;
import com.wanghao.hgshop.service.SpecService;
/**
 * 规格管理
 * @author hp
 *
 */

@Service(interfaceClass=SpecService.class)
public class SpecServiceImpl implements SpecService {

	@Autowired
	SpecMapper specMapper;
	
	@Override
	public PageInfo<Spec> list(String name, int page) {
		
		PageHelper.startPage(page, 5);
		return new PageInfo<Spec>(specMapper.list(name));
	}

	@Override
	public int add(Spec spec) {
		// TODO Auto-generated method stub
		//添加主表
		specMapper.addSpec(spec);
		// 这里才能获取到主键ID
		//添加子表
		List<SpecOption> options = spec.getOptions();
		//
		int n=1;
		for (SpecOption specOption : options) {
			// 设置主表的id
			specOption.setSpecId(spec.getId());
			specMapper.addOption(specOption);
			n++;
		}
		
		// 返回的是影响数据的条数
		return n;
	}

	@Override
	public int update(Spec spec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Spec findById(int id) {
		// TODO Auto-generated method stub
		return specMapper.get(id);
	}

	@Override
	public int deleteBatch(int[] id) {
		// TODO Auto-generated method stub
		//删除详细表
		specMapper.deleteSpecOtionsBatch(id);
		//删除规格表
		specMapper.deleteSpecBatch(id);
		return 1;
	}

	@Override
	public List<Spec> listAll() {
		// TODO Auto-generated method stub
		return specMapper.listAll();
	}

}
