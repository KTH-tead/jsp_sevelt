<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("utf-8"); %>

<%
	request.setAttribute("address","서울시 강남구");
	session.setAttribute("tel","010-2222-3333");
	application.setAttribute("age","52살");

%>    

<%--
	request.setAttribute("address","서울시 강남구");
	session.setAttribute("tel","010-2222-3333");
	application.setAttribute("age","52살");

--%>   


<html>
<head>
   <meta charset="UTF-8">
   <title>forward</title>
</head>
<body>
	<!-- page 속성에 컨텍스트 이름을 포함시키면 않됩니다. -->
   <jsp:forward page="/test01/member2.jsp"></jsp:forward>
</body>
</html>
