<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> --%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원 검색창</title>
</head>
<body>
<%--<form action="${pageContext.request.contextPath}/member2303" method="post"> --%>
	<form action="/pro23/member2303" method="post">
		입력 : <input  name="inputValue" type="text"  />
		<select name="action">
			<option value="selectAllMembersList" >전체</option>
			<option value="selectMemberById" >아이디</option>
			<option value="selectMembersByPassword">비밀번호</option>
		</select> <br>
	<input type="submit" value="검색"  />
	</form>   
</body>
</html>
