<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="java.util.List"
    	 import="java.sql.Date"
    	 import="sec02.ex03.MemberVO" 
    	 import="sec02.ex03.MemberDAO" %>
    	 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>회원 정보 출력창</title>
    <style>
    	h1 { text-align: center; }
    </style>
    
</head>
<body>
    <h1>회원 정보 출력</h1>
<%
    request.setCharacterEncoding( "utf-8" );

    String _name = request.getParameter("name");
    
    System.out.println("search.jsp에서 대문자로 변경된 이름:" + _name);
    
    MemberVO memberVO = new MemberVO();
    
    memberVO.setName(_name);
    
    MemberDAO memberDAO=new MemberDAO();
    
	List<MemberVO> memberList = memberDAO.listMembers(memberVO); 
	System.out.println("memberList 객체 정보:" + memberList);
	
%>
	
	<!-- <table border="1" width="800" align="center"> -->
	<!-- <table border="1" width="800px" style="margin:auto"> -->
	<table border="1" style="margin:auto; width:800px">
        <tr align='center' bgcolor='#FFFF66'> 
            <td>아이디</td>
            <td>비밀번호</td>
            <td>이름</td>
            <td >이메일</td>
            <td>가입일자</td>
        </tr>
<%	
	if(memberList.size() == 0){ 
%>
	<tr align=center>
  		<td colspan="5">찾는 회원의 정보가 없습니다.</td>
    </tr>
<%
	} else {
	    for (int i=0; i < memberList.size(); i++){
	    	
	        MemberVO memVO=(MemberVO) memberList.get(i);
	        
	        String id = memVO.getId();
	        String password = memVO.getPassword();
	        String name = memVO.getName();
	        String email = memVO.getEmail();
	        Date joinDate = memVO.getJoinDate();		
%>
	    <tr align=center>
    	    <td><%=id %></td>
        	<td><%=password %></td>
	        <td><%=name %></td>
    	    <td><%=email %></td>
        	<td><%=joinDate %></td>
     	</tr>
<%		
    	}
	}
%>
    </table>
    <br>
	<p style="text-align:center;">
		<a href="/pro07/search.jsp">회원 검색을 계속하시겠습니까?</a>
	</p>    
</body>
</html>
