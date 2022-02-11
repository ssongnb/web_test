<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../include/_header.jsp" %>

<!-- update 을 위한 form태그 작성 -->
<%
	/*
		이 페이지에 들어올 때, getUserInfo()메서드를 재활용해서 정보를 불러온 후에 input태그에 값이 보여지도록 처리
		id태그는 변경 불가, pw는 안보이도록 처리
	*/
	
	//url강제 접속 거부
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("user_login.jsp");
	}

	String id = (String)session.getAttribute("user_id");
	
	//객체생성
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = dao.getUserInfo(id);  
	request.setAttribute("vo",vo);
	
%>

	<section>
	<div align="center">
		<form name="regform" action="user_update_ok.jsp" method="post">
			<h2>회원정보 수정페이지</h2>
			<table>
				<tr>
					<td>ID</td><!-- disabled는 값을 안넘김 -->
					<td><input type="text" name="id" value="${sessionScope.user_id }" readonly>
					
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name="name" value="${requestScope.vo.name }"></td>
				
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="${requestScope.vo.email}"></td>
				
				</tr>
				<tr>
					<td>address</td>
					<td><input type="text" name="address" value="${requestScope.vo.address}"></td>
				
				</tr>
			</table>
			<input type="button" value="수정" class="btn btn-primary" onclick="check()">
			<input type="button" value="취소" class="btn btn-info" onclick="javascript:location.href='user_mypage.jsp'">
		</form>
	</div>
</section>

<%@ include file="../include/_footer.jsp" %>

<script type="text/javascript">
	function check(){
		//form은  document.태그이름.태그이름으로 하위태그 접근이 가능.
		//console.log (document.regform.id.value);;을 이용하여 접근확인 가능
		
		if(document.regform.name.value == ''){
			alert("이름은 필수 사항입니다.");
			return;
		}else if(confirm("수정을 하시겠습니까?")){
			//confirm()은 확인창에 "예"를 클릭하면 true를 반환하고 "아니요"클릭시 false를 반환
			document.regform.submit(); //자바스크립트를 이용한submit()기능
			
			}
		}
			
</script>

</html>