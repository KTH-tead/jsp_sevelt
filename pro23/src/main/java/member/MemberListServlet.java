package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memberList23")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response)
								 throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String _name = request.getParameter("name");
		
		System.out.println("name: "+ _name);

		//MemberDAO 객체를 생성합니다.
	    MemberDAO memberDAO=new MemberDAO();
	    
		List<MemberVO> memberList = null;
		
		// 검색 조건이 지정되지 않은 경우(_name이 Null)를 제외하기 위한 if 조건 
		if( _name !=null ) {
			//검색어가 입력된 경우 
			if (_name.length() != 0) {
			
				MemberVO memberVO = new MemberVO();
   
			    memberVO.setName(_name);
				
				memberList = memberDAO.listMembers(memberVO);
				
			} else {
				memberList=memberDAO.listMembers();
			}
		  //기간 검색 페이지를 거치지 않았거나, startDay와 endDay에 값이 입력되지 않은 경우 
		} else {
			
			memberList=memberDAO.listMembers();
		}
	    
    
		request.setAttribute("memberList", memberList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/membersInfo.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}

}
