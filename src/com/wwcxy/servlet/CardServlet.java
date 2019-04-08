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
import com.wwcxy.entity.Card;
import com.wwcxy.service.CardService;

/**
 * Servlet implementation class CardServlet
 */
@WebServlet("/CardServlet")
public class CardServlet extends HttpServlet {
	CardService cs = new CardService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if("GetCardsByPage".equals(request.getParameter("flag"))){
			if(request.getParameter("likename").equals("")){
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				
				List<Card> list = cs.getAllCards(page,limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", cs.getCount());
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}else{
				int limit = Integer.parseInt(request.getParameter("limit")) ;
				int page = Integer.parseInt(request.getParameter("page")) ;
				String name = request.getParameter("likename");
				List<Card> list = cs.getLikeCard(name, page, limit);
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", cs.getLikeCount(name));
				map.put("data", list);
				ObjectMapper mapper = new ObjectMapper();
		        response.setContentType("application/json;charset=utf-8");
		        mapper.writeValue(response.getOutputStream(),map);
			}
		}else if("AddCard".equals(request.getParameter("flag"))){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String vip = request.getParameter("vip") ;
			int num=5;
			if("是".equals(vip)){
				num=10;
			}
			Card card = new Card(0, name, email, "正常", vip,num);
			cs.addUser(card);
			PrintWriter out = response.getWriter();       
	        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/card.jsp','_parent')</script>");      
		}else if("EditCard".equals(request.getParameter("flag"))){
			int id = Integer.parseInt(request.getParameter("cid")) ;
			Card card = cs.getCardById(id);
			HttpSession session = request.getSession();
			session.setAttribute("card", card);
			response.sendRedirect("jsp/cardedit.jsp");
		}else if("UpdateCard".equals(request.getParameter("flag"))){
			int id = Integer.parseInt(request.getParameter("id")) ;
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String status = request.getParameter("status");
			String vip = request.getParameter("vip") ;
			int num=5;
			if("是".equals(vip)){
				num=10;
			}
			Card card = new Card(id, name, email, status, vip,num);
			cs.updateById(card);
			PrintWriter out = response.getWriter();       
	        out.println("<script>window.open ('"+request.getContextPath()+"/jsp/card.jsp','_parent')</script>");
		}else if("DelCard".equals(request.getParameter("flag"))){
			int id = Integer.parseInt(request.getParameter("cid")) ;
			cs.delById(id);
			response.sendRedirect("jsp/card.jsp");
		}else if("DelSelect".equals(request.getParameter("flag"))){
			String cids = request.getParameter("cids");
			cs.DelSelect(cids);
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
