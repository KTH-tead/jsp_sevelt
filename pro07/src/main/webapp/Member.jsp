<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.List"
    import="java.sql.Date"
    import="sec02.ex03.MemberVO"
    import="sec02.ex03.MemberDAO"
    
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
<style>
 h1 {
 text-align:center;
 }
</style>
</head>

<body>
	<h1>회원 정보 출력</h1>
	<% request.setCharacterEncoding("utf-8");
		String _name = request.getParameter("name");
		MemberVO memverVO = new MemberVO();
		memverVO.setName(_name);
		MemberDAO dao=new MemberDAO();
		List memberList=dao.listMembers(memverVO);		
		%>
	<table border=1 width=800 align=center >
		<tr align=center bgcolor="#ffff66">
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>이메일</td>
		<td>가입일자</td>
		</tr>
		
		<%
			if (memberList.size()==0){
			%>
			<tr align=center>
				<td colspan="5">찾는 회원의 정보가 없습니다.</td>
		<% }else {
			for (int i =0; i<memberList.size();i++) {
			MemberVO vo=(MemberVO) memberList.get(i);
			String id=vo.getId();
			String pwd=vo.getPassword();
			String name=vo.getName();
			String email=vo.getEmail();
			Date joinDate=vo.getJoinDate();			
			%>
			
			<tr align=center>
			<td><%= id %></td>
			<td><%= pwd %></td>
			<td><%= name %></td>
			<td><%= email %></td>
			<td><%= joinDate %></td>
			</tr>
			
			<%
			}
		}
			%>	
	</table>
	<br>
	<p style="text-algin">
	<a href="search.jsp">다른 이름으로 검색하기</a>
</body>
</html>