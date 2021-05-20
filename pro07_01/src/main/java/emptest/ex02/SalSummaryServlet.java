package emptest.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/salsummary02")
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
		
		PrintWriter out = response.getWriter();
		
		SalSummaryDAO dao = new SalSummaryDAO();

		List<SalSummaryVO> salSummaryList = dao.deptSalSummary();

		out.print("<html><body>");
		out.print("<table  border=1>"
					+ "<tr align='center' bgcolor='lightgreen'>");
		out.print(		"<td>부서명</td>"
					+ 	"<td>총급여</td>"
					+ 	"<td>평균급여</td>"
					+ 	"<td>직원수</td>"
					+ "</tr>");
			
		for(int i=1; i<salSummaryList.size();i++) {
			
			SalSummaryVO vo = (SalSummaryVO)salSummaryList.get(i);
			
			String department_name = vo.getDepartment_name();
			int salsum = vo.getSalsum();
			int salavg = (int) vo.getSalavg();
			int workers = vo.getWorkers();
				
			out.print("<tr>"
						+ "<td>"+department_name+"</td>"
						+ "<td>"+salsum+"</td>"
						+ "<td>"+salavg+"</td>"
						+ "<td>"+workers+"</td>"
					+ "</tr>");	
		}
			
		out.print("</table>"
				+ "</body></html>");
		out.print("<a href='/pro07/salsummary02'>결과 갱신하기</a>");
			
		}
		
		
			
	}
