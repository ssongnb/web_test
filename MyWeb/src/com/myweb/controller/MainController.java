package com.myweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		if(command.equals("/member.do")) {
			RequestDispatcher dp = request.getRequestDispatcher("member/member.jsp");
			dp.forward(request, response);
		}else {
			RequestDispatcher dp = request.getRequestDispatcher("index.jsp");
			dp.forward(request, response);
		}
		
		//RequestDispatcher dp = request.getRequestDispatcher("index.jsp");
		//dp.forward(request, response);
		
	}


	
	
}
