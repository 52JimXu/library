package com.wwcxy.servlet;

import java.io.File;
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
import com.wwcxy.entity.Card;
import com.wwcxy.entity.Type;
import com.wwcxy.service.TypeService;
/**
 * Servlet implementation class TypeServlcet
 */
@WebServlet("/TypeServlet")//这个不能注释啊 ，前台写了../这里就不用+jsp了
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   public TypeServlet() {
        super();
        // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   //实例化service
   TypeService ts = new TypeService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if("GetTypeByPage".equals(request.getParameter("flag"))){
			if(request.getParameter("likename")==null){
			int limit = Integer.parseInt(request.getParameter("limit")) ;
			int page = Integer.parseInt(request.getParameter("page")) ;
			
			List<Type> list = ts.getAllTypeByPage(page, limit);
			Map<String, Object> map = new HashMap<>();
			map.put("code", 0);//返回状态码，正常是200，但框架默认是0，所以设置0
			map.put("msg", "");
			map.put("count", ts.getCount());
			map.put("data", list);//data传的就是查询的list集合
			ObjectMapper mapper = new ObjectMapper();
	        response.setContentType("application/json;charset=utf-8");
	        mapper.writeValue(response.getOutputStream(),map);
	        }else {
	        	int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				String tname = request.getParameter("likename");
				List<Type> list = ts.getTypeByName(tname, page, limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", ts.getLikeCount(tname));
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}			
		}else if("AddType".equals(request.getParameter("flag"))){
			int tCode = Integer.parseInt(request.getParameter("tcode"));
			String tName = request.getParameter("tname");
			Type type= new Type(0, tCode, tName);
			ts.addType(type);
			PrintWriter out = response.getWriter();       
	        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/type.jsp','_parent')</script>");
	}else if("EditType".equals(request.getParameter("flag"))){
		int tId = Integer.parseInt(request.getParameter("tId")) ;
		Type type = ts.getTypeById(tId);
		HttpSession session = request.getSession();
		session.setAttribute("type", type);
		response.sendRedirect("jsp/typeedit.jsp");
	}else if ("updateType".equals(request.getParameter("flag"))) {
		
		int tId = Integer.parseInt(request.getParameter("tId")) ;
		int tCode = Integer.parseInt(request.getParameter("tCode"));
		String tName = request.getParameter("tName");
		Type type = new Type(tId, tCode, tName);
		ts.updateById(type);
		PrintWriter out = response.getWriter();       
        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/type.jsp','_parent')</script>");
	}
	else if("DelType".equals(request.getParameter("flag"))){
		int id = Integer.parseInt(request.getParameter("tId")) ;
		ts.delById(id);
		response.sendRedirect("jsp/type.jsp");
	}else if("DelSelect".equals(request.getParameter("flag"))){
		String tids = request.getParameter("tids");
		ts.delAll(tids);
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
