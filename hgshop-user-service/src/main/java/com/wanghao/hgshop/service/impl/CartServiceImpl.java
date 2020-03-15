package com.wanghao.hgshop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanghao.hgshop.dao.CartMapper;
import com.wanghao.hgshop.dao.OrderMapper;
import com.wanghao.hgshop.pojo.Cart;
import com.wanghao.hgshop.pojo.Order;
import com.wanghao.hgshop.pojo.OrderDetail;
import com.wanghao.hgshop.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author hp
 *
 */
@Service(interfaceClass=CartService.class)
public class CartServiceImpl  implements CartService {

	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public int addCart(Integer uid, int skuId, int buyNum) {
		// TODO Auto-generated method stub
		// 增加一个库存数量的一个判断
		Cart cart = new Cart(uid,skuId,buyNum);
		return cartMapper.add(cart);
		
	}

	@Override
	public int delCart(int[] ids) {
		// TODO Auto-generated method stub
		return cartMapper.delete(ids);
	}

	@Override
	public int clearCart(int uid) {
		// TODO Auto-generated method stub
		return cartMapper.clear(uid);
	}

	@Override
	public PageInfo<Cart> list(int uid, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		
		return new PageInfo<Cart>(cartMapper.list(uid));
	}

	@Override
	public int createOrder(Integer uid, String address,int[] cartIds) {
		// TODO Auto-generated method stub
		//先根据购物车id生成主表的数据
		//根据购物车id 查询商品的信息
		List<Cart> cartList  = cartMapper.listByIds(cartIds);
		
		//整个订单的价格
		BigDecimal sumTotal = new BigDecimal(0);
		
		int addNum = 0;
		
		//计算总价格
		for (Cart cart : cartList) {
			sumTotal.add(cart.getSumTotal());
		}
		
		//生成主表的数据
		Order order = new Order();
		//用户
		order.setUid(uid);
		order.setSumtotal(sumTotal);//计算总价格
		order.setAddress(address);//邮寄地址
		
		addNum += orderMapper.add(order);
		
		//生成子表的数据
		for (Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail(order.getOid(),cart);
			//插入子表
			addNum += orderMapper.addDetail(orderDetail);
		}
		
		//从购物车中删除这些数据
		 cartMapper.deleteByIds(cartIds);
		
		
		//成功影响数据的条数
		return addNum;
	}

	

}
