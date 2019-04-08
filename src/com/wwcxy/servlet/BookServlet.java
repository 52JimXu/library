package com.wwcxy.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwcxy.entity.Book;
import com.wwcxy.service.BookService;
import com.wwcxy.util.BarcodeUtil;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		BookService bs =new BookService();
		if("GetBook".equals(request.getParameter("flag"))){
			if (request.getParameter("like")==null) {
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				List<Book> list = bs.getAllBook(limit, page);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", bs.getCount());
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
				request.getSession().setAttribute("list", list);
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}else {
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				String bname=request.getParameter("like");
				List<Book> list=bs.getBookBybname(bname, page, limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", bs.getLikeCount(bname));
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
				request.getSession().setAttribute("list", list);
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}
		}else if ("delBook".equals(request.getParameter("flag"))) {
			int bid = Integer.parseInt(request.getParameter("bid")) ;
			bs.delBybid(bid);
			response.sendRedirect("jsp/book.jsp");
		}else if ("delSelect".equals(request.getParameter("flag"))) {
			String bids =request.getParameter("bids");
			bs.delSelect(bids);
		}else if("AddBook".equals(request.getParameter("flag"))){
			boolean flag = ServletFileUpload.isMultipartContent(request);
			if(flag){
				String ibsnimg = null; String bookname =null;
				String bookimg = null; String author= null;
				int tid=-1; String publisher = null;
				String ibsn = null; int allnum=-1;String years=null;
				String tname =null;
				Long date = new Date().getTime();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					String path = request.getSession().getServletContext().getRealPath("image");
					List<FileItem> list = upload.parseRequest(request);
					Iterator<FileItem> iter = list.iterator();
					while(iter.hasNext()){
						FileItem item = iter.next();
						String name = item.getFieldName();
						if(!item.isFormField()){
							if(item.getSize()==0){
								bookimg ="<img lay-event='bimagebig' src='../image/nopic.jpg'/>";
							}else{
								String filename = date+".jpg";
								bookimg ="<img lay-event='bimagebig' src='../image/"+filename+"'/>";
								File file = new File(path,filename);
								item.write(file);
							}
						}else{
							if("bookname".equals(name)){
								bookname = item.getString("UTF-8");
							}else if("author".equals(name)){
								author = item.getString("UTF-8");
							}else if("publisher".equals(name)){
								publisher = item.getString("UTF-8");
							}else if("years".equals(name)){
								years = item.getString("UTF-8");
							}else if("ibsn".equals(name)){
								ibsn = item.getString("UTF-8");
								String ibsnpath = date+".png";
								String ibsnp = path+"/"+ibsnpath;
								BarcodeUtil.generateFile(ibsn, ibsnp);
								ibsnimg = "<img lay-event='ibsnbig' src='../image/"+ibsnpath+"'>";
							}else if("allnum".equals(name)){
								allnum = Integer.parseInt(item.getString("UTF-8"));
							}else if("tname".equals(name)){
								tname = item.getString("UTF-8");
							}
						}
					}
				} catch ( Exception e) {
					e.printStackTrace();
				}
				tid = bs.gettid(tname);
				Book book = new Book(0, bookname, tid, author, publisher, years, ibsn, allnum, allnum, bookimg, ibsnimg);
				bs.addBook(book);
				PrintWriter out = response.getWriter();       
		        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/book.jsp','_parent')</script>");
			}
		}else if("EditBook".equals(request.getParameter("flag"))){
			int bid = Integer.parseInt(request.getParameter("bid")) ;
			Book book = new Book();
			book = bs.getBookBybid(bid);
			request.getSession().setAttribute("book", book);
			response.sendRedirect("jsp/bookedit.jsp");
		}else if("UpdateBook".equals(request.getParameter("flag"))){
			boolean flag = ServletFileUpload.isMultipartContent(request);
			boolean flag1 = false;
			if(flag){
				String ibsnimg = null; String bookname =null;
				String bookimg = null; String author= null;
				int tid=-1; String publisher = null;String tname = null;
				String ibsn = null; int allnum=-1;String years=null;
				Long date = new Date().getTime();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					String path = request.getSession().getServletContext().getRealPath("image");
					List<FileItem> list = upload.parseRequest(request);
					Iterator<FileItem> iter = list.iterator();
					while(iter.hasNext()){
						FileItem item = iter.next();
						String name = item.getFieldName();
						if(!item.isFormField()){
							if(item.getSize()!=0){
								String filename = date+".jpg";
								bookimg ="<img lay-event='bimagebig' src='../image/"+filename+"'/>";
								File file = new File(path,filename);
								item.write(file);
							}else{
								flag1=true;
							}
						}
						else{
							if("bookname".equals(name)){
								bookname = item.getString("UTF-8");
							}else if("author".equals(name)){
								author = item.getString("UTF-8");
							}else if("publisher".equals(name)){
								publisher = item.getString("UTF-8");
							}else if("years".equals(name)){
								years = item.getString("UTF-8");
							}else if("ibsn".equals(name)){
								ibsn = item.getString("UTF-8");
								String ibsnpath = date+".png";
								String ibsnp = path+"/"+ibsnpath;
								BarcodeUtil.generateFile(ibsn, ibsnp);
								ibsnimg = "<img lay-event='ibsnbig' src='../image/"+ibsnpath+"'>";
							}else if("allnum".equals(name)){
								allnum = Integer.parseInt(item.getString("UTF-8"));
							}else if("tname".equals(name)){
								tname = item.getString("UTF-8");
							}
						}
					}
				} catch ( Exception e) {
					e.printStackTrace();
				}
				int bid = Integer.parseInt(request.getParameter("bid"));
				if(flag1){
					Book book = bs.getBookByBid(bid);
					bookimg = book.getBimage();
				}
				tid = bs.gettid(tname);
				Book book = new Book(bid, bookname, tid, author, publisher, years, ibsn, allnum, allnum, bookimg, ibsnimg);
				bs.updateBybid(book);
				PrintWriter out = response.getWriter();       
		        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/book.jsp','_parent')</script>");
			}
		}
		else if("GetTname".equals(request.getParameter("flag"))){
			List<Book> list = bs.getalltname();
			List<String> list2 = new ArrayList<String>();
			for(Book book:list){
				list2.add(book.getTname());
			}
			ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),list2);
		}
	}
}
