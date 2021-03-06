package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login090102")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		String data = "안녕하세요<br> 로그인 하셨습니다.<br><br>";
		data+="<!DOCTYPE html>";
		data+="아이디 : " +user_id + "<br>" ;
		data+="패스워드 : " +user_pw + "<br>" ;
		data+="주소 : " +user_address + "<br>" ;
		data+="email : " +user_email + "<br>" ;
		data+="휴대전화 : " +user_hp + "<br>" ;
		out.print(data);
		
		//get 방식으로 전송되는 데이터에 포함된 한글을 정상적으로 전송하기 위해서 인코딩 처리를 합니다.
		user_address=URLEncoder.encode(user_address, "utf-8");
		out.print("<a href='/pro09/second?user_id="+user_id+"&user_pw="+user_pw+"&user_addresss="+user_address+"'> 두 번째 서블릿으로 보내기 </a>");
		data+="</body></html>";	
		out.print(data);
		out.close();

	}

}
