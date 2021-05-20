package emptest.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/salsummary03")
public class SalSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		SalSummaryDAO dao = new SalSummaryDAO();
		
		String command = request.getParameter("command");

		List<SalSummaryVO> salSummaryList = dao.deptSalSummary();
		
		//생성된 membersList 객체를 바인딩 걸어서 ViewServlet으로 전달합니다.
		request.setAttribute("salSummaryList", salSummaryList);
		
		RequestDispatcher dispatcher = 
									request.getRequestDispatcher("viewsalsummary03");
		dispatcher.forward(request, response);
		
			
	}
}