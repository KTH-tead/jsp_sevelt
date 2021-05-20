package emptest.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listemps01")
public class EmpServlet extends HttpServlet {
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
		
		EmpDAO dao = new EmpDAO();
		
		String command = request.getParameter("command");
		String vsDay = request.getParameter("sDay");
		String veDay = request.getParameter("eDay");
		
		if(command!=null&&command.equals("delEmp")) {
			String employee_id = request.getParameter("employee_id");
			dao.delEmp(employee_id);
		}

		
		//YYYY-MM-DD 형식으로 입력
		List<EmpVO> list = dao.listEmps(vsDay, veDay);
			
		out.print("<html><body>");
		out.print("<table  border=1>"
					+ "<tr align='center' bgcolor='lightgreen'>");
		out.print(		"<td>사번</td>"
					+ 	"<td>이름</td>"
					+ 	"<td>성</td>"
					+ 	"<td>이메일</td>"
					+ 	"<td>전화번호</td>"
					+ 	"<td>입사일</td>"
					+ 	"<td>직책코드</td>"
					+ 	"<td>급여</td>"
					+ 	"<td>커미션비율</td>"
					+ 	"<td>상관사번</td>"
					+ 	"<td>부서 이름</td>"
					+ 	"<td>삭제</td>"
					+ "</tr>");
			
		for(int i=1; i<list.size();i++) {
			
			EmpVO vo = (EmpVO)list.get(i);

			int employee_id = vo.getEmployee_id();
			String first_name = vo.getFirst_name();
			String last_name = vo.getLast_name();
				
			String email = vo.getEmail();
			String phone_number = vo.getPhone_number();
			Date hire_date = vo.getHire_date();
			String job_id = vo.getJob_id();
			int salary = vo.getSalary();
			double commission_pct = vo.getCommission_pct();
			int manager_id = vo.getManager_id();
			String department_name = vo.getDepartment_name();
				
			out.print("<tr>"
						+ "<td>"+employee_id+"</td>"
						+ "<td>"+first_name+"</td>"
						+ "<td>"+last_name+"</td>"
						+ "<td>"+email+"</td>"
						+ "<td>"+phone_number+"</td>"
						+ "<td>"+hire_date+"</td>"
						+ "<td>"+job_id+"</td>"
						+ "<td>"+salary+"</td>"
						+ "<td>"+commission_pct+"</td>"
						+ "<td>"+manager_id+"</td>"
						+ "<td>"+department_name+"</td>"
						+ "<td>"
						+ 	"<a href='/pro07/listemps01?command=delEmp&employee_id="+employee_id+"'>삭제</a>"
						+ "</td>"
					+ "</tr>");	
		}
			
		out.print("</table>"
				+ "</body></html>");
		out.print("<a href='/pro07/empForm.html'>다시 검색하기</a>");
			
		}
		
		
			
	}


