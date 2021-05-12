package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login090101")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() {
		System.out.println("init 메서드 호출");
	}
	
    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String data = "<!DOCTYPE html>";
		data+="아이디 : " +user_id + "<br>" ;
		data+="패스워드 : " +user_pw + "<br>" ;
		data+="주소 : " +user_address + "<br>" ;
		data+="email : " +user_email + "<br>" ;
		data+="휴대전화 : " +user_hp + "<br>" ;
		data+="</body></html>";	
		out.print(data);
		out.close();
	}
	
//	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		String user_id = request.getParameter("user_id");
//		String user_pw = request.getParameter("user_pw");
//		String user_address = request.getParameter("user_address");
//		String user_email = request.getParameter("user_email");
//		String user_hp = request.getParameter("user_hp");
//		
//		String data = "<!DOCTYPE html>";
//		data+="아이디 : " +user_id + "<br>" ;
//		data+="패스워드 : " +user_pw + "<br>" ;
//		data+="주소 : " +user_address + "<br>" ;
//		data+="email : " +user_email + "<br>" ;
//		data+="휴대전화 : " +user_hp + "<br>" ;
//		data+="</body></html>";	
//		out.print(data);
//		out.close();
		
//		String data = "<!DOCTYPE html>";
//		data +="<html>";
//		data +="<head>";
//		data +="<meta charset=\"UTF-8\">";
//		data +="<title>로그인 창</title>";
//		data +="</head>";
//		data +="<body>";
//		data += "아이디 :" +id;
//		data += "<br>";
//		data += "패스워드 : " +pw;
//		data +="</body>";
//		data +="</html>";
//		out.print(data);
//		out.close();
//	}

}
