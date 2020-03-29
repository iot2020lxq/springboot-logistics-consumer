package com.iot.logistics.send.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Send {
	private String sid;// 订单编号
	private String s_mName;// 寄件人姓名
	private String s_company;// 寄件公司
	private String s_mNumber;// 寄件人手机号码
	private String s_mAddress;// 寄件人地址
	private String s_mStreet;

	private String s_uName;// 收件人姓名
	private String s_uNumber;// 收贱人手机号码
	private String s_uAddress;// 收件人地址
	private String s_uStreet;

	private int s_state;// 订单状态(3和4)，表示未付款和付款
	private String uid;// 用户主键

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化日期
	private Date s_date;

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getS_mName() {
		return s_mName;
	}

	public void setS_mName(String s_mName) {
		this.s_mName = s_mName;
	}

	public String getS_company() {
		return s_company;
	}

	public void setS_company(String s_company) {
		this.s_company = s_company;
	}

	public String getS_mNumber() {
		return s_mNumber;
	}

	public void setS_mNumber(String s_mNumber) {
		this.s_mNumber = s_mNumber;
	}

	public String getS_mAddress() {
		return s_mAddress;
	}

	public void setS_mAddress(String s_mAddress) {
		this.s_mAddress = s_mAddress;
	}

	public String getS_mStreet() {
		return s_mStreet;
	}

	public void setS_mStreet(String s_mStreet) {
		this.s_mStreet = s_mStreet;
	}

	public String getS_uName() {
		return s_uName;
	}

	public void setS_uName(String s_uName) {
		this.s_uName = s_uName;
	}

	public String getS_uNumber() {
		return s_uNumber;
	}

	public void setS_uNumber(String s_uNumber) {
		this.s_uNumber = s_uNumber;
	}

	public String getS_uAddress() {
		return s_uAddress;
	}

	public void setS_uAddress(String s_uAddress) {
		this.s_uAddress = s_uAddress;
	}

	public String getS_uStreet() {
		return s_uStreet;
	}

	public void setS_uStreet(String s_uStreet) {
		this.s_uStreet = s_uStreet;
	}

	public int getS_state() {
		return s_state;
	}

	public void setS_state(int s_state) {
		this.s_state = s_state;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getT_date() {
		return s_date;
	}

	public void setT_date(Date s_date) {
		this.s_date = s_date;
	}

	@Override
	public String toString() {
		return "Send [sid=" + sid + ", s_mName=" + s_mName + ", s_company=" + s_company + ", s_mNumber=" + s_mNumber
				+ ", s_mAddress=" + s_mAddress + ", s_mStreet=" + s_mStreet + ", s_uName=" + s_uName + ", s_uNumber="
				+ s_uNumber + ", s_uAddress=" + s_uAddress + ", s_uStreet=" + s_uStreet + ", s_state=" + s_state
				+ ", uid=" + uid + ", s_date=" + s_date + "]";
	}

}
