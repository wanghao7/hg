package com.wanghao.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanghao.hgshop.dao.OrderMapper;
import com.wanghao.hgshop.pojo.Order;
import com.wanghao.hgshop.pojo.OrderDetail;
import com.wanghao.hgshop.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author hp
 *
 */
@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public PageInfo<Order> list(int userId, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Order>(orderMapper.list(userId));
	}

	@Override
	public List<OrderDetail> listDetail(int orderId, int page) {
		// TODO Auto-generated method stub
		return orderMapper.listDetail(orderId);
	}
	
}
