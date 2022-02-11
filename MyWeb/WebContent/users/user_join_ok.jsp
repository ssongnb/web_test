<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="../include/_header.jsp" %>

<%

	/*
		진행흐름 : 
			1. 전달받은 값을 처리
			2. 회원가입 여부를 확인
			3. 가입된 경우 실패처리(뒤로가기 historygo -1) || 가입이 안된경우 -> 가입을 진행("join()")
			4. 회원가입 축하 및 로그인 페이지로 이동하는 페이지
			5. 실패시 재가입 페이지로 이동 

	*/	
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	//DAO객체 생성
	UserDAO dao = UserDAO.getInstance();
	
	//VO객체 생성
	UserVO vo = new UserVO(id,pw,name, email, address, null);

	//id중복 검사
	int result = dao.IdConfirm(id);
	
	if(result == 1){	//아이디 중복
%>
	<script>
		alert("이미 존재하는 ID 입니다.");
		historygo(-1);	//뒤로가기
	</script>
<%	}else{	//중복이 없는 경우
			//insert쿼리를 진행(회원가입 - join())
		int result2 = dao.join(vo);	
			if(result2 == 1){
		%>
			<script>
				alert("회원가입을 축하합니다.");
				location.href="user_login.jsp";
			</script>	
		<% }else{	%>
				<script>
				alert("회원가입에 실패했습니다..");
				history.go(-1);	//뒤로가기
				</script>	 
<%			}
}	
	
%>

<%@ include file = "../include/_footer.jsp" %>