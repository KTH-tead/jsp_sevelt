package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetServletContext")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SetServletContext() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
//		List<String> member = new ArrayList();
//		member.add("이순신");
//		member.add("30");
//		context.setAttribute("member", member);
		
		Map<String, Integer> member = new HashMap<>();
		member.put("이순신", 30);
		//context.setAttribute("member",member);
		
		List<Map<String, Integer>> memberList = new ArrayList<>();
		memberList.add(member);
		
		context.setAttribute("memberList", memberList);
		
		out.print("<html><body>");
		out.print("이순신과 30 설정");
		out.print("</body></html>");
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
