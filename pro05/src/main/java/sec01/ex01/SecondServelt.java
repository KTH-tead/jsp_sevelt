package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstServelt")
public class SecondServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SecondServelt() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("SecondServlet의 init() 메서드 호출");
	}

	public void destroy() {
		System.out.println("SecondServlet의 destroy 메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SecondServlet의 doGet 메서드 호출");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
