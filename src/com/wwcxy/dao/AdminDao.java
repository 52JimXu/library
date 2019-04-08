package com.wwcxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwcxy.entity.AdminEntity;
import com.wwcxy.util.JdbcUtil;

public class AdminDao {
//查询所有管理员信息
	JdbcUtil ju =new JdbcUtil();
	public List<AdminEntity> getAllAdmin(int limit, int page){
		Connection con= ju.getConnection();
		PreparedStatement ps=null;
		ResultSet rs =null;
		List<AdminEntity> list =new ArrayList<AdminEntity>();
		String sql ="select * from user limit ?,? ";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs=ps.executeQuery();
			while (rs.next()) {
				AdminEntity ae =new AdminEntity();
				ae.setUid(rs.getInt("uid"));
				ae.setUsername(rs.getString("username"));
				ae.setUserpwd(rs.getNString("userpwd"));
				ae.setUname(rs.getString("uname"));
				ae.setUpower(rs.getString("upower"));
				list.add(ae);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}
	/*public static void main(String[] args) {
		List<AdminEntity> list = new AdminDao().getAllAdmin(1, 10);
		for(AdminEntity list1:list){
			System.out.println(list1.getUn);
		}
	}*/
	//获取管理员表数据数
		public int getCount(){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			String sql = "select count(*) from user";
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
	//根据管理员姓名(uname)进行模糊查询
	public List<AdminEntity> getAdminByusername(String username,int page,int limit){
		Connection con =ju.getConnection();
		PreparedStatement ps = null;
		ResultSet rs =null;
		List<AdminEntity> list = new ArrayList<AdminEntity>();
		String sql ="select * from user where username like ? limit ?,?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+username+"%");
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while (rs.next()) {
				AdminEntity ae =new AdminEntity();
				ae.setUid(rs.getInt("uid"));
				ae.setUsername(rs.getString("username"));
				ae.setUserpwd(rs.getNString("userpwd"));
				ae.setUname(rs.getString("uname"));
				ae.setUpower(rs.getString("upower"));
				list.add(ae);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getLikeCount(String username){
		Connection conn = ju.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from user where username like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+username+"%");
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
	//新增管理员
	public int addAdmin(AdminEntity ae){
		Connection con =ju.getConnection();
		PreparedStatement ps=null;
		int row=0;
		String sql="insert into user(username,userpwd,uname,upower) values(?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, ae.getUsername());
			ps.setString(2, ae.getUserpwd());
			ps.setString(3, ae.getUname());
			ps.setString(4, ae.getUpower());
			row=ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return row;
	}
	//根据管理员编号(uid)获取管理员信息
	public AdminEntity getAdminByuid(int uid){
		Connection con=ju.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		AdminEntity ae =new AdminEntity();
		String sql ="select * from user  where uid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, uid);
			rs=ps.executeQuery();
			while (rs.next()) {
				ae.setUid(rs.getInt("uid"));
				ae.setUsername(rs.getString("username"));
				ae.setUserpwd(rs.getNString("userpwd"));
				ae.setUname(rs.getString("uname"));
				ae.setUpower(rs.getString("upower"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ae;
	}
	//根据管理员编号(uid)修改管理员信息
	public int updateByuid(AdminEntity ae){
		Connection con =ju.getConnection();
		PreparedStatement ps=null;
		int row=0;
		String sql="update user set username=?,userpwd=?,uname=?,upower=? where uid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, ae.getUsername());
			ps.setString(2, ae.getUserpwd());
			ps.setString(3, ae.getUname());
			ps.setString(4, ae.getUpower());
			ps.setInt(5, ae.getUid());
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return row;
	}
	//根据管理员编号(uid)删除管理员信息
	public int delAdminByuid(int uid){
		Connection conn = ju.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "delete from user where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
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
}
