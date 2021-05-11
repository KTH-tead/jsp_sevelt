package sec04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Gugutest")
public class Gugutest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Gugutest() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html; charset=utf-8");
	      PrintWriter out = response.getWriter();
	      int dan = Integer.parseInt(request.getParameter("dan"));
	      out.print("<table style='border: solid black; width:800; tex-align=center'>");
	      out.print("<tr style='background-color:#ffff66; text-align:center'>");
	      out.print("<td colspan=2>" +dan + "단 출력 </td>");
	      out.print("</tr>");
	      
	      for (int i =1; i<10; i++) {
	    	  out.print("<tr style='text-align:center'>");
	    	  out.print("<td style='width:400'>");
	    	  out.print(dan +"x"+i);
	    	  out.print( "</td>");
	    	  out.print("<td style='width:400'>");
	    	  out.print(i * dan);
	    	  out.print("</td>");
	    	  out.print("</tr>");
	      }
	      out.print("</table>");
	      
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
