package com.wwcxy.entity;
public class AdminEntity {
private int uid;//管理员编号
private String username;//管理员用户名
private String userpwd;//管理员密码
private String uname;//管理员姓名
private String upower;//管理员权限
public AdminEntity(){
	
}
public AdminEntity(int uid, String username, String userpwd, String uname,
		String upower) {
	super();
	this.uid = uid;
	this.username = username;
	this.userpwd = userpwd;
	this.uname = uname;
	this.upower = upower;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpwd() {
	return userpwd;
}
public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getUpower() {
	return upower;
}
public void setUpower(String upower) {
	this.upower = upower;
}

}
