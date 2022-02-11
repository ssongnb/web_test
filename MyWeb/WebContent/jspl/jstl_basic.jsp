<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<%
	String test = "문자열 변수입니다.";
	int num = 100;

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 출력태그 -->
	test변수값: <c:out value="<%= test %>"></c:out><br>
	test변수값: <c:out value="<%= test %>"/><br>
	
	num변수(숫자) 값 : <c:out value="${num}"></c:out><br>
	->문자가 아니라 출력이 안됨<br>

	<!-- 변수 선언 태그 -->
	<c:set var="var1" value="100" scope="page" /><hr>
	var1 변수의 값 : ${var1 }<br>
	
	<!-- 변수 제거 태그 -->
	<c:remove var="var1" scope="page" />
	<hr>변수 제거후 결과<br>
	var1 변수의 값 : ${var1 }<br>
	
	


</body>
</html>