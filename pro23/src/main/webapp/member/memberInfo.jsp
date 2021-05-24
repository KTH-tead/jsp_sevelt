<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="java.util.List"
    	 import="java.sql.Date"
    	 import="member.MemberVO" 
    	 import="member.MemberDAO" %>

<% request.setCharacterEncoding( "utf-8" ); %>

<!-- pro12의 회원의 이름 검색 결과를 표시하는 member.jsp 파일에, 
     pro12의 search.jsp 내용을 추가한 후, 기존의 pro09의 MemberListServlet의 결과 표시 기능을 수정함  -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 페이지</title>
	<style>
		td {padding:3px; border: 1px lightgrey solid;} 
		input, form {margin:0;}
	</style>
</head>
<body>
    <h3 style="text-align:center;"></h3>
	<table style="margin:auto; text-align:center; font-size:13px; border: 1px lightgrey solid;" >
		<tr style="margin:auto; text-align:center; font-size:16px; background-color:lightblue; padding:2px;">
		<td colspan="4">회원 정보</td>
		</tr>
		<tr style="margin:auto; text-align:center; font-size:15px; background-color:lightgreen ;">
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
		</tr>
 
 <%
 	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
 
 	if(memberVO == null){ 
%>
	<tr align=center>
		<td colspan="6">찾는 회원의 정보가 없습니다.</td>
	</tr>
<%
	} else {
	 			
			String id=memberVO.getId();				//태그를 이용해 리스트로 출력합니다.
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
%>		
			<tr>
				<td><%=id %></td>
		   		<td><%=name %></td>
		   		<td><%=email %></td>
		   		<td><%=joinDate %></td>
			</tr>
<%		
		}
%>
 	</table>
	<br><br>
	<h2 style='text-align:center;'><a href='/pro16/member/memberLogin.html'><button>로그아웃</button></a></h2>

</body>
</html>