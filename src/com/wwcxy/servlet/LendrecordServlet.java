package com.wwcxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwcxy.entity.AdminEntity;
import com.wwcxy.entity.Book;
import com.wwcxy.entity.Card;
import com.wwcxy.entity.LendRecord;
import com.wwcxy.service.LendrecordService;

/**
 * Servlet implementation class LendrecordServlet
 */
@WebServlet("/LendrecordServlet")
public class LendrecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LendrecordService ls = new LendrecordService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendrecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("getAllLendRecordBypage".equals(request.getParameter("flag"))){
			if (request.getParameter("like")==null) {
			int limit = Integer.parseInt(request.getParameter("limit")) ;
			int page = Integer.parseInt(request.getParameter("page")) ;
			List<LendRecord> list = ls.getAllLendRecordBypage(page,limit);
			Map<String, Object> map = new HashMap<>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", ls.getCount());
			map.put("data", list);
			ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),map);
			}else {
				int cid=Integer.parseInt(request.getParameter("like")) ;
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				List<LendRecord> list=ls.getlrByCid(cid, page, limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", ls.getLikeCount(cid));
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}
		}else if("addLr".equals(request.getParameter("flag"))){
			 int bid = Integer.parseInt(request.getParameter("bid"));
			 int cid = Integer.parseInt(request.getParameter("cid"));
			 String borrowtime = request.getParameter("borrowtime");
			 Calendar calc =Calendar.getInstance();
			 SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
			 String deadline=null;
			 try {
				calc.setTime(sdf.parse(borrowtime));
				calc.add(calc.DATE, +30);
				Date deadlinetime = calc.getTime();
				deadline = sdf.format(deadlinetime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 String status = "未还";
			 
			 
			 LendRecord lr = new LendRecord(0,bid,cid,borrowtime,deadline,null,status);
			 ls.addLr(lr);
			 
			 PrintWriter out = response.getWriter();       
		     out.println("<script>window.open ('"+request.getContextPath()+"/jsp/lendrecord.jsp','_parent')</script>");
		}else if("EditLendrecord".equals(request.getParameter("flag"))){
			int lid = Integer.parseInt(request.getParameter("lid")) ;
			LendRecord lendRecord = ls.getLrByLid(lid);
			HttpSession session = request.getSession();
			session.setAttribute("lendRecord", lendRecord);
			response.sendRedirect("jsp/lendrecordedit.jsp");
		}else if("updateLrByLid".equals(request.getParameter("flag"))){
			 int lid = Integer.parseInt(request.getParameter("lid"));
			 int cid = Integer.parseInt(request.getParameter("cid"));
			 String borrowtime = request.getParameter("borrowtime");
			 String deadline = request.getParameter("deadline");
			 String returntime = request.getParameter("returntime");
			 String status="";
			 if(returntime!=null){
				 status = "已还";
			 }else{
				 status="未还";
			 }
			 LendRecord lr = new LendRecord(lid,0,cid,borrowtime,deadline,returntime,status);
			 ls.updateLrByLid(lr);
			 PrintWriter out = response.getWriter();       
		     out.println("<script>window.open ('"+request.getContextPath()+"/jsp/lendrecord.jsp','_parent')</script>");
		}else if("delLrByLid".equals(request.getParameter("flag"))){
			int lid = Integer.parseInt(request.getParameter("lid"));
			ls.delLrByLid(lid);
			System.out.println(lid);
			response.sendRedirect("jsp/lendrecord.jsp");
		}else if ("delAll".equals(request.getParameter("flag"))) {
			String lids =request.getParameter("lids");
			ls.delAll(lids);
		}else if ("GetCid".equals(request.getParameter("flag"))) {
			List<LendRecord> list = ls.getCid();
			List<Integer> list2 = new ArrayList<Integer>();
			for(LendRecord lr:list){
				list2.add(lr.getCid());
			}
			ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),list2);
		}else if ("GetBid".equals(request.getParameter("flag"))) {
			List<LendRecord> list = ls.getBid();
			List<Integer> list2 = new ArrayList<Integer>();
			for(LendRecord lr:list){
				list2.add(lr.getBid());
			}
			ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),list2);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
