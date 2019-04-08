package com.wwcxy.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.prism.Image;


/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 100;
		int height = 50;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);//为画布创建背景颜色
        g.fillRect(0, 0, width,height); //fillRect:填充指定的矩形
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width-1, height-1);
         
        char[] ch="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//转化为字符型的数组
        Random r=new Random();
        int len=ch.length;
        int index; //index用于存放随机数字
        StringBuffer sb=new StringBuffer();
        Font f = new Font("宋体",Font.BOLD,30);
        g.setFont(f);
        for(int i=1;i<=4;i++)
        {
            index=r.nextInt(len);//产生随机数字
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));  //设置颜色
            g.drawString(ch[index]+"",width/5*i-5, height/2+10);//画数字以及数字的位置
            sb.append(ch[index]);
        }
        g.setColor(Color.GREEN);
        for (int i = 0; i <10; i++) {
        	int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
		}
        
        
        
        
        
        request.getSession().setAttribute("checkcode",sb.toString()); //将数字保留在session中，便于后续的使用
        ImageIO.write(image, "JPG", response.getOutputStream()); 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
