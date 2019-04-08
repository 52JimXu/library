package com.wwcxy.entity;

public class Card {
	private int cid;
	private String name;
	private String email;
	private String status;
	private String vip;
	private int num;
	
	public Card(int cid, String name, String email, String status, String vip,
			int num) {
		super();
		this.cid = cid;
		this.name = name;
		this.email = email;
		this.status = status;
		this.vip = vip;
		this.num = num;
	}
	public Card() {
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	
	
}
