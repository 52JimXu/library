package com.wwcxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.wwcxy.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AdminService as =new AdminService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		if("GetAdminByPage".equals(request.getParameter("flag"))){
			if (request.getParameter("like")==null) {
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				List<AdminEntity> list = as.getAllAdmin(limit, page);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", as.getCount());
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			} else {
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				String username=request.getParameter("like");
				List<AdminEntity> list=as.getAdminByusername(username, page, limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", as.getLikeCount(username));
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
				
			}
			
		}else if ("AddAdmin".equals(request.getParameter("flag"))) {
			String username=request.getParameter("username");
			String userpwd=request.getParameter("userpwd");
			String uname=request.getParameter("uname");
			String upower=(request.getParameter("upower"));
			AdminEntity ae=new AdminEntity(0,username,userpwd,uname,upower);
			as.addAdmin(ae);
			PrintWriter out = response.getWriter();       
	        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/admin.jsp','_parent')</script>");      
		}else if ("editAdmin".equals(request.getParameter("flag"))) {
			int uid=Integer.parseInt(request.getParameter("uid"));
			AdminEntity ae =as.getAdminByuid(uid);
			HttpSession session =request.getSession();
			session.setAttribute("ae", ae);
			response.sendRedirect("jsp/adminedit.jsp");
		}else if ("updateAdmin".equals(request.getParameter("flag"))) {
			int uid=Integer.parseInt(request.getParameter("uid"));
			String username=request.getParameter("username");
			String userpwd=request.getParameter("userpwd");
			String uname=request.getParameter("uname");
			String upower = null;
			if("是".equals(request.getParameter("upower"))){
				upower="超级管理员";
			}else{
				upower="普通管理员";
			}

			AdminEntity ae = new AdminEntity(uid,username,userpwd,uname,upower);
			as.updateByuid(ae);
			PrintWriter out = response.getWriter();       
	        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/admin.jsp','_parent')</script>");
		}else if ("delAdmin".equals(request.getParameter("flag"))) {
			int uid=Integer.parseInt(request.getParameter("uid"));
			as.delAdminByuid(uid);
			response.sendRedirect("jsp/admin.jsp");
		}else if ("delSelect".equals(request.getParameter("flag"))) {
			String uids =request.getParameter("uids");
			as.delSelect(uids);
		}

	
	}
}
