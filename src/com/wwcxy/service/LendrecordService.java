package com.wwcxy.service;

import java.util.List;

import com.wwcxy.dao.LendRecordDao;
import com.wwcxy.entity.Card;
import com.wwcxy.entity.LendRecord;

public class LendrecordService {
	LendRecordDao lrd = new LendRecordDao();
	//查询所有图书信息
	public List<LendRecord> getAllLendRecordBypage(int page,int limit){
		return lrd.getAllLendRecordBypage(page,limit);
	}
	//根据id查询图书信息
	public LendRecord getLrByLid(int lid){
		return lrd.getLrByLid(lid);
	}
	//根据cid查询图书信息
	public List<LendRecord> getlrByCid(int cid ,int page,int limit){
			return lrd.getlrByCid(cid, page, limit);
	}
	//新增图书信息
	public int addLr(LendRecord lr){
		return lrd.addLr(lr);
	}
	//删除图书信息
	public int delLrByLid(int lid){
		return lrd.delLrByLid(lid);
	}
	//修改图书信息
	public int updateLrByLid(LendRecord lr){
		return lrd.updateLrByLid(lr);
	}
	public int getCount(){
		return lrd.getCount();
	}
	public int getLikeCount(int cid){
		return lrd.getLikeCount(cid);
	}
	//根据
	public LendRecord getlrBycid(int cid){
	return lrd.getlrBycid(cid);		
	}
	public void delAll(String lid) {
		System.out.println(lid);
		if(!lid.equals("")){
			String [] lids = lid.split(",");
			LendrecordService ld=new LendrecordService();
	        for(int a = 0;a<lids.length;a++){
	        	int id = Integer.parseInt(lids[a]);
	        	ld.delLrByLid(id);
	        }
		}
	}
	public List<LendRecord> getCid(){
		return lrd.getCid();
	}
	public List<LendRecord> getBid(){
		return lrd.getBid();
	}
}
