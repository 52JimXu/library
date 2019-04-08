package com.wwcxy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String path = req.getContextPath();
		String basepath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		String uri = req.getRequestURI();
		if(uri.contains("/LoginServlet")||uri.contains("/css/")||uri.contains("/iconfont/")||uri.contains("/image/")||uri.contains("/js/")||uri.contains("/layui/")||uri.contains("/CheckCodeServlet")){
			chain.doFilter(request, response);
		}else if(uri.contains("/login.jsp")){
			Object user = req.getSession().getAttribute("user");
			if(user!=null){
				res.sendRedirect(basepath+"jsp\\index.jsp");
			}else{
				chain.doFilter(request, response);
			}
		}else{
			Object user = req.getSession().getAttribute("user");
			if(user!=null){
				chain.doFilter(request, response);
			}else{
				res.sendRedirect(basepath+"login.jsp");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
