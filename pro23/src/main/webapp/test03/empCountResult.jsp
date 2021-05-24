<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"  %>
    	 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding( "utf-8" ); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원채용통계</title>
	<style>
		td {padding:3px; border: 1px lightgrey solid;} 
		input, form {margin:0;}
	</style>
</head>
<body>   <!-- http://172.16.5.31:8080/pro23/member2304?action=hiredCountEmployees -->

	<table style="margin:auto; text-align:center; font-size:13px; border: 1px lightgrey solid;" >
		<tr style="margin:auto; text-align:center; font-size:16px; background-color:lightblue; padding:2px;">
		<td colspan="2">직원채용통계</td>
		</tr>
		<tr style="margin:auto; text-align:center; font-size:15px; background-color:lightgreen ;">
			<td>채용기간</td>
			<td>채용인원</td>
		</tr>
		<c:forEach var="perMonthCount" items="${hiredCountList}" >     
			<tr align="center">
				<td>${perMonthCount.hiredInterval}</td>
				<td>${perMonthCount.persons}</td>
			</tr>
		</c:forEach>		
 	</table>
</body>
</html>