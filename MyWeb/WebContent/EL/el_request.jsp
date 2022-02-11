<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO vo = new UserVO();
	vo.setId("test111");
	vo.setName("홍길동");
	vo.setAddress("서울 서대문구 신촌");
	
	request.setAttribute("vo", vo);

	//포워드
	RequestDispatcher dp = request.getRequestDispatcher("el_request_ok.jsp");
	dp.forward(request, response);	
	
%>    
 
