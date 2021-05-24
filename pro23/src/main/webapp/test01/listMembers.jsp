<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="java.util.List"
    	 import="java.sql.Date"
    	 import="com.mybatis.ex01.MemberVO" 
    	 import="com.mybatis.ex01.MemberDAO" %>
    	 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding( "utf-8" ); %>


<%-- <c:set var="contextpath" value="${pageCibtext,request.contextPath}"/> --%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록 페이지</title>
	<style>
		td {padding:3px; border: 1px lightgrey solid;} 
		input, form {margin:0;}
	</style>
</head>
<body>

	<table style="margin:auto; text-align:center; font-size:13px; border: 1px lightgrey solid;" >
		<tr style="margin:auto; text-align:center; font-size:16px; background-color:lightblue; padding:2px;">
		<td colspan="5">전체 회원 목록</td>
		</tr>
		<tr style="margin:auto; text-align:center; font-size:15px; background-color:lightgreen ;">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
		</tr>
	<c:forEach var="member" items="${membersList}" >     
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.password}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>
		</tr>
	</c:forEach>
 	</table>
</body>
</html>