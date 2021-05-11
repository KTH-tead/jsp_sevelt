//package sec02.ex01;

//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/calc")
//public class CarcServlet1 extends HttpServlet {
//	
//	private static float USE_RATE=1124.70F;
//	private static float JPY_RATE=1124.70F;
//	private static float CNY_RATE=1124.70F;
//	private static float GBP_RATE=1124.70F;
//	private static float EUR_RATE=1124.70F;
//			
//	
//	private static final long serialVersionUID = 1L;
//       
//
//    public CarcServlet1() {
//        super();
//    }
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		String command = request.getParameter("Command");
//		String Won = request.getParameter("won");
//		String operator = request.getParameter("operator");
//		
//		if (command != null && command.equals("calculate"))
//		{
//			String result = calculate(Float.parseFloat(won), operatator);
//			pw.print("<html><font size=10>변환 결과</font><br>");
//			pw.print("<font size=10>" +result+ "</font><br>");
//			pw.print("<a href="/pro06/calc">환율 계산기</a></html>");
//			return;
//		}
//		
//		pw.print("<html><title>환율 계산기</title>");
//		pw.print("<font size=5>환율 계산기</font>");
//		pw.print("<form name='frmCalc' method='get' action='/pro06/calc'/>");
//		pw.print("원화 <input type='text' name='won' size=10>");
//		pw.print("<font size=5>환율 계산기</font>");
//		pw.print("<font size=5>환율 계산기</font>");
//		pw.print("<font size=5>환율 계산기</font>");
//		pw.print("<font size=5>환율 계산기</font>");
//		
//		
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//
//
//}


package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc060202")
public class CalcServlet1 extends HttpServlet {
   
  private static float USD_RATE = 1124.70F;
  private static float JPY_RATE = 10.113F;
  private static float CNY_RATE = 163.30F;
  private static float GBP_RATE = 1444.35F;
  private static float EUR_RATE = 1295.97F;
   
   protected void doGet(HttpServletRequest request, 
                   HttpServletResponse response) 
                         throws  ServletException, IOException {

      //웹 브라우저에서 전송된 데이터의 인코딩을 설정합니다.            
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      PrintWriter pw = response.getWriter();
      String command = request.getParameter("command");
      String won = request.getParameter("won");
      String operator = request.getParameter("operator");
      //응답으로 웹브라우저에 보낼 데이터 종류가 HTML(utf-8인코딩)임을 설정합니다.      

            
      if (command != null && command.equals("calculate"))
      {String result = calculate(Float.parseFloat(won),operator);
      pw.print("<!DOCTYPE html>");
      pw.print("<html><head>");
      pw.print("<title>환율 계산기 </title>");
      pw.print("<font size= 5> 환율 계산기</font><br>");
      pw.print("</head>");
      pw.print("<body>");
      pw.print("<font size =10>변환 결과</font><br>");
      pw.print("<font size =10>"+result+ "</font><br>");
      pw.print("<a href='/pro06/calc060202'>환율 계산기로 돌아가기</a>");      
      pw.print("</body>");
      pw.print("</html>");
      return;
      }
      
      
      String strHtml = 
    		  "<!DOCTYPE html>"
    		  +" <html>"
    		  +"<head>"
    		  +" <meta charset=\"UTF-8\">"
    		  +" <title>환율 계산기</title>"
    		  +"</head>"
    		  +"<body>"
    		  + "<font size=5> 환율 계산기</font><br>"
    		  + "<form name='frmCalc' method='get' action= '/pro06/calc060202'>"
    		  + "원화 <input type='text' name='won' size=10/>"
    		  +  "<select name='operator'>"
    		  +   " <option value='dollar'>달러</option>"
    		  +   " <option value='en'>엔화</option>"
    		  +   " <option value='wian'>위안화</option>"
    		  +   " <option value='pound'>파운드</option>"
    		  +   " <option value='euro'>유로</option>"
    		  +  "</select>"
    		  +   "<input type='hidden' name='command' value='calculate'>"
    		  +   "<input type='submit' value='변환'>"
    		  + "<form>"
    		  +"</body>"
    		  +"</html>";
      pw.print(strHtml);
      pw.close();
  
   }
   
   private static String calculate(float won, String operator) {
	   String result = null;
	   if (operator.equals("dollar")) {
		   result =String.format("%.6f", won / USD_RATE);
	   } else if (operator.equals("en")) {
		   result =String.format("%.6f", won / JPY_RATE);
	   } else if (operator.equals("wian")) {
		   result =String.format("%.6f", won / CNY_RATE);
	   } else if (operator.equals("pound")) {
		   result =String.format("%.6f", won / GBP_RATE);
	   } else if (operator.equals("euro")) {
		   result =String.format("%.6f", won / EUR_RATE);
	   }
	return result; 
	   
	   
   }
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
   
}

