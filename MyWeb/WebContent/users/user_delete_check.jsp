<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file = "../include/_header.jsp" %>
<%
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("user_login.jsp");
	}
%>
<section>
	<div align="center">
		<h3>회원탈퇴</h3>
		<h4>비밀번호 확인창</h4><hr>
		<form action="user_delete_ok.jsp" method="post">
			<input type ="password" name="pw" placeholder="비밀번호를 입력하세요">
			<input type="submit" value="확인" class="btn btn-info"><br><br>
		</form> 
	</div>
</section>

<%@ include file = "../include/_footer.jsp" %>
</html>