<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트</title>
</head>

<body>
    <ul class="list_type">
        <li>
            <span style="marign-left:50px">이미지</span>
            <span>이미지 이름</span>
            <span>선택하기</span>
        </li>
<%
for (int i=0; i<10;i++){
%>
		<li>
			<a href='#' style='margin-left:50px'>
				<img src='/pro12/image/img7.jpg' width='90' height='90' alt='이미지 없음'>
			</a>
			<a href='#'><strong> 이미지 이름: 이미지<%=i %></strong></a>
			<a href='#'><input name='chk<%=i %>' type="checkbox"></a>
		</li>
<%
}
%>
	</ul>


</body>
</html>