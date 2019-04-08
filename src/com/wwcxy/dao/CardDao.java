package com.wwcxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwcxy.entity.Card;
import com.wwcxy.util.JdbcUtil;

public class CardDao {
	public List<Card> getAllCards(int page,int limit){
		JdbcUtil ju = new JdbcUtil();
		Connection conn = ju.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Card> list = new ArrayList<Card>();
		String sql = "select * from card limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*limit);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Card card = new Card();
				card.setCid(rs.getInt("cid"));
				card.setEmail(rs.getString("email"));
				card.setName(rs.getString("name"));
				card.setStatus(rs.getString("status"));
				card.setVip(rs.getString("vip"));
				card.setNum(rs.getInt("num"));
				list.add(card);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getCount(){
		JdbcUtil ju = new JdbcUtil();
		Connection conn = ju.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from card";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	//新增卡
		public int addUser(Card card){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			int row = 0;
			String sql = "insert into card(name,email,vip,num) values(?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, card.getName());
				pstmt.setString(2, card.getEmail());
				pstmt.setString(3, card.getVip());
				pstmt.setInt(4, card.getNum());
				row = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return row;
		}
		//删除卡信息
		public int delById(int id){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			int row = 0;
			String sql = "delete from card where cid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				row = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return row;
		}
		//根据ID获取卡信息
		public Card getCardById(int id){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			String sql = "select * from card where cid=?";
			Card card = new Card();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					card.setCid(rs.getInt("cid"));
					card.setEmail(rs.getString("email"));
					card.setName(rs.getString("name"));
					card.setStatus(rs.getString("status"));
					card.setVip(rs.getString("vip"));
					card.setNum(rs.getInt("num"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return card;
		}
		//根据ID修改用户信息
		public int updateById(Card card){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			int row = 0;
			String sql = "update card set name=?,email=?,status=?,vip=?,num=? where cid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, card.getName());
				pstmt.setString(2, card.getEmail());
				pstmt.setString(3, card.getStatus());
				pstmt.setString(4, card.getVip());
				pstmt.setInt(5, card.getNum());
				pstmt.setInt(6, card.getCid());
				row = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return row;
		}
	//模糊查询
	public List<Card> getLikeCard(String name,int page,int limit){
		JdbcUtil ju = new JdbcUtil();
		Connection conn = ju.getConnection();
		List<Card> list = new ArrayList<Card>();
		String sql = "select * from card where name like ? limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			pstmt.setInt(2, (page-1)*limit);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Card card = new Card();
				card.setCid(rs.getInt("cid"));
				card.setEmail(rs.getString("email"));
				card.setName(rs.getString("name"));
				card.setStatus(rs.getString("status"));
				card.setVip(rs.getString("vip"));
				card.setNum(rs.getInt("num"));
				list.add(card);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getLikeCount(String name){
		JdbcUtil ju = new JdbcUtil();
		Connection conn = ju.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from card where name like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
}
