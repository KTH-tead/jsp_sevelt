package sec01ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login060101")
public class LoginServlet extends HttpServlet {
	
//	public void init() throws SevletException 
//	{
//		System.out.println("init 메서드 호출");
//	}
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_id= request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		System.out.println("아이디 : " +user_id);
		System.out.println("비밀번호 : " +user_pw);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
