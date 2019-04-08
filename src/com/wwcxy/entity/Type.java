package com.wwcxy.entity;

public class Type {
	private int tId;
	private int tCode;
	private String tName;
	public Type() {
		
	}
	public Type(int tId,int tCode,String tName){
		this.tId = tId;
		this.tCode = tCode;
		this.tName = tName;	
	}
	public int getTId() {
		return tId;
	}
	public void setTId(int tId) {
		this.tId = tId;
	}
	public int getTCode() {
		return tCode;
	}
	public void setTCode(int tCode) {
		this.tCode = tCode;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
}
