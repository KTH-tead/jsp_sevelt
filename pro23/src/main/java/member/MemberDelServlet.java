package member;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Date;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberDel23")
public class MemberDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response)
								 throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		
		//PrintWriter out=response.getWriter();
		
		String id = request.getParameter("id");
		
	     
		//MemberDAO 객체를 생성합니다.
		MemberDAO dao=new MemberDAO();
		
		dao.delMember(id);
		
		response.sendRedirect("/pro16/memberList16");

	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}

}