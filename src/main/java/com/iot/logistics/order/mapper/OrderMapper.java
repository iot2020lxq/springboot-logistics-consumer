package com.iot.logistics.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iot.logistics.send.entity.Send;
import com.iot.logistics.take.entity.Take;

@Mapper
public interface OrderMapper {
	
	@Select("select * from t_take where t_state=#{state} and uid=#{uid} order by t_date desc")
	List<Take> findTakePayOrder(@Param("state") int state,@Param("uid") String uid);

	@Select("select * from t_send where s_state=#{state} and uid=#{uid} order by s_date desc")
	List<Send> querySendPayOrder(@Param("state") int state,@Param("uid") String uid);

	@Update("update t_take set t_state = #{state} where tid = #{t_id}")
	void editTakeOrderSatet(@Param("t_id") String t_id,@Param("state") String state);

	@Update("update t_send set s_state = #{state} where sid = #{s_id}")
	void editSendOrderSatet(@Param("s_id") String s_id,@Param("state") String state);
	
}
