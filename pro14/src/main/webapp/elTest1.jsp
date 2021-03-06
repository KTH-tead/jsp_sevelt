<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>표현언어에서 사용되는 데이터들</title>
</head>
<body>
	<h1>표현언어로 여러 가지 데이터 출력하기</h1>
	<h1>
	 <!-- 직접 값을 지정합니다. -->
	 \${100}: ${100}<br>
	 \${"안녕하세요"}: ${"안녕하세요"}<br> 
<!-- 값에 연산자를 사용하여 결과를 표시합니다. 
-->
	 \${10+1}: ${10+1}<br>
	 <!-- 표현식에서는 숫자로 된 문자 + 숫자의 경우, 숫자가 문자로 변형된 후, 처리됩니다. -->
	 &nbsp;&nbsp;&nbsp;&nbsp;<%="10"+1 %><br>  <!-- 101  -->
	 <!-- 표현언어에서는 숫자로 된 문자 + 숫자의 경우, 숫자로 된 문자가 숫자로 변형된 후, 처리됩니다. -->
	 \${"10"+1} : ${"10"+1 }<br><!-- 11 -->


	 <!-- null + 숫자는 숫자가 그대로 표시됩니다. -->
	 <!-- null * 숫자 : null -->
	\$1{5 + Null + 10 }: ${5 + Null +10 }<br><!-- 15 -->
	\$2{5 * Null + 10 }: ${5 * Null +10 }<br><!-- 10 -->
	\$2{5 * Null }: ${5 * Null }<br><!-- 0 -->
	\$3{5 + Null * 10 }: ${5 + Null *10 }<br><!-- 5 -->
	\$4{(5 + Null) * 10 }: ${(5 + Null) *10 }<br><!-- 50 -->
	\$5{5 * Null * 10 }: ${5 * Null *10 }<br><!-- 0 -->
	\$6{5 * Null / 10 }: ${5 * Null /10 }<br><!-- 0.0 -->
	\$7{5 / Null}: ${5 / Null}<br><!-- Infinity -->
	\$7{5 / Null * 10 }: ${5 / Null *10 }<br><!-- Infinity -->
	\$8{5 / (Null * 10) }: ${5 / (Null *10) }<br><!-- Infinity -->
	\$9{5 / 0 }: ${5 / 0 }<br> <!-- Infinity -->
	<%-- \${5 % NULL }: ${5 % null }<br> --%>  <!-- 오류발생 java.lang.ArithmeticException: / by zero -->
	
	<!-- 숫자가 아닌 문자와 + 숫자의 경우, 오류가 발생됩니다. -->
	<!-- 문자 + 문자 연산은 지원되지 않습니다. -->
 	<%-- \${"안녕"+11 }: ${"안녕"+11 }<br> --%> 
	<%-- \${"hello"+"world"}:${"hello"+"world"}<br>  --%>
	 </h1>
	<hr>
	
	<h1>여러 가지 산술 연산자: elTest2.jsp<br><br>
		\${10+10}  : ${10+10} <br>
		\${20-10}  : ${20-10}  <br>
		\${10*10}  : ${10*10} <br>
		\${100/9} : ${100/9} <br>
		<%-- \${100 div 9} : ${100 div 9} <br> --%>
		\${100%9} : ${100%9}<br>
		\${100 mod 9} : ${100 mod 9}<br>
	</h1>
	<hr>
	<h1>여러 가지 비교 연산자: elTest3.jsp<br><br>

		\${10==10} : ${10==10} <br>
		\${10 eq 10} : ${10 eq 10} <br><br>
		\${"hello"=="hello"} : ${"hello"=="hello"} <br>
		\${"hello"=="Hello"} : ${"hello"=="Hello"} <br>
		\${"hello" eq "hello"} : ${"hello" eq "hello"} <br><br>
		
		\${20!=10} : ${20!=10}<br>
		<%-- \${20 ne 10} : ${20 ne 10}<br><br> --%>
		\${"hello"!="apple"} : ${"hello"!="apple"} <br>
		<%-- \${"hello" ne "apple"} : ${"hello" ne "apple"} <br><br> --%>
		\${10 < 10} : ${10 < 10} <br>
		\${10 lt 15} : ${10 lt 15} <br><br>
		\${100>10} : ${100 > 10}<br>
		\${100 gt 10} : ${100 gt 10}<br><br>
		\${100 <=10} : ${100 <= 10}<br>
		\${100 le 10} : ${100 le 10}<br><br>
		\${100 >=10} : ${100 >= 10}<br>
		<%-- \${200 ge 50} : ${200 ge 50}<br><br> --%>
	</h1>
	<hr>
	<h1>여러가지 논리연산자: elTest4.jsp<br><br>
		\${(10==10) && (20==20)}  : ${(10==10)&&(20==20)} <br>
		
		\${(10==10) and (20!=20)}  : ${(10==10) and (20!=20)} <br><br>
		
		\${(10==10) || (20!=30)}  : ${(10==10)||(20==30)} <br>
		\${(10!=10) or (20!=20)}  : ${(10!=10) or (20!=20)} <br><br>
		
		\${!(20==10)}  : ${!(20==10)}<br>
		\${not (20==10)}  : ${not (20==10)}<br><br>
		
		\${!(20!=10)}  : ${!(20!=10)}<br>
		\${not(20!=10)}  : ${not(20!=10)}<br><br>       
	</h1>
	
	
   
</body>
</html>
