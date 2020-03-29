package com.iot.logistics.take.controller;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101700708741";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChAZhXErHWvJ2JFy5lBQuaygF7XvzRd2vyRqHN2JkoUIRHdLoLHV4rnh0RIpjyKOUR87lfoz1wEXK7SULldoAcs7hyLYqANFQaOKLplNIxDJXjh7v+ON+7nVpjxO5hdNu2vgmdx1Qj8FW01Um/Gd/qFYKFb47vK+vlPzujkJZMau0GETB20HMr6nfwxktJexDp9HniljLmILfCsAqN4lS70p0eOSnFhvlXvwFIz7jQetwuIkkQtYVHy6HaKXAwcCJnWjepAr8h9McdWZS0ReUIM24E3t0gvP7Ls8tZJfpZuGmJeJx0XFvGnv5gSb5R+E8XhFOXNaQVql3UcvQLyCFPAgMBAAECggEAAXbWP9R3sZA9joWbcme9V0cHBGw0uGLxpF5oEksp+WfiDBRPrNIzjQn/EazS30Dn9GXXBbuiT2aZOILODPAq8hfgUYmAfPH84dAqCx3DW5bVtiAB4an6vuYLcGTWFiTN6reHttudNEZ3QRG/ffFqS+KuMTB2iE5J8ufETxR9iWxbzaJlQ07f5tSHKZznjlhQ2acoIM2YbgqjZlAdm9frJvXhKjg6k8F41IC8bjywij2FmbuMeqrOksG8XhrBDh6Es9Jrw1qelnFP/ZjHLgL+jTW74e1pFShhXwTJKp+CXaBNQbj9Ydn39F1qhlN6wmr+BUGKb8DhG8jbnScuAJHjgQKBgQDf2J5AmiiBrdWK4eN4glYNAjSFYAeCmCKu2J7YgTXuNUn9C9obZrxIqmig0E9Sqk+FhGlzU2zf2uqiSrUtz4ye/XEyhdKU48K/XoATiZ5B14XXmgTS1dcxb4JdaxmIPx/Ufi8VvnF1cp74FRZd1bED+2wHb8E24RC+SSgrGLqhnwKBgQC4IjIfPM/MrgErtYPTGatf1pDNAq0V1Gfwc7ma31ik2/8fK4tikJic7fQLAXhVbXHZj3Tg0H1gB2CD7JwjE8GoNQZoBqzA+ztLOIGL/sSagwZ16yfH5p5F4v+GPMq7ftHCkFXwA4MJaAdfvywJw6/pEfmA3Ca9rAYfiheaqvBCUQKBgQCfefHNLsc7FID10FdCTQFHhVGr0C2pkf/rt7L7ppqomlibKq16JihwUM/3+lD5IRNEmYCTRFVl6s6NT1JjT+Hsh73087iCkP33Xzk6MiqI2Can4zgaiXUl6D7llV78XZSszDV5dVPaDA5LyqJDufHCV4awiaMLGS/XkcFHavfUGwKBgEZd4xOm9K5+jLn8av1AYQqF/94ouKaP/oyYXQS/d6vA2vU5edHX+kp2cP3HuQnEgm6m8P59DnylNRAVWvHYufgjE2irrFcva1Bqrq8mVFHL4qsri49MJY4FmqrdDoiqhVS6Pe00Gl5839yKPTUaEF6cXOIOv8taMxjYd/7GdnyRAoGBAJt6pj7AGfH6UwAu7DoVjflkyldMn0Eyj9cQg1zLTYHyUxYzMrE34V3gGLTyP4QL3VlIfWl7w+9VrFQRbEb7v6mcKHx5cs9kE617Xos7aoB2n8bh3Ruk0+tBUdaaOfpShaqot0W4LSsVJns6HUFpdL0v+DU+glXCWfwQPNalOPjc";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnlHL7hbExrkDteey+W4k8eGC0+2VxpPNV8feJ8R/VhVtN5sWuKFWG8asCDoYVuPChtVETOPJgDqKoG31rvL3KCVWNwGMSYYHoQ50sLG8GsrErd/4OLpfoWBR1pdhJvOmE0ffnLVDI2l5u/decLoFP/1TdacJnlas9zXOKcFczLu6JLMqrGgSES0yUEyw2xtXjUrR0VFVI1t+MO5C7fYf2a8Dog9HvO56f32QnjG+1GWU/Io2q2whLekwPXgeRGdGFolKmqlw6Jn7vFgVicye0l7cF49QaBZ9OftEWeVUxlsipEgVOBVny0p1GpvE5h29nWF/hH9J/pNzVTtVA65h8QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://localhost:8888/logistics/index.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/takeZhiFuSuccess";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

