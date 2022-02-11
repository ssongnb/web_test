<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../include/_header.jsp" %>

<!-- join(회원가입)을 위한 form태그 작성 -->

	<script type="text/javascript">
	
			function id_check(){
				
			}
		
	</script>

	<section>
	<div align="center">
		<form name="regform" action="user_join_ok.jsp" method="post">
			<h2>회원가입 페이지</h2>
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" placeholder="4~8글자 입력">
						<input type="button" value="아이디 중복 확인" class="id_check"></td>
					
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="password" name="pw"></td>
				
				</tr>
				<tr>
					<td>PW확인</td>
					<td><input type="password" name="pw_check"></td>
				
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name="name"></td>
				
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email"></td>
				
				</tr>
				<tr>
					<td>address</td>
					<td><input type="text" name="address"></td>
				
				</tr>
			</table>
			<input type="button" value="회원가입" class="btn btn-primary" onclick="check()">
			<input type="button" value="로그인" class="btn btn-info" onclick="javascript:location.href='user_login.jsp'">
		</form>
	</div>
</section>

<%@ include file="../include/_footer.jsp" %>

<script type="text/javascript">
	function check(){
		//form은  document.태그이름.태그이름으로 하위태그 접근이 가능.
		//console.log (document.regform.id.value);;을 이용하여 접근확인 가능
		
		if(document.regform.id.value == ''){
			// required
			alert("아이디는 필수 사항입니다.");
			return;
		}else if(document.regform.id.value.length < 4 || document.regform.id.value.length >8){
			alert("아이디는 4~8글자 내로 입력하세요");
			return;
		}else if(document.regform.pw.value == ''){
			alert("비밀번호는 필수 사항입니다.");
			return;
		}else if(document.regform.pw.value != document.regform.pw_check.value){
			alert("비밀번호확인을 확인해주세요.");
			return;
		}else if(document.regform.name.value == ''){
			alert("이름은 필수 사항입니다.");
			return;
		}else if(confirm("회원가입을 하시겠습니까?")){
			//confirm()은 확인창에 "예"를 클릭하면 true를 반환하고 "아니요"클릭시 false를 반환
			document.regform.submit(); //자바스크립트를 이용한submit()기능
			
			}
		}
			
</script>

</html>