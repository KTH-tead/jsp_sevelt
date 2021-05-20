package emptest.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listemps03")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		EmpDAO dao = new EmpDAO();
		
		String command = request.getParameter("command");
		
		
		if(command!=null&&command.equals("delEmp")) {
			String employee_id = request.getParameter("employee_id");
			dao.delEmp(employee_id);
		}
		
		
		//맴버의 목록 정보를 담은 List 객체를 생성합니다.
		List<EmpVO> empsList = dao.listEmps();
				
		//생성된 membersList 객체를 바인딩 걸어서 ViewServlet으로 전달합니다.
		request.setAttribute("empsList", empsList);
		
		RequestDispatcher dispatcher = 
							request.getRequestDispatcher("viewemps03");
		dispatcher.forward(request, response);
	}
}


