
package com.wwcxy.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
import com.wwcxy.entity.AdminEntity;
import com.wwcxy.entity.LendRecord;
import com.wwcxy.util.JdbcUtil;
public class LendRecordDao extends JdbcUtil{
	//查询所有图书信息
	public List<LendRecord> getAllLendRecordBypage(int page,int limit){
		List<LendRecord> list = new ArrayList<LendRecord>();
		Connection conn = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from lendrecord limit ?,?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*limit);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()){
				LendRecord lr = new LendRecord();
				lr.setLid(rs.getInt("lid"));
				lr.setBid(rs.getInt("bid"));
				lr.setBname(new LendRecordDao().getBnameBybid(rs.getInt("bid")));
				lr.setCid(rs.getInt("cid"));
				lr.setCname(new LendRecordDao().getCnameBycid(rs.getInt("cid")));
				lr.setBorrowtime(rs.getString("borrowtime"));
				lr.setDeadline(rs.getString("deadline"));
				lr.setReturntime(rs.getString("returntime"));
				lr.setStatus(rs.getString("status"));
				list.add(lr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.close(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
 		return list;
	}
	//新增图书信息
	public int addLr(LendRecord lr){
		int row = 0;
		Connection con=this.getConnection();
		PreparedStatement ps=null;
		String sql="insert into lendrecord"
				+ "(lid,bid,cid,borrowtime,deadline,returntime,status) values(?,?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1,lr.getLid());
			ps.setInt(2,lr.getBid());
			ps.setInt(3,lr.getCid());
			ps.setString(4,lr.getBorrowtime());
			ps.setString(5,lr.getDeadline());
			ps.setString(6, lr.getReturntime());
			ps.setString(7,lr.getStatus());
			row=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//根据lid修改图书信息
		public int updateLrByLid(LendRecord lr){
			int row = 0;
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			String sql = "update lendrecord set cid=?,borrowtime=?,returntime=?,status=? where lid=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1,lr.getCid());
				
				ps.setString(2,lr.getBorrowtime());
				ps.setString(3,lr.getReturntime());
				ps.setString(4,lr.getStatus());
				ps.setInt(5, lr.getLid());
				row=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					this.close(con, ps, null);
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			return row;
		}
	//删除图书信息
	public int delLrByLid(int lid){
		int row=0;
		Connection con=this.getConnection();
		PreparedStatement ps=null;
		String sql="delete from lendrecord where lid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1,lid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//根据cid查询图书信息
	public List<LendRecord> getlrByCid(int cid ,int page,int limit){
		List<LendRecord> list = new ArrayList<LendRecord>();
		Connection con = this.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from lendrecord where cid like ? limit ?,?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, cid+"");
			ps.setInt(2, (page-1)*limit);
			ps.setInt(3, limit);
			rs=ps.executeQuery();
			while(rs.next()){
				LendRecord lr = new LendRecord();
				lr.setLid(rs.getInt("lid"));
				lr.setBid(rs.getInt("bid"));
				lr.setBname(new LendRecordDao().getBnameBybid(rs.getInt("bid")));
				lr.setCid(rs.getInt("cid"));
				lr.setCname(new LendRecordDao().getCnameBycid(rs.getInt("cid")));
				lr.setBorrowtime(rs.getString("borrowtime"));
				lr.setDeadline(rs.getString("deadline"));
				lr.setReturntime(rs.getString("returntime"));
				lr.setStatus(rs.getString("status"));
				list.add(lr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//根据lid查询图书信息
	public LendRecord getLrByLid(int lid){
		LendRecord lendrecord = new LendRecord();
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from lendrecord where lid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, lid);
			rs = ps.executeQuery();
			while(rs.next()){
				lendrecord.setLid(rs.getInt("lid"));
				lendrecord.setBid(rs.getInt("bid"));
				lendrecord.setBname(new LendRecordDao().getBnameBybid(rs.getInt("bid")));
				lendrecord.setCid(rs.getInt("cid"));
				lendrecord.setCname(new LendRecordDao().getCnameBycid(rs.getInt("cid")));
				lendrecord.setBorrowtime(rs.getString("borrowtime"));
				lendrecord.setDeadline(rs.getString("deadline"));
				lendrecord.setReturntime(rs.getString("returntime"));
				lendrecord.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lendrecord;
	}
	public int getLikeCount(int cid){
		Connection conn = this.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from lendrecord where cid like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+cid+"%");
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
	//借书
		public int Borrowtime(int cid) {
			int row = 0;
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			String sql = "insert into lendrecord (cid,borrowtime,status,deadline) values(?,?,?,?)";
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			String nowtime = df.format(new Date());
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, cid);
				ps.setString(2, nowtime);
				ps.setString(3, "未还书");
				ps.setString(4, df1.format(new Date()));
				row = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					this.close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		//查看是否借书成功
		public int GetBorrowtime(int cid) {
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select cid from lendrecord where status=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, "未还书");
				rs = ps.executeQuery();
				while(rs.next()){
					if(cid == rs.getInt("cid")){
						return 1;  //已经借书成功
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					this.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return 0;//未借书
			
		}
		//通过cid查看未还书的借书时间
		public String GetBorrowimeByCid(int cid) {
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String borrowtime = null;
			String sql = "select borrrowtime from lendrecord where status=? and cid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, "未还书");
				ps.setInt(2, cid);
				rs = ps.executeQuery();
				if(rs.next()){
					borrowtime = rs.getString("borrowtime");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					this.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
			return borrowtime;
		
		}
		public int getCount(){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			String sql = "select count(*) from lendrecord";
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
		
		public LendRecord getlrBycid(int cid){
			Connection con=this.getConnection();
			PreparedStatement ps =null;
			ResultSet rs=null;
			LendRecord lr =new LendRecord();
			String sql ="select * from lendrecord  where cid=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, cid);
				rs=ps.executeQuery();
				while (rs.next()) {
					lr.setLid(rs.getInt("lid"));
					lr.setBid(rs.getInt("bid"));
					lr.setBname(new LendRecordDao().getBnameBybid(rs.getInt("bid")));
					lr.setCid(rs.getInt("cid"));
					lr.setBorrowtime(rs.getString("borrowtime"));
					lr.setDeadline(rs.getString("deadline"));
					lr.setReturntime(rs.getString("returntime"));
					lr.setStatus(rs.getString("status"));
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
			return lr;
		}
		//根据cid查询借书人姓名
		public String getCnameBycid(int cid){
			LendRecord lendrecord = new LendRecord();
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String cname = null;
			String sql = "select name from card where cid = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, cid);
				rs = ps.executeQuery();
				while(rs.next()){
					cname=rs.getString("name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return cname;
		}
		public String getBnameBybid(int bid){
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String bname = null;
			String sql = "select bname from book where bid = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, bid);
				rs = ps.executeQuery();
				if(rs.next()){
					bname=rs.getString("bname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					this.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return bname;
		}
		public List<LendRecord> getCid(){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<LendRecord> list = new ArrayList<LendRecord>();
			String sql = "select cid from card";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					LendRecord lr = new LendRecord();
					lr.setCid(rs.getInt("cid"));
					list.add(lr);
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
		public List<LendRecord> getBid(){
			JdbcUtil ju = new JdbcUtil();
			Connection conn = ju.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<LendRecord> list = new ArrayList<LendRecord>();
			String sql = "select bid from book";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					LendRecord lr = new LendRecord();
					lr.setBid(rs.getInt("bid"));
					list.add(lr);
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
}
