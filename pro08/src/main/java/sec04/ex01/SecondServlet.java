package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sec04/ex01/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public SecondServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String address=(String) request.getAttribute("address");
		out.print("<html><body>");
		out.print("주소 :" + address +"<br>");
		out.print("redirect를 이용한 바인딩 실습입니다" );
		out.print("</html></body>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
