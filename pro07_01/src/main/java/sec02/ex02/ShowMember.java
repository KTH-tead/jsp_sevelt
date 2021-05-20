package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show070501")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
					     HttpServletResponse response) 
					    		 throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
        PrintWriter out = response.getWriter();
    
        String id ="" ;
        String password="" ;
        String name="" ;
    
        Boolean isLogon=false;
    
        //이미 세션이 존재하면, 기존 세션객체를 가져옵니다.
        //세션이 존재하지 않으면, session는 null이 됩니다.
        HttpSession session =  request.getSession(false);			

        //기존 세션이 존재하면,
        if(session != null){

        	//isLogon 속성의 값을 가져옵니다.
    		isLogon = (Boolean) session.getAttribute("isLogon");
    	
    		//isLogon 속성의 값이 true이면, 회원 정보를 브라우저에 표시합니다.
        	if(isLogon==true){ 
    			id = (String) session.getAttribute("login.id");
    			password = (String) session.getAttribute("login.password");
    			name = (String) session.getAttribute("login.name");

    			out.print("<html><head><meta charset='utf-8'></head><body>");
    			out.print(id+"님 환영합니다. <br>");
    			out.print("계정: "+ id + "<br>");
    			out.print("암호: "+ password + "<br>");
    			out.print("한글이름: " + name+"<br>");
    			
    			out.print("<br><br>"
    					+ "<h2 style='text-align:center;'>"
    					+ "	<a href='/pro07/login3.html'><button>로그아웃</button></a></h2>");
        		out.print("</body></html>");
    		
    		} else{//(isLogon==false) 인 경우, 로그인 상태가 아니면 로그인 창으로 이동합니다.
    		
   			//기존 세션 무효화
    		session.invalidate();
    		//로그인 페이지로 리다이렉트
    		System.out.println("showMember가 브라우저에서 직접 호출된 경우입니다.");
    		response.sendRedirect("/pro07/login3.html");
    		}
       	
        } else{//세션이 생성되지 않았으면 로그인 창으로 이동합니다.
		//session.invalidate();
        System.out.println("세션 객체가 없을 때 처리됩니다.");
		response.sendRedirect("/pro07/login3.html");
        }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}//Class End
