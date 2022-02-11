<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script type="text/javascript">


<!-- 1.registCheck()구현하고 작성자,글제목에 공백을 확인하고ㅡ 공백이 아니라면 submit출력  --> 
		
	function registCheck(){
		if(document.regform.writer.value == ''){
			alert("작성자를 입력하세요");
			location.href="board_write.jsp";
		}else if(document.regform.title.value == ''){
			alert("글제목을 입력하세요");
			return;
		}else if(confirm("게시글을 등록하겠습니까?")){
			document.regform.submit();
		}
	}
</script>

<body>
<%@ include file = "../include/_header.jsp" %>

<section>

	<div align="center">
	
		<form name="regform" action="register.board" method="post">
			<h2> 게시판 글작성 페이지</h2>
			<hr>
			
			<table board="1" width="500">
				<tr>	
					<td>작성자</td>
					<td><input type="text" name="writer" size="10"></td>
				</tr>
				<tr>	
					<td>글제목</td>
					<td><input type="text" name="title" size="10"></td>
				</tr>
				<tr>	
					<td>글내용</td>
					<td>
						<textarea rows="10" style="width:100%;" name="content"></textarea>
					</td>
				</tr>
				<tr>	
					<!-- 글 등록 메유 -->
					<td colspan="2" align="center">
						<input type="submit" value="등록" onclick="registCheck()">
						<input type="button" value="목록" onclick="javascript:location.href = 'list.board'">
					</td>
				</tr>
			</table>
		
		</form>
	
	</div>

</section>



<%@ include file = "../include/_footer.jsp" %>


</body>
</html>