package com.iot.logistics.send.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.iot.logistics.login.entity.User;
import com.iot.logistics.send.entity.Send;
import com.iot.logistics.send.service.SendException;
import com.iot.logistics.send.service.SendService;

@Controller
public class SendController {
	
	@Autowired
	private SendService sendService;
	
	/**
	 * 支付成功
	 */
	@GetMapping("/sendZhiFuSuccess")
	public String zhiFuSuccess(HttpServletRequest request) {
		Send sendSid = (Send)request.getSession().getAttribute("send");
		String sSid = sendSid.getSid();//得到当前订单的编号
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();//得到用户uid
		Send send = null;
		try {
			//根据用户uid和订单状态为1来得到Take对象
			send = sendService.findSendByUid(uid);	

			if(send != null) {
				int state = send.getS_state();
				String sid = send.getSid();
				// 当订单状态为1的时候修改为2
				if (state == 1 && sid.equals(sSid)) {
					sendService.editState(1,2,sid);
					//修改订单转台成功就 转发到success-send
					return "send/success-send";
				}else{
					return "send/send";
				}
			}else{
				return "send/send";
			}
			
		}catch (SendException e) {
			System.out.println(e.getMessage());
			return "redirect:/index.html";
		}
	}
	
	
	/**
	 * 去支付页面的同时生成订单
	 */
	@PostMapping("/addSendOrder")
	public void addSendOrder(Send send,HttpServletRequest request) {
		//设置take的id 
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		Date date = new Date();
		send.setSid(uuid);
		send.setS_date(date);
		send.setS_state(1);
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user != null) {
			String uid = user.getUid();
			send.setUid(uid);
			
			sendService.addSendOrder(send);//添加到数据库
			
			request.getSession().setAttribute("send", send);
		}
	}
	
	/**
	 * 去支付页面
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 * @throws SendException 
	 */
	@GetMapping("/sendToZhiFu")
	@ResponseBody
	public String toZhiFu(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException, SendException {

		/*
		 * 睡眠1秒，给出生成订单时间
		 */
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		 //获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		//alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		String uid = user.getUid();//得到用户uid
		//根据用户uid 得到订单状态为1的id
		Send send = sendService.findSendByUid(uid);
			String sid = send.getSid();
	
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = new String(sid.getBytes("ISO-8859-1"), "UTF-8");
			//付款金额，必填
			String total_amount = new String("0.01".getBytes("ISO-8859-1"), "UTF-8");
			//订单名称，必填
			String subject = new String("寄出快递".getBytes("ISO-8859-1"), "UTF-8");
			//商品描述，可空
			String body = new String("x".getBytes("ISO-8859-1"), "UTF-8");
	
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
					+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");	
	
			//请求
			String result = null;
			result = alipayClient.pageExecute(alipayRequest).getBody();
			//输出
//			response.getWriter().print(result);
			return result;
		}
}
