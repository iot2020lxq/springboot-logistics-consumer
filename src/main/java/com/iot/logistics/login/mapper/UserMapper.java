package com.iot.logistics.login.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.iot.logistics.login.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("select uid,username,password from t_user where username=#{username}")
	User findByUsername(String username);

	@Insert("insert into t_user values(#{from.uid},#{from.username},#{from.password})")
	void userRegist(@Param("from") User from);
	
}
