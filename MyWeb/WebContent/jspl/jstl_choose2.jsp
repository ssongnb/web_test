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
	<!-- choose절 이용 90이상 a, 80이상 b, 70이상 c, 나머지는 f -->
	<c:choose>
		<c:when test="${param.score >= 90 }">
			A
		</c:when>
		<c:when test="${param.score >= 80 }">
			B
		</c:when>
		<c:when test="${param.score >= 70 }">
			C
		</c:when>
		
		<c:otherwise>
			F
		</c:otherwise>
	
	</c:choose>
	
</body>
</html>