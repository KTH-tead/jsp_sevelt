<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%-- 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/> 
--%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 검색창</title>
</head>
<body>
	<h1>회원검색</h1>
	<%-- <form action="${contextPath}/member2304.do" method="post"> --%>
	<!-- <form name="frmSeach" action="/pro23/member2304" method="get"> -->
	<form name="frmSearch">
	<!-- <input type="hidden" name="action" value="searchMember" /> -->
	<!-- <input type="hidden" name="action" value="selectLike" /> -->
	<input type="hidden" name="action" value="selectLike2" />
		이름 : <input  type="text" name="name" /><br>
		이메일 :<input  type="text" name="email" /><br>
		<input type ="button" value="검색" onClick="changeToUpperCase()">
	</form>

<script>
function changeToUpperCase(){
	//입력된 값을 변수에 저장
	var inputName=frmSearch.name.value;
	var inputEmail=frmSearch.email.value;
	
	//변수값을 대문자로 변환하여 input 요소의 값으로 저장
	frmSearch.name.value=inputName.toUpperCase() ;
	frmSearch.email.value=inputEmail.toUpperCase() ;
	
	frmSearch.method="get";
	frmSearch.action="/pro23/member2304";
	frmSearch.submit();
}

</script>
 
</body>
</html>