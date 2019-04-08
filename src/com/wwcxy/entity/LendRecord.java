package com.wwcxy.entity;


public class LendRecord {
	private int lid;
	private int bid;
	private String bname;
	private int cid;
	private String borrowtime;
	private String deadline;
	private String returntime;
	private String status;
	private String cname;
	public LendRecord(){
		
	}
	public LendRecord(int lid, int bid, int cid, String borrowtime,
			String deadline, String returntime, String status) {
		super();
		this.lid = lid;
		this.bid = bid;
		this.cid = cid;
		this.borrowtime = borrowtime;
		this.deadline = deadline;
		this.returntime = returntime;
		this.status = status;
	}
	public LendRecord(int lid, int bid, int cid,String cname, String borrowtime,
			String deadline, String returntime, String status) {
		super();
		this.lid = lid;
		this.bid = bid;
		this.cid = cid;
		this.cname = cname;
		this.borrowtime = borrowtime;
		this.deadline = deadline;
		this.returntime = returntime;
		this.status = status;
	}
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getBorrowtime() {
		return borrowtime;
	}
	public void setBorrowtime(String borrowtime) {
		this.borrowtime = borrowtime;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
