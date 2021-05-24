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
	<title>회원관리 페이지</title>
	<style>
		td {padding:3px; border: 1px lightgrey solid;} 
		input, form {margin:0;}
	</style>
</head>
<body>
	<h1 style="text-align:center;">회원정보 조회 </h1>
	<form name="frmSeachName" style="text-align:center;">
		<input type="text" name="name" placeholder="검색할 이름을 입력하세요...">&nbsp;&nbsp;
		<input type ="button" value="조회하기" onClick="changeToUpperCase()">
	</form> 
	
    <h3 style="text-align:center;"></h3>
	<table style="margin:auto; text-align:center; font-size:13px; border: 1px lightgrey solid;" >
		<tr style="margin:auto; text-align:center; font-size:16px; background-color:lightblue; padding:2px;">
		<td colspan="6">회원 정보 조회결과</td>
		</tr>
		<tr style="margin:auto; text-align:center; font-size:15px; background-color:lightgreen ;">
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
			<td>회원삭제</td>
		</tr>
 
 <%
	@SuppressWarnings("unchecked")
	List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
 
 	if(memberList.size() == 0){ 
%>
	<tr align=center>
		<td colspan="6">찾는 회원의 정보가 없습니다.</td>
	</tr>
<%
	} else {
	 
		for (int i=0; i<memberList.size();i++){
			
			MemberVO memberVO=(MemberVO) memberList.get(i); 	//조회한 회원 정보를 for문과 <tr>
			
			String id=memberVO.getId();				//태그를 이용해 리스트로 출력합니다.
			String password = memberVO.getPassword();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
	%>		
			<tr>
				<td><%=id %></td>
			    <td><%=password %></td>
		   		<td><%=name %></td>
		   		<td><%=email %></td>
		   		<td><%=joinDate %></td>
		   		<td>
					<form method="post" action="/pro16/memberDel16">
						<input type="hidden" name="id" value=<%=id %>>
						<input type="submit" value="회원삭제">
					</form>
		   		</td>
			</tr>
	<%		
		}
	}
	%>
 
	</table>
	<br><br>
	<h2 style='text-align:center;'><a href='/pro16/member/memberLogin.html'><button>관리자 로그아웃</button></a></h2>

<script type="text/javascript">
	
	//이름이 영문인 경우, 입력된 이름의 대소문자 구분이 필요없도록
	//이름을 모두 대문자로 변환하는 함수
	function changeToUpperCase(){
		//입력된 이름을 변수에 저장
		
		if(frmSeachName.name.value != null && frmSeachName.name.value != "" ) {
			var inputName=frmSeachName.name.value;
		
			//변수값을 대문자로 변환하여 input 요소의 값으로 저장
			frmSeachName.name.value=inputName.toUpperCase() ;
		}
			frmSeachName.method="post";
			frmSeachName.action="/pro16/memberList16";
			frmSeachName.submit();
			
	}
</script>
</body>
</html>