<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page import = "javax.servlet.http.HttpSession" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/_header.jsp" %>

<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	/*
		1. login(id,pw)메서드 생성
			->executeQuery()메서드 사용, 그 결과값이 존재하는지 확인
		2. 결과값이 존재하면, 성공의 의미로 1을 반환
			결과값이 없다면, 실패의 의미로 0을 반환
		3. 실패인 경우, 경고창을 출력후, 뒤로가기
			성공인경우 user_mypage.jsp로 이동
		4. 성공시 user_mypage.jsp로 이동하기 전에  user_mypage.jsp에 전달할
			정보를 세션에 저장하여 전달(UserVO객체에 정보를 저장)
			UserDAO에 getUserInfo(id)메서드 생성 - id에 속한 정보를 저장하는 메서드
	*/
	
	//dao객체 생성
	UserDAO dao = UserDAO.getInstance();
	
	//dao의 login메서드사용
	int result = dao.login(id, pw);
	
	if(result == 1){
%>			
	<script>
		alert("로그인되었습니다.");
	</script>

<% 	
	UserVO vo = dao.getUserInfo(id);
 	String name = vo.getName();

 	//아이디의 이름을 세션에 저장
 	session.setAttribute("user_id", id);
 	session.setAttribute("user_pw",pw);
 	session.setAttribute("user_name",name);
			
	response.sendRedirect("user_mypage.jsp");

	}else{ %>
	
	<script>
		alert("로그인에 실패했습니다.\n 아이디 혹은 비밀번호를 확인해주세요.");
		history.go(-1);
	</script>

<%} %>

<%@ include file = "../include/_footer.jsp" %>