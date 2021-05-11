package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet5")
public class LoginServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
       

    public LoginServlet5() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html; charset=utf-8");
	     PrintWriter out = response.getWriter();
	     String id = request.getParameter("user_id");
	     String pw = request.getParameter("user_pw");
	     String address = request.getParameter("user_address");
	     System.out.println("아이디 : " + id);
	     System.out.println("비밀번호 : " + pw);
		doGet(request, response);
		
		
		String strHtml =  "<!DOCTYPE html>";
				strHtml +=" <html>";
				strHtml += "<head>";
				strHtml += " <meta charset=\"UTF-8\">";
				strHtml += " <title>로그인</title>";
				strHtml += "</head>";
				strHtml += "<body>";
				strHtml += "<font size=5> 로그인 정보</font><br>";
				strHtml +="<font size=5> 아이디" + id +"</font><br>";
				strHtml += "<font size=5> 비밀번호" + pw +"</font><br>";
				strHtml +="</body>";
				strHtml +="</html>";
				out.print(strHtml);
	}
	

}
