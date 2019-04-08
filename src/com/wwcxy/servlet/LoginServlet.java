package com.wwcxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wwcxy.entity.AdminEntity;
import com.wwcxy.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String code = request.getParameter("code");
		boolean flag=false;
		if(name==null&&pwd==null){
			flag=true;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie:cookies){
				if("username".equals(cookie.getName())){
					name = URLDecoder.decode(cookie.getValue(),"UTF-8");
				}else if("password".equals(cookie.getName())){
					pwd = URLDecoder.decode(cookie.getValue(),"UTF-8");
				}
			}
			code = "1";
		}
		
		String remeber = request.getParameter("remeber");
		PrintWriter out = response.getWriter();
		String checkcode = null;
		if(flag){checkcode="1";}else{checkcode = (String)request.getSession().getAttribute("checkcode");}
		
		if(!checkcode.equalsIgnoreCase(code)){
			if(flag){out.write("-1");}
		}else{
			LoginService ls = new LoginService();
			AdminEntity login = ls.login(name, pwd);
//			if(login.getUsername()==null){
//				out.write("0");
//			}else{
//				
//				request.getSession().setAttribute("user",login);
//				out.write("1");
			
			if(login.getUsername()!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user", login);
				if("true".equals(remeber)){
					Cookie cookieName = new Cookie("username", URLEncoder.encode(name,"UTF-8"));
					cookieName.setMaxAge(30*24*60*60);
					cookieName.setPath("/");
					Cookie cookiePwd = new Cookie("password",URLEncoder.encode(pwd,"UTF-8"));
					cookiePwd.setMaxAge(30*24*60*60);
					cookiePwd.setPath("/");
					response.addCookie(cookieName);
					response.addCookie(cookiePwd);
				}
				if(flag){
					response.sendRedirect("jsp/index.jsp");
				}else{
					out.write("1");
				}
			}else{
				if(flag){
					response.sendRedirect("login.jsp");
				}else{
					out.write("0");
				}
			}
			}
		}
	}

