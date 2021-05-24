package ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/member2301")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//MemberDAO 객체를 생성하고, selectAllMemberList()를 호출하여
		//반환된 정보를 리스트 객체에 저장합니다.
		MemberDAO memberDao = new MemberDAO();
		//처음 실습
		List<MemberVO> membersList = memberDao.selectAllMemberList();
		
		//HashMap 실습용
		//List membersList = dao.selectAllMemberList();
		
		//조회한 회원정보를 바인딩하고 JSP로 포워딩합니다.
		request.setAttribute("membersList", membersList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test01/listMembers.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
