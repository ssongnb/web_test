package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러를 생성할 때는 맵핑형식을 확장자 패턴방식으로 변경한다..  ~~~.xxx 

//@WebServlet("/TestController")
@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doAction(request, response); -> doget, dopost 로 오든 doAction에서 처리하도록 설정
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		//myweb어쩌구하는 부분들
		
		String command = uri.substring(path.length());
		//substring -> 문자열을 잘라주는 
		
		System.out.println("URI정보 : "+ uri);
		System.out.println("ContextPath정보 : "+ path);
		
		System.out.println(command);
		
		if(command.equals("/controller/join.test")) {
			//... 회원관련 동작 ...
			System.out.println("조인 동작 구현.....");
			
		}else if(command.equals("/controller/login.test")) {
			// ....로그인 정보 처리 ....
			System.out.println("로그인 동작 구현");
		}else if(command.equals("/controller/update.test")) {
			// ....회원 정보 처리 ....
			System.out.println("회원정보 수정 동작 구현");
		}else if(command.equals("/controller/delete.test")) {
			// ....삭제 정보 처리 ....
			System.out.println("회원정보삭제 동작 구현");
		}
		
		
		
		
		
		
		
		
		
	}
	
}
