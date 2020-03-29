package com.iot.logistics.querylogistics;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryLogisticsController {
	
	@Autowired
	private QueryLogisticsConfig config;
	
	@PostMapping("/queryLogistics")
	public String queryLogistics(HttpServletRequest request){
		String shipperCode = request.getParameter("shipperCode");//得到快递商编码
		String logisticCode = request.getParameter("logisticCode");//得到运单号
		try {
			String result = config.getOrderTracesByJson(shipperCode, logisticCode);//得到查询结果
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
