<%@page import="javax.websocket.SendResult"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		1. 폼값 처리
		2. login(id, 예전비밀번호) 아이디로 비밀번호가 밪는지 확인
			예전비밀번호가 틀리면 뒤로가기
		3. 일치하면  changePassword()메소드사용
		4. 성공하면 '비밀번호 변경처리 되었습니다.'를 출력하고 user_mypage.jsp로 이동
			실패하면 '비밀번호 변경에 실패했습니다.'를 출력하고 user_mypage.jsp로 이동
	*/
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("user_login.jsp");
	}
	
	String id = (String)session.getAttribute("user_id");
	String old_pw = request.getParameter("old_pw");
	String new_pw = request.getParameter("new_pw");

	//dao
	UserDAO dao = UserDAO.getInstance();
	
	//changePassword()메소드사용
	int result = dao.changePassword(id, old_pw, new_pw);
	
	if(result == 1){%>    
    <script type="text/javascript">
		alert("비밀번호 변경처리 되었습니다.");
		location.href="user_mypage.jsp";
	</script>
<% }else{ %>    
  	<script>
  		alert("비밀번호 변경에 실패했습니다.");
		location.href="user_mypage.jsp";
  	</script>
<% } %>  



