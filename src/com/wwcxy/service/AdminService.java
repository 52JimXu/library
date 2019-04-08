package com.wwcxy.service;

import java.util.List;

import com.wwcxy.dao.AdminDao;
import com.wwcxy.entity.AdminEntity;
public class AdminService {
	AdminDao ad =new AdminDao();
	//查询所有管理员信息
	public List<AdminEntity> getAllAdmin(int page,int limit){
		List<AdminEntity> list = ad.getAllAdmin(page,limit);
		return list;
	}
	public int getCount() {
		int count = ad.getCount();
		return count;
	}
	//根据管理员姓名(uname)进行模糊查询
	public List<AdminEntity> getAdminByusername(String username,int page,int limit){
		return ad.getAdminByusername(username, page, limit);
	}
	public int getLikeCount(String username){
		return ad.getLikeCount(username);
	}
	//新增管理员
	public int addAdmin(AdminEntity ae){
		return ad.addAdmin(ae);
	}
	//根据管理员编号(uid)获取管理员信息
	public AdminEntity getAdminByuid(int uid){
		return ad.getAdminByuid(uid);	
	}
	//根据管理员编号(uid)修改管理员信息
	public int updateByuid(AdminEntity ae){
		return ad.updateByuid(ae);
	}
	//根据管理员编号(uid)删除管理员信息
		public int delAdminByuid(int uid){
			return ad.delAdminByuid(uid);
		}
		public void delSelect(String uid) {
			if(!uid.equals("")){
				String [] uids = uid.split(",");
				AdminService as =new AdminService();
		        for(int a = 0;a<uids.length;a++){
		        	int id = Integer.parseInt(uids[a]);
		        	as.delAdminByuid(id);
		        }
			}
		}
}
