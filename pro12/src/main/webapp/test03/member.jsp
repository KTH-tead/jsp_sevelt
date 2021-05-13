<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="java.sql.Date"
    import="pro12.sec02.ex01.MemberVO"
    import="pro12.sec02.ex01.MemberDAO"    
    %>
<!DOCTYPE html>
<html>
<head>
<style>
 h1 { text-align:center;}
</style>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
<h1> 회원 정보 출력</h1>
<% request.setCharacterEncoding("utf-8");
	String _name = request.getParameter("name");
	System.out.println("search.jsp에서 대문자로 변경된 이름:" + _name);
	MemberVO memberVO = new MemberVO();
	memberVO.setName(_name);
	MemberDAO dao=new MemberDAO();
	List memberList=dao.listMembers(memberVO);
	System.out.println("memberList 객체 정보:" + memberList);
%>
<table border=1 style="margin:auto; width:800px">
	<tr align=center bgcolor="#ffff66">
	<td>아이디</td>
	<td>비밀번호</td>
	<td>이름</td>
	<td>> 이메일</td>
	<td>가입일자</td>
</table>

<% for(int i=0; i<memberList.size(); i++){
	MemberVO vo=(MemberVO) memberList.get(i);
	String id=vo.getId();
	String pwd=vo.getPassword();
	String name=vo.getName();
	String email=vo.getEmail();
	Date joinDate=vo.getJoinDate();
	%>
	
	<tr align=center>
	<td><%=id %></td>
	<td><%=pwd %></td>
	<td><%=name %></td>
	<td><%=email %></td>
	<td><%=joinDate %></td>
	</tr>
	
<%
}
%>	
</table>

<p style="text-align:center;">
		<!-- <a align="center" href="/pro12/test03/search.jsp">회원 검색을 계속하시겠습니까?</a> -->
		<!-- <a style="margin:auto" href="/pro12/test03/search.jsp">회원 검색을 계속하시겠습니까?</a> -->
		<a href="/pro12/test03/search.jsp">회원 검색을 계속하시겠습니까?</a>
</body>
</html>