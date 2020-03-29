package com.iot.logistics.take.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iot.logistics.take.entity.Take;


@Mapper
public interface TakeMapper {
	
	@Select("select * from t_take where uid = #{uid} and t_state = 1 order by t_date desc limit 1")
	public Take findTakeByUid(String uid);
	
	@Insert("insert into t_take values(#{take.tid},#{take.t_number},#{take.t_address},#{take.t_msg},#{take.t_name},#{take.t_uNumber},#{take.t_street},#{take.t_uAddress},#{take.t_date},#{take.uid},#{take.t_state})")
	public void addTakeOrder(@Param("take") Take take);

	@Update("update t_take set t_state = #{state_2} where t_state=#{state_1} and tid=#{tid}")
	public void editState(@Param("state_1") int state_1, @Param("state_2") int state_2, @Param("tid") String tid);
	
}
