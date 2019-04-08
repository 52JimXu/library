package com.wwcxy.service;

import java.util.List;

import com.wwcxy.dao.BookDao;
import com.wwcxy.entity.Book;


public class BookService {
	//查询所有书籍信息
		BookDao bd=new BookDao();
		public List<Book> getAllBook(int limit, int page){
			List<Book> list =bd.getAllBook(limit,page);
			return list;
		}
		public int getCount() {
			int count = bd.getCount();
			return count;
		}
		//根据书籍（bname）名称进行模糊查询
		public List<Book> getBookBybname(String bname,int page,int limit){
			return bd.getBookBybname(bname, page, limit);
		}
		public int getLikeCount(String bname){
			return bd.getLikeCount(bname);
		}
		//删除书籍
		public int delBybid(int bid){
			return bd.delBybid(bid);
		}
		public void delSelect(String bid) {
			System.out.println(bid);
			if(!bid.equals("")){
				String [] bids = bid.split(",");
				BookService bs= new BookService();
		        for(int a = 0;a<bids.length;a++){
		        	int id = Integer.parseInt(bids[a]);
		        	bs.delBybid(id);
		        }
			}
		}
		public int addBook(Book book){
			return bd.addBook(book);
		}
		public Book getBookByBid(int bid){
			return bd.getBookByBid(bid);
		}
		public Book getBookBybid(int bid){
			return bd.getBookBybid(bid);
		}
		public int updateBybid(Book book){
			return bd.updateBybid(book);
		}
		public List<Book> getalltname(){
			return bd.getalltname();
		}
		public int gettid(String tname){
			return bd.gettid(tname);
		}
}
