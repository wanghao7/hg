package com.wanghao.hgshop.dao;

import java.util.List;

import com.wanghao.hgshop.pojo.Order;
import com.wanghao.hgshop.pojo.OrderDetail;

public interface OrderMapper {

	int addDetail(OrderDetail orderDetail);

	int add(Order order);

	/**
	 * 显示一列订单
	 * @param userId
	 * @return
	 */
	List<Order> list(int userId);

	/**
	 * 显示一个订单的详情
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> listDetail(int orderId);

}
