package com.iot.logistics.login.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.iot.logistics.login.entity.User;
import com.iot.logistics.login.service.UserException;
import com.iot.logistics.login.service.UserService;
import com.iot.logistics.login.verify.VerifyCode;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 退出
	 */
	@GetMapping("/userExit")
	public String userExit(HttpServletRequest request) {
		//清除session域数据,重定向到首页
		request.getSession().invalidate();
		return "redirect:/index.html";
	}
	
	/**
	 * 用户登录
	 */
	@PostMapping("/userLogin")
	public String userLogin(User user,HttpServletRequest request) {
		//移除formAtle属性（去除弹出对话框）
		if(request.getAttribute("formAlert")!=null) {
			request.removeAttribute("formAlert");
		}
		try{
			User userLogin = userService.userLogin(user);
			request.getSession().setAttribute("sessionUser", userLogin);		
			return "redirect:/index.html";
		}catch(UserException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("user", user);
			return "login";
		}
	}
	
	/**
	 * 用户注册操作
	 */
	@PostMapping("/userRegist")
	public String userRegist(User user,HttpServletRequest request) {
		Map<String,Object> errors = new HashMap<>();
		//设置用户id 
		String uid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		user.setUid(uid);
		
		//校验用户名
		String username = user.getUsername();
		if(username==null || username.trim().isEmpty()) {
			request.setAttribute("username", "用户名不能为空！");
			errors.put("a", "a");
		}else if(username.length()>6) {
			request.setAttribute("username", "名称长度必须为1~6之间！");
			errors.put("b", "b");
		}
		
		//校验密码
		String password = user.getPassword();
		if(password==null || password.trim().isEmpty()) {
			request.setAttribute("password", "密码不能为空！");
			errors.put("c", "c");
		}else if(password.length()<6 || password.length()>12) {
			request.setAttribute("password", "密码长度必须为6~12之间！");
			errors.put("d", "d");
		}else if(!password.equals(user.getRepassword())) {
			request.setAttribute("repassword", "两次密码不一致！");
			errors.put("e", "e");
		}
		
		//得到session正确验证码
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		
		//校验验证码
		if(user.getVerifyCode()==null || user.getVerifyCode().trim().isEmpty()) {
			request.setAttribute("verifyCode", "验证码不能为空！");
			errors.put("f", "f");
		}else if(!sessionVerifyCode.equalsIgnoreCase(user.getVerifyCode())) {
			request.setAttribute("verifyCode", "验证码错误！");
			errors.put("g", "g");
		}
		
		//判断errors是否为空，不为空说明存在错误，
		if(errors.size() > 0) {
			request.setAttribute("user", user);
			return "regist";
		}
		
		try {
			userService.userRegist(user);
			request.setAttribute("formAlert", user);
			return "login";
		} catch (UserException e) {
			//获取异常信息，保存到request域中
			request.setAttribute("msg", e.getMessage());
			return "regist";
		}
	}
	
	/**
	 * 获取验证码图片
	 */
	@GetMapping("/verifyCodeSrc")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//创建验证码对象
			VerifyCode vc = new VerifyCode();
			//得到验证码图片
			BufferedImage image = vc.getImage();
			//把图片上的文字保存到session中
			request.getSession().setAttribute("session_vcode", vc.getText());
			//把图片响应给客户端
			VerifyCode.output(image, response.getOutputStream());
	}
	
}
