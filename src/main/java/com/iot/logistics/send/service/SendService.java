package com.iot.logistics.send.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.logistics.send.entity.Send;
import com.iot.logistics.send.mapper.SendMapper;

@Service
public class SendService {

	@Autowired
	private SendMapper sendMapper;
	
	/*
	 * 根据用户id查询Send
	 */
	public Send findSendByUid(String uid) throws SendException{
		Send send = sendMapper.findSendByUid(uid);
		if(send == null) throw new SendException("不存在");
		return send;
	}

	/*
	 * 添加订单
	 */
	public void addSendOrder(Send send) {
		sendMapper.addSendOrder(send);
	}
	
	/*
	 * 修改订单状态
	 */
	public void editState(int state_1, int state_2, String sid) {
		sendMapper.editState(state_1,state_2,sid);
	}

}
