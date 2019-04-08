package com.wwcxy.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.wwcxy.util.JdbcUtil;


public class JdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcUtil ju = new JdbcUtil();
		Connection conn = ju.getConnection();
		System.out.println(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

