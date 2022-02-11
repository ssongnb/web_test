<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- for Tokens 태그
	: 자바의 StringTokenizer를 JSTL사용하여 구현하여 쓸 수 있다.
	첫번째 토큰->첫번째문자 :토큰- 글자를 분리해서 
	
	 -->
	<h2>forTokens 태그</h2>
	
	<c:forTokens var ="abc" items="안녕/하세요./ 지금은 /JSP/수업/ 시간/입니다." delims="/">
		${abc }<br>
	</c:forTokens> 
	
	<hr>
	
	<!-- redirect태그 : 특정값을 지정된 url로 전달 할 때 사용 -->
	<h2>redirect 태그</h2>
	 <c:redirect url="jstl_test.jsp">
	 	<c:param name="test" value="안녕 난 redirect태그야!!!" />
	 </c:redirect>
	 <!-- get방식 -->
</body>
</html>