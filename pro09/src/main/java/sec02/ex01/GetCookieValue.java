package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Get090201")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

//    public GetCookieValue() {
//        super();
//    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] allValues=request.getCookies();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'></head>");
		for(int i=0; i<allValues.length; i++) {
			System.out.println("쿠키이름 :" +allValues[i].getName()+"::"
								+ "쿠키값" +allValues[i].getValue());
			if(allValues[i].getName().equals("cookieTest")) {
				out.println("<h2>Cookie 값 가져오기 :"
						+URLDecoder.decode(allValues[i].getValue(),"utf-8"));
				//break;
			}
		}
		out.println("</body></html>");
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
