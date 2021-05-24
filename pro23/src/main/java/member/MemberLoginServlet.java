package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memberLogin23")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		//MemberVO 객체를 생성하고 속성에 아이디와 패스워드를 설정합니다.
		MemberVO memberVO = new MemberVO();
		
		//로그인 창에서 전송된 아이디와 패스워드를 가져옵니다.
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println("사용자 입력ID: "+ id);
		System.out.println("사용자 입력암호: "+ password);
		
		memberVO.setId(id);
		memberVO.setPassword(password);


		//MemberDAO의 isExisted()메소드에, 
		//아이디와 패스워드가 저장된 MemberVO 객체를 전달하여 처리합니다.   
		MemberDAO memberDAO = new MemberDAO();
		
		boolean result = memberDAO.isMemberExist(memberVO);
		
		PrintWriter out = response.getWriter();
		
		//웹브라우저의 세션정보가 있는 세션 객체(HttpSession 객체)를 호출합니다.
		HttpSession session = request.getSession();
		
		//memberDAO.isMemberExist(memberVO) 처리 결과가 true이면,
		if (result) {
			
			//isLogin 속성에 true 값을 지정하여 세션객체에 저장합니다.
			//로그인아이디와패스워드도 세션 객체에 저장합니다.
			//session.setAttribute("isLogon", true); 
			session.setAttribute("login.id", id);
			//session.setAttribute("login.password", password);
			
			if (id.equals("hong")) {
				response.sendRedirect("/pro16/memberList16");
				
			} else {
				response.sendRedirect("/pro16/memberInfo16");

			/*
				                  //response.encodeURL("호출되는서블릿매핑이름")
				String url=response.encodeURL("memberShow91");
				
				out.print("<html><head><meta charset=\"utf-8\"></head><body>");
				out.print("안녕하세요 " + id + "님!!!<br>");
				out.print("<a href="+url+">회원정보보기(encodeURL처리)</a><hr>");
				out.print("</body></html>");
			*/
			}
			
		//memeberDAO.isMemberExist(memberVO) 처리 결과가 false이면,
		} else {
			out.print("<html><head><meta charset=\"utf-8\"></head><body>회원 아이디 또는 암호가 틀립니다.");
			out.print("<a href='/pro16/member/memberLogin.html'> 다시 로그인하기</a>");
			out.print("</body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}

}
