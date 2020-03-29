package com.iot.logistics.send.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iot.logistics.send.entity.Send;

@Mapper
public interface SendMapper {
	
	@Select("select * from t_send where uid = #{uid} and s_state = 1 order by s_date desc limit 1")
	public Send findSendByUid(String uid);

	@Insert("insert into t_send values(#{send.sid},#{send.s_mName},#{send.s_company},"
			+ "#{send.s_mNumber},#{send.s_mAddress},#{send.s_mStreet},#{send.s_uName},"
			+ "#{send.s_uNumber},#{send.s_uAddress},#{send.s_uStreet},#{send.s_date},"
			+ "#{send.uid},#{send.s_state})")
	public void addSendOrder(@Param("send") Send send);
	
	@Update("update t_send set s_state = #{state_2} where s_state = #{state_1} and sid = #{sid}")
	public void editState(@Param("state_1") int state_1, @Param("state_2") int state_2,@Param("sid") String sid);
	
	
	
}
