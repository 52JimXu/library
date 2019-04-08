package com.wwcxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wwcxy.entity.AdminEntity;
import com.wwcxy.util.JdbcUtil;

public class LoginDao {
	JdbcUtil ju = new JdbcUtil();
	public AdminEntity login(String username,String userPwd){
		Connection conn = ju.getConnection();
		String sql = "select * from user where username=? and userpwd=?";
		AdminEntity ae = new AdminEntity();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ae.setUid(rs.getInt("uid"));
				ae.setUsername(rs.getString("username"));
				ae.setUserpwd(rs.getNString("userpwd"));
				ae.setUname(rs.getString("uname"));
				ae.setUpower(rs.getString("upower"));
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
		return ae;
	}
}
