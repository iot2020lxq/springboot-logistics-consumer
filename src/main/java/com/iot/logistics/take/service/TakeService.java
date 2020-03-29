package com.iot.logistics.take.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.logistics.take.entity.Take;
import com.iot.logistics.take.mapper.TakeMapper;


@Service
public class TakeService {

	@Autowired
	private TakeMapper takeMapper;
	
	/*
	 * 根据用户id查询take
	 */
	public Take findTakeByUid(String uid) throws TakeException {
		Take take = takeMapper.findTakeByUid(uid);
		if(take == null) throw new TakeException("不存在");
		return take;
	}
	
	/*
	 * 添加订单
	 */
	public void addTakeOrder(Take take) {
		takeMapper.addTakeOrder(take);
	}

	/*
	 * 修改订单状态
	 */
	public void editState(int state_1, int state_2, String tid) {
		takeMapper.editState(state_1,state_2,tid);
	}

	
}
