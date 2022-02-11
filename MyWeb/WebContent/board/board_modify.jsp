<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.test{
		background-color : white;
	}


</style>

</head>
<body>

<%@ include file = "../include/_header.jsp" %>
	<section>
		<div align="center">	
		<form name="regform" action="update.board" method="post">
			<table border="1" width="500" class="test">
				<tr>
					<td width="20%">글번호</td>
					<td width="30%"><input type="text" name ="num" value="${vo.num}" readonly></td><!-- DB로부터 전달받을 부분 -->
					<td width="20%">작성자</td>
					<td width="30%"><input type="text" name ="writer" value="${vo.writer }" readonly></td><!-- DB로부터 전달받을 부분 -->
				</tr>
				<tr>
					<td>제목</td>
					<td colspan = "3"><input type="text" name ="title" style="width:100%;" value="${vo.title }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3" height="120"> 
					<textarea rows="12" style="width:100%;" name="content">
						${vo.content } 
					</textarea>
					</td><!-- DB로부터 전달받을 부분 -->
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="수정하기" onclick="modifyCheck()">
						<input type="button" value="목록" onclick="javascript:location.href ='list.board'">
						<input type="button" value="삭제" onclick="delete_check()">
<!-- onclick="(confirm('삭제하시겠습니까?'))? javascrip:location.href='delete.board?num=${vo.num}' : ''"  삼항연산식을 이용해도 가능-->
					</td>
				</tr>
			</table>
		</form>
		</div>
	</section>
<%@ include file = "../include/_footer.jsp" %>

	<script type="text/javascript">
		function modifyCheck(){
			//작성자, 글제목의 공백을 확인하고, 공백이 아니라면 submit()처리
			if(document.regform.title.value == ''){
				alert("제목을 입력하세요");
				return;
			}else if(confirm("게시글 수정하겠습니까?")){
				document.regform.submit();
			}
			
		}
		
		function delete_check(){
			if(confirm("삭제하시겠습니까?") == true){
				location.href="delete.board?num=${vo.num}";
			}else {
				return;
			}
		}
	</script>

</body>
</html>