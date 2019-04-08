package com.wwcxy.service;

import java.util.List;

import com.wwcxy.dao.CardDao;
import com.wwcxy.entity.Card;

public class CardService {
	CardDao cd = new CardDao();
	public List<Card> getAllCards(int page,int limit) {
		List<Card> list = cd.getAllCards(page,limit);
		return list;
	}
	public int getCount() {
		int count = cd.getCount();
		return count;
	}
	public int addUser(Card card){
		return cd.addUser(card);
	}
	//删除卡信息
	public int delById(int id){
		return cd.delById(id);
	}
	//根据ID获取卡信息
	public Card getCardById(int id){
		return cd.getCardById(id);
	}
	//根据ID修改用户信息
	public int updateById(Card card){
		return cd.updateById(card);
	}
	public void DelSelect(String cid) {
		System.out.println(cid);
		if(!cid.equals("")){
			String [] cids = cid.split(",");
			CardService cardService = new CardService();
	        for(int a = 0;a<cids.length;a++){
	        	int id = Integer.parseInt(cids[a]);
	        	cardService.delById(id);
	        }
		}
	}
	//模糊查询
	public List<Card> getLikeCard(String name,int page,int limit){
		return cd.getLikeCard(name, page, limit);
	}
	public int getLikeCount(String name){
		return cd.getLikeCount(name);
	}
}
