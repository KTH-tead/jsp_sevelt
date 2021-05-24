<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="java.sql.Date"
    	 import="com.mybatis.ex01.MemberVO"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	 
<% request.setCharacterEncoding( "utf-8" ); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보</title>
	<style>
		td {padding:3px; border: 1px lightgrey solid;} 
		input, form {margin:0;}
	</style>
</head>
<body>

	<table style="margin:auto; text-align:center; font-size:13px; border: 1px lightgrey solid;" >
		<tr style="margin:auto; text-align:center; font-size:16px; background-color:lightblue; padding:2px;">
		<td colspan="5">회원 정보</td>
		</tr>
		<tr style="margin:auto; text-align:center; font-size:15px; background-color:lightgreen ;">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
		</tr>
	<c:choose>
		<%-- <c:when test="${member==null}" > --%>
		<c:when test="${empty member}" >
			<tr>
				<td colspan=5>
				<b>해당 아이디를 사용하는 회원이 없습니다.</b>
				</td>  
			</tr>
		</c:when>
		<c:otherwise>
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.password}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>
		</tr>
		</c:otherwise>
	</c:choose>
 	</table>
</body>
</html>