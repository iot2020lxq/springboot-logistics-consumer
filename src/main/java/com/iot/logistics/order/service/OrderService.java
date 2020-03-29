package com.iot.logistics.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.logistics.order.mapper.OrderMapper;
import com.iot.logistics.send.entity.Send;
import com.iot.logistics.take.entity.Take;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	/*
	 * 查询取件的订单
	 */
	public List<Take> queryTakePayOrder(int state, String uid) throws OrderException {
		List<Take> takeList  = orderMapper.findTakePayOrder(state,uid);
		if(takeList.size()==0) throw new OrderException("您还没有订单哟！");
		return takeList;
	}

	/*
	 * 查询寄件的订单
	 */
	public List<Send> querySendPayOrder(int state, String uid) throws OrderException {
		List<Send> sendList = orderMapper.querySendPayOrder(state,uid);
		if(sendList.size()==0) throw new OrderException("您还没有订单哟！");
		return sendList;
	}

	/*
	 * 修改取件订单状态为4
	 */
	public void editTakeOrderSatet(String t_id, String state) {
		
		orderMapper.editTakeOrderSatet(t_id,state);
	}

	public void editSendOrderSatet(String s_id, String state) {
		
		orderMapper.editSendOrderSatet(s_id,state);
	}
	
	
}
