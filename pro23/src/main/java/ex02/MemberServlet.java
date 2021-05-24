package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member2302")
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

		String name = memberDao.selectMemberName();  //MemberDAO의 selectName()메소드 호출
		String password = memberDao.selectMemberPassword();     //MemberDAO의 selectPwd()메소드 호출
		
		//아래는  웹브라우저의 경고창에 표시하기 위한 자바스크립트 코드를 작성하는 코드입니다. 
		PrintWriter pw = response.getWriter();
		
		pw.write("<script>");
		pw.write("alert(' 이름: " + name +"');");
		pw.write("alert(' 비빌번호 : "+ password+"');");
		pw.write("</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
