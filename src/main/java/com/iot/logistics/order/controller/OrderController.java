package com.iot.logistics.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.logistics.login.entity.User;
import com.iot.logistics.order.service.OrderException;
import com.iot.logistics.order.service.OrderService;
import com.iot.logistics.send.entity.Send;
import com.iot.logistics.take.entity.Take;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 删除取件订单
	 */
	@PostMapping("/deleteSendOrder")
	@ResponseBody
	public boolean deleteSendOrder(@RequestParam String s_id,@RequestParam String state,
			HttpServletRequest request) {
		
		//修改订单状态为4
		orderService.editSendOrderSatet(s_id,state);
		
		return true;
	}
	
	/**
	 * 删除寄件订单
	 */
	@PostMapping("/deleteTakeOrder")
	@ResponseBody
	public boolean deleteTakeOrder(@RequestParam String t_id,@RequestParam String state,
			HttpServletRequest request) {
		
		//修改订单状态为4
		orderService.editTakeOrderSatet(t_id,state);
		
		return true;
	}
	
	
	/**
	 * 查询所有订单
	 * @param request
	 * @return
	 */
	@GetMapping("/queryOrder")
	public String queryOrder(HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user != null) {
			String uid = user.getUid();
			
			try {
				List<Take> takeList = orderService.queryTakePayOrder(2,uid);			
				request.setAttribute("takeList", takeList);			
			} catch (OrderException e) {
				request.setAttribute("msgTake", e.getMessage());
			}
			
			try {
				List<Send> sendList =  orderService.querySendPayOrder(2,uid);
				request.setAttribute("sendList", sendList);
			}catch (OrderException e) {
				request.setAttribute("msgSend", e.getMessage());
			}
		}
		return "order/order";
	}
	
}
