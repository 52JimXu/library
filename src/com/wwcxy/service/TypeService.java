package com.wwcxy.service;

import java.util.List;

import com.wwcxy.dao.TypeDao;
import com.wwcxy.entity.Type;

public class TypeService {
	TypeDao ud = new TypeDao();
	//查询所有用户信息
	public List<Type> getAllTypeByPage(int page,int limit){
		return ud.getAllTypeByPage(page,limit);
	}
	//根据姓名进行模糊查询
	public List<Type> getTypeByName(String tname,int page,int limit){
		return ud.getTypeByName(tname, page, limit);
	}
	public int getLikeCount(String tname){
		return ud.getLikeCount(tname);
	}
	//新增用户
	public int addType(Type type){
		return ud.addType(type);
	}
	//根据ID获取用户信息
	public Type getTypeById(int id){
		return ud.getTypeById(id);
	}
	//根据ID修改用户信息
	public int updateById(Type type){
		return ud.updateById(type);
	}
	//根据ID删除用户
	public int delById(int id){
		return ud.delById(id);
	}
	//批量删除
	public void delAll(String tid){
		if(!tid.equals("")){
			String [] tids = tid.split(",");
			TypeService ts =new TypeService();
	        for(int a = 0;a<tids.length;a++){
	        	int id = Integer.parseInt(tids[a]);
	        	ts.delById(id);
	        }}
	}
	//获取用户表数据数
	public int getCount(){
		return ud.getCount();
	}
	
}
