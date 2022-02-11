<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/_header.jsp" %>
<%															
 %>
<section>

	<div align="center">
		<h3>비밀번호 변경 페이지</h3><hr><br>
		<form name="pwchange" action="user_change_pw_ok.jsp" method="post">
		
			현재 비밀번호:<input type="password" name="old_pw"><input type="button" value="확인" onclick="check()"><br>
			변경 비밀번호:<input type="password" name="new_pw"><input type="button" value="확인" ><br>
			<input type="submit" value="확인" class="btn btn-primary">
			<input type="button" value="취소" class="btn btn-info" onclick="history.go(-1)">
		
		</form>
	</div>

</section>

<%@ include file="../include/_footer.jsp" %>
</body>
</html>