package com.wanghao.hgshop.dao;

import java.util.List;

import com.wanghao.hgshop.pojo.Cart;
/**
 * 购物车dao
 * @author hp
 *
 */
public interface CartMapper {

	int delete(int[] ids);

	int add(Cart cart);

	int clear(int uid);

	List<Cart> list(int uid);

	List<Cart> listByIds(int[] cartIds);
	
	//根据购物车数据id 数据
	void deleteByIds(int[] cartIds);

}
