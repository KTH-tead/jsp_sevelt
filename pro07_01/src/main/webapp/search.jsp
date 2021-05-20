<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 검색창</title>
</head>
<body>
	<h2>회원 검색</h2>
	<p>회원이름을 입력하세요</p>
<!-- 
	<form name="frmSeachName" method="post" action="/pro07/member.jsp">
		이름: <input type="text" name="name"><br><br>
		<input type ="submit" value="조회하기">
	</form>
 -->
	<form name="frmSeachName">
		<input type="text" name="name" placeholder="이름을 입력하세요...">&nbsp;&nbsp;
		<input type ="button" value="조회하기" onClick="changeToUpperCase()">
		<button type = "button" onClick="changeToUpperCase()">조회하기</button>
	</form> 
	
<script type="text/javascript">
	
	//이름이 영문인 경우, 입력된 이름의 대소문자 구분이 필요없도록
	//이름을 모두 대문자로 변환하는 함수
	function changeToUpperCase(){
		//입력된 이름을 변수에 저장
		var inputName=frmSeachName.name.value;
		
		//변수값을 대문자로 변환하여 input 요소의 값으로 저장
		frmSeachName.name.value=inputName.toUpperCase() ;
		
		frmSeachName.method="post";
		frmSeachName.action="/pro07/member.jsp";
		frmSeachName.submit();
	}
</script>
</body>
</html>
