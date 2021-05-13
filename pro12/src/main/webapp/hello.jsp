<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! String name="Eddy";
    	public String getName(){return name;} %>
    <% String age=request.getParameter("age"); %>
    
    
<!DOCTYPE html>
<%-- <!--  html 주석문 입니다. -->  --%>
<html>
<head>
<meta charset="UTF-8">
<title>선언문 연습</title>
</head>
<body>
 <h1>안녕하세요 <%=name %>님!!</h1>
  <h2>나이는 <%=age %>세 입니다!!</h2>
  <h2>나이는 <%=170 %>cm 입니다!!</h2>
  <h2>나이+10은 <%=Integer.parseInt(age)+10%> 세 입니다!!</h2>
</body>
</html>