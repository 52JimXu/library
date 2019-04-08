package com.wwcxy.util;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JdbcUtil {
	public Connection getConnection(){
		Connection conn = null;
		InputStream in = null;
		try {
			in = this.getClass()
					.getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties();
			prop.load(in);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pwd = prop.getProperty("pwd");
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	public void close(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		if(conn != null){
			conn.close();
		}
	}
}
