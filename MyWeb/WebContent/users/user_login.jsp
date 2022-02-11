<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.http.HttpSession" %>


<%
	

%>


	<%@ include file="../include/_header.jsp" %>
	
	<div align = "center">
		<form name="login" action="user_login_ok.jsp" method="post">
			<h2>로그인페이지</h2><hr>
				<input type="text" name="id" placeholder="아이디 4~8글자 입력"><br>
				<input type="password" name="pw" placeholder="비밀번호 입력"><br>
			<input type="submit" value="로그인" class="btn btn-info" onclick="check()">	
			<input type="button" value="회원가입" class ="btn btn-primary" onClick="javascript:location.href='user_join.jsp'">	
		</form>
		<form action="user_join.jsp">
			
		</form>
	</div>
	
	<%@ include file="../include/_footer.jsp" %>

<script type="text/javascript">

	function check(){
		if(document.login.id.value == ''){
			alert("아이디 값을 입력하세요");
			return;
		}else if(document.login.id.value.length <4 || document.login.id.value.length >8){
			slert("아이디는 4~8글자 내로 입력해 주세요");
			return;
		}else if(document.login.pw.value == ''){
			alert("비밀번호를 입력하세요");
			return;
		}
	}

</script>

