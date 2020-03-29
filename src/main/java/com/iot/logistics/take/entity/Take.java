package com.iot.logistics.take.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Take {
	private String tid;
	private String t_number;
	private String t_address;
	private String t_msg;
	private String t_name;
	private String t_uNumber;
	private String t_street;
	private String t_uAddress;
	private String uid;
	private int t_state;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//格式化日期
	private Date t_date;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getT_number() {
		return t_number;
	}

	public void setT_number(String t_number) {
		this.t_number = t_number;
	}

	public String getT_address() {
		return t_address;
	}

	public void setT_address(String t_address) {
		this.t_address = t_address;
	}

	public String getT_msg() {
		return t_msg;
	}

	public void setT_msg(String t_msg) {
		this.t_msg = t_msg;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_uNumber() {
		return t_uNumber;
	}

	public void setT_uNumber(String t_uNumber) {
		this.t_uNumber = t_uNumber;
	}

	public String getT_street() {
		return t_street;
	}

	public void setT_street(String t_street) {
		this.t_street = t_street;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getT_state() {
		return t_state;
	}

	public void setT_state(int t_state) {
		this.t_state = t_state;
	}

	public Date getT_date() {
		return t_date;
	}

	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	
	public String getT_uAddress() {
		return t_uAddress;
	}

	public void setT_uAddress(String t_uAddress) {
		this.t_uAddress = t_uAddress;
	}
	
	@Override
	public String toString() {
		return "Take [tid=" + tid + ", t_number=" + t_number + ", t_address=" + t_address + ", t_msg=" + t_msg
				+ ", t_name=" + t_name + ", t_uNumber=" + t_uNumber + ", t_street=" + t_street + ", t_uAddress="
				+ t_uAddress + ", uid=" + uid + ", t_state=" + t_state + ", t_date=" + t_date + "]";
	}

}
