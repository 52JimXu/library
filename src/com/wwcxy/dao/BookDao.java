package com.wwcxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwcxy.entity.Book;
import com.wwcxy.util.JdbcUtil;

public class BookDao {

	JdbcUtil ju = new JdbcUtil();
	//查询所有书籍信息
	public List<Book> getAllBook(int limit, int page){
		Connection con =ju.getConnection();
		PreparedStatement ps =null;
		ResultSet rs =null;
		List<Book> list =new ArrayList<Book>();
		String sql="select * from book limit ?,?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs=ps.executeQuery();
			while (rs.next()) {
				Book book=new Book();
				book.setBid(rs.getInt("bid"));
				book.setBname(rs.getString("bname"));
				book.setBimage(rs.getString("bimage"));
				book.setTid(rs.getInt("tid"));
				book.setTname(new BookDao().gettname(rs.getInt("tid")));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setYears(rs.getString("years"));
				book.setIbsn(rs.getString("ibsn"));
				book.setAllnum(rs.getInt("allnum"));
				book.setNownum(rs.getInt("nownum"));
				book.setIbsnimg(rs.getString("ibsnimg"));
				list.add(book);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return list;
		
	}
	//获取书籍表数据条数
	public int getCount(){
		Connection con = ju.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from book";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	//根据书籍（bname）名称进行模糊查询
	public List<Book> getBookBybname(String bname,int page,int limit){
		Connection con =ju.getConnection();
		PreparedStatement ps = null;
		ResultSet rs =null;
		List<Book> list = new ArrayList<Book>();
		String sql ="select * from book where bname like ? limit ?,?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+bname+"%");
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book=new Book();
				book.setBid(rs.getInt("bid"));
				book.setBname(rs.getString("bname"));
				book.setBimage(rs.getString("bimage"));
				book.setTid(rs.getInt("tid"));
				book.setTname(new BookDao().gettname(rs.getInt("tid")));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setYears(rs.getString("years"));
				book.setIbsn(rs.getString("ibsn"));
				book.setAllnum(rs.getInt("allnum"));
				book.setNownum(rs.getInt("nownum"));
				book.setIbsnimg(rs.getString("ibsnimg"));
				list.add(book);
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
	public int getLikeCount(String bname){
		Connection conn = ju.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from book where bname like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+bname+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	//根据书籍名称获取卡信息
			public Book getBookBybid(int bid){
				JdbcUtil ju = new JdbcUtil();
				Connection conn = ju.getConnection();
				String sql = "select * from book where bid=?";
				Book book=new Book();
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, bid);
					rs = ps.executeQuery();
					if(rs.next()){
						book.setBid(rs.getInt("bid"));
						book.setBname(rs.getString("bname"));
						book.setBimage(rs.getString("bimage"));
						book.setTid(rs.getInt("tid"));
						book.setTname(new BookDao().gettname(rs.getInt("tid")));
						book.setAuthor(rs.getString("author"));
						book.setPublisher(rs.getString("publisher"));
						book.setYears(rs.getString("years"));
						book.setIbsn(rs.getString("ibsn"));
						book.setAllnum(rs.getInt("allnum"));
						book.setNownum(rs.getInt("nownum"));
						book.setIbsnimg(rs.getString("ibsnimg"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return book;
			}
			//删除书籍信息
			public int delBybid(int bid){
				JdbcUtil ju = new JdbcUtil();
				Connection conn = ju.getConnection();
				PreparedStatement ps = null;
				int row = 0;
				String sql = "delete from book where bid=?";
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, bid);
					row = ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						ps.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return row;
			}
			//新增书籍
			public int addBook(Book book){
				Connection con =ju.getConnection();
				PreparedStatement ps=null;
				int row=0;
				String sql="insert into book(bname,tid,author,publisher,years,ibsn,ibsnimg,allnum,nownum,bimage) values(?,?,?,?,?,?,?,?,?,?)";
				try {
					ps=con.prepareStatement(sql);
					ps.setString(1, book.getBname());
					ps.setInt(2, book.getTid());
					ps.setString(3, book.getAuthor());
					ps.setString(4, book.getPublisher());
					ps.setString(5, book.getYears());
					ps.setString(6, book.getIbsn());
					ps.setString(7, book.getIbsnimg());
					ps.setInt(8, book.getAllnum());
					ps.setInt(9, book.getNownum());
					ps.setString(10, book.getBimage());
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
			public Book getBookByBid(int bid){
				JdbcUtil ju = new JdbcUtil();
				Connection conn = ju.getConnection();
				String sql = "select * from book where bid=?";
				Book book=new Book();
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, bid);
					rs = ps.executeQuery();
					if(rs.next()){
						book.setBimage(rs.getString("bimage"));
						book.setIbsnimg(rs.getString("ibsnimg"));
						book.setIbsn(rs.getString("ibsn"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return book;
			}
			public int updateBybid(Book book){
				Connection con =ju.getConnection();
				PreparedStatement ps=null;
				int row=0;
				String sql="update book set bname=?,tid=?,author=?,publisher=?,years=?,ibsn=?,ibsnimg=?,allnum=?,nownum=?,bimage=? where bid=?";
				try {
					ps=con.prepareStatement(sql);
					ps.setString(1, book.getBname());
					ps.setInt(2, book.getTid());
					ps.setString(3, book.getAuthor());
					ps.setString(4, book.getPublisher());
					ps.setString(5, book.getYears());
					ps.setString(6, book.getIbsn());
					ps.setString(7, book.getIbsnimg());
					ps.setInt(8, book.getAllnum());
					ps.setInt(9, book.getNownum());
					ps.setString(10, book.getBimage());
					ps.setInt(11, book.getBid());
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
			public String gettname(int tid){
				Connection con = ju.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String tname = null;
				String sql = "select tname from type where tid=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, tid);
					rs = ps.executeQuery();
					while(rs.next()){
						tname = rs.getString("tname");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return tname;
			}
			public List<Book> getalltname(){
				Connection con = ju.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				List<Book> list = new ArrayList<>();
				String sql = "select tname from type";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()){
						Book book = new Book();
						book.setTname(rs.getString("tname"));
						list.add(book);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return list;
			}
			public int gettid(String tname){
				Connection con = ju.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				int tid = -1;
				String sql = "select tid from type where tname = ?";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, tname);
					rs = ps.executeQuery();
					while(rs.next()){
						tid = rs.getInt("tid");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return tid;
			}
			
			
}
