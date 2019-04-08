package com.wwcxy.service;

import com.wwcxy.dao.LoginDao;
import com.wwcxy.entity.AdminEntity;

public class LoginService {
	public AdminEntity login(String userName,String userPwd){
		LoginDao ld = new LoginDao();
		AdminEntity ae = ld.login(userName, userPwd);
		return ae;
	}
}
