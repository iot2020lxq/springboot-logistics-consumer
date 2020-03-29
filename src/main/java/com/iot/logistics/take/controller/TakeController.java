package com.iot.logistics.take.controller;

import java.io.IOException;
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
import com.iot.logistics.take.entity.Take;
import com.iot.logistics.take.service.TakeException;
import com.iot.logistics.take.service.TakeService;

@Controller
public class TakeController {
	
	@Autowired
	private TakeService takeService;
	
	/**
	 * 支付成功
	 */
	@GetMapping("/takeZhiFuSuccess")
	public String zhiFuSuccess(HttpServletRequest request) {

		Take takeTid = (Take)request.getSession().getAttribute("take");
		String sTid = takeTid.getTid();
	
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();//得到用户uid
		Take take = null;
		try {
			//根据用户uid和订单状态为1得到tid
			take = takeService.findTakeByUid(uid);
			if(take != null) {
				int state = take.getT_state();
				String tid = take.getTid();
				// 当订单状态为1的时候修改为2
				if (state == 1 && sTid.equals(tid)) {
					takeService.editState(1,2,tid);
					//修改订单转台成功就 转发到success-take
					return "take/success-take";
				}else{
					return "take/take";
				}
			}else{
				return "take/take";
			}
		}catch (TakeException e) {
			System.out.println(e.getMessage());
			//出现异常转发到index.jsp
			return "redirect:/index.html";
		}
		
	}
	/**
	 * 去支付的同时，添加订单
	 */
	@PostMapping("/addTakeOrder")
	public void addTakeOrder(Take take,HttpServletRequest request) {
		//得到时间对象
		Date date = new Date();
		//设置take的id 
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		take.setTid(uuid);
		take.setT_date(date);
		take.setT_state(1);
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		if(user != null) {
			String uid = user.getUid();
			take.setUid(uid);
			takeService.addTakeOrder(take);
			
			request.getSession().setAttribute("take", take);//保存take信息，可得到tid
		}
	}
	
	/**
	 * 去支付
	 * @throws AlipayApiException 
	 * @throws TakeException 
	 */
	@GetMapping("/takeToZhiFu")
	@ResponseBody
	public String toZhiFu(HttpServletRequest request,HttpServletResponse response) throws IOException, AlipayApiException, TakeException {
		/*
		 * 睡眠1秒，给出生成订单时间
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		User user = (User) request.getSession().getAttribute("sessionUser");
		/*if(user != null) {*/
		 //获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		//alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		String uid = user.getUid();//得到用户uid得到tid
		Take take = null;
		//根据用户uid 得到订单状态为1的id
		take = takeService.findTakeByUid(uid);
		String tid = take.getTid();

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(tid.getBytes("ISO-8859-1"), "UTF-8");
		//付款金额，必填
		String total_amount = new String("0.01".getBytes("ISO-8859-1"), "UTF-8");
		//订单名称，必填
		String subject = new String("快递取件".getBytes("ISO-8859-1"), "UTF-8");
		//商品描述，可空
		String body = new String("x".getBytes("ISO-8859-1"), "UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
				+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");	

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
			//输出
			//response.getWriter().print(JSON.toJSONString(result));
			return result;
	}
}
