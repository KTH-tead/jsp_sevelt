package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memberInfo23")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response)
								 throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("login.id");
		
		//MemberDAO 객체를 생성합니다.
	    MemberDAO memberDAO=new MemberDAO();
	    	      
		MemberVO memberVO = memberDAO.memberInfo(id);
    
		request.setAttribute("memberVO", memberVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberInfo.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}

}
