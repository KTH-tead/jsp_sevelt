package se05.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login070501")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out= response.getWriter();
		
	    //로그인 창에서 전송된 아이디와 패스워드를 가져옵니다.
		String userId = request.getParameter("id");
		String userPassword = request.getParameter("password");
		String name=request.getParameter("name");
		
		System.out.println("사용자 입력 ID:" +userId);
		System.out.println("사용자 입력암호:" +userPassword);
		System.out.println("한글 테스트:" +name);
		
		//MemberVO 객체를 생성하고 속성에 아이디와 패스워드를 설정합니다.
		MemberVO memberVO = new MemberVO();
		memberVO.setId(userId);
		memberVO.setPassword(userPassword);
		
		//MemberDAO의 isExisted()메소드에, 
	    //아이디와 패스워드가 저장된 MemberVO 객체를 전달하여 처리합니다.   
		  //맴버가 존재하는지 확인하는 메소드를 호출합니다.
		MemberDAO dao = new MemberDAO();
	    //memberDAO.isMemberExist(memberVO) 처리 결과가 true이면,

		boolean result = dao.isMemberExist(memberVO);
		 //웹브라우저의 세션정보가 있는 세션 객체(HttpSession 객체)를 호출합니다.
		HttpSession session = request.getSession();

		if (result) {
			System.out.println("생성된 세션 아이디 : " + session.getId());
	        
	        //isLogin 속성에 true 값을 지정하여 세션객체에 저장합니다.
	        //로그인아이디와패스워드도 세션 객체에 저장합니다.

			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", userId);
			session.setAttribute("login.Password",userPassword);
			session.setAttribute("login.name", name);
			
			
			
			 //response.encodeURL("호출되는서블릿매핑이름")
	        //String url=response.encodeURL("show090501");
	        //System.out.println("생성된 세션 아이디 : " + session.getId());
	        //System.out.println("encodeURL() 값 : " + url);

			
			
			out.print("<html><head></head><body>");
			out.print("안녕하세요" + userId + "님!!!<br>" );
			out.print("<a href='show070501')회원정보 보기</a>");
			out.print("</body></html>");	
			
			} else
				//memeberDAO.isMemberExist(memberVO) 처리 결과가 false이면
					{
				out.print("<html><head></head><body>");
				out.print("<center>회원 아이디,비밀번호가 틀립니다." );
				out.print("<a href='/pro07/login3.html')다시 로그인하기</a>");
				out.print("</body></html>");	
			}
		
		}
	}
	
