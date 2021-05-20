package se05.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/show070501")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String Id="", Password="";
		Boolean isLogon=false;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			isLogon=(Boolean)session.getAttribute("isLogon");
			if(isLogon==true) {
				Id =(String)session.getAttribute("login.id");
				Password =(String)session.getAttribute("login.Password");
				out.print("<html><head></head><body>");
				out.print("아이디 : " + Id + "님!!!<br>" );
				out.print("비밀번호 :" + Password +"<br>" );
				out.print("</body></html>");	
			} else {
				response.sendRedirect("/pro07/login3.html");
			} 
		} else {
			response.sendRedirect("/pro07/login3.html");
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
