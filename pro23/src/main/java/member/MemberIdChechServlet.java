package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/idDuplicateCheck23")
public class MemberIdChechServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = (String) request.getParameter("id");
		
		System.out.println("id = " + id);
		
		MemberDAO memberDAO = new MemberDAO();
		
		boolean CheckedResult = memberDAO.isIdExist(id);
		
		PrintWriter writer = response.getWriter();

		if (CheckedResult == true) {
			writer.print("not_usable");
		} else {
			writer.print("usable");
		}
	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
			doGet(request, response);
	}

}
