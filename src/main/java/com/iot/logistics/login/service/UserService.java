package com.iot.logistics.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.logistics.login.entity.User;
import com.iot.logistics.login.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public void userRegist(User from) throws UserException {
		//校验用户名是否存在
		User user = userMapper.findByUsername(from.getUsername());
		if(user != null) throw new UserException("用户名已被注册！");
				
		//插入用户导到数据库
		userMapper.userRegist(from);
	}

	public User userLogin(User from) throws UserException {
		/*
		 * 1.使用form中的username进行查询，得到User
		 * 2.如果返回null，抛出异常
		 * 3.如果不为null，密码进行比较
		 * 4.如果那里都没错就返回user
		 */
		User user = userMapper.findByUsername(from.getUsername());
		if(user == null||!user.getPassword().equals(from.getPassword())) 
			throw new UserException("账号或密码错误!");
		if(user != null&&!user.getPassword().equals(from.getPassword())){
			throw new UserException("密码错误!");
		}
		return user;
	}

}
