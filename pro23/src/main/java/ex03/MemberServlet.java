package ex03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex01.MemberVO;

@WebServlet("/member2303")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
	
		MemberDAO memberDao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		String action = request.getParameter("action");
		System.out.println("action:"+ action);
		
		String nextPage = "";

		if (action == null || action.equals("selectAllMembersList") || action.equals("/")) {
			
			List<MemberVO> membersList = memberDao.selectAllMembersList();
			request.setAttribute("membersList", membersList);
			
			nextPage = "/test02/listMembers.jsp";
		//action이 selectMemberById이면 전송된 값을 getParameter()로 가져온 후, 
		//SQL문의 조건식에서 id의 값으로 전달합니다.
			
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("inputValue");
			//이 서블릿에서 생성한 memberVO 입니다.
			//
			memberVO = memberDao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "/test02/memberInfo.jsp";
			
		//action이 selectMemberByPwd이면 전송된 값을 getParameter()로 가져온 후, 
		//SQL문의 조건식에서 pwd의 값으로 전달합니다.
		} else if (action.equals("selectMembersByPassword")) {
			String password = request.getParameter("inputValue");
			List<MemberVO> membersList = memberDao.selectMembersByPassword(password);
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);  
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
