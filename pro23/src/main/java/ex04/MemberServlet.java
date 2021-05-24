package ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex01.MemberVO;

@WebServlet("/member2304")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) 
								 throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
	
		MemberDAO memberDao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		String action = request.getParameter("action");
		System.out.println("action:"+ action);
		
		String nextPage = "";

		if (action == null || action.equals("selectAllMembersList") || action.equals("/")) {
			
			List<MemberVO> membersList = memberDao.selectAllMembersList();
			request.setAttribute("membersList", membersList);
			
			nextPage = "/test02/listMembers.jsp";
		//action이 selectMemberById이면 전송된 값을 getParameter()로 가져온 후, 
		//SQL문의 조건식에서 id의 값으로 전달합니다.
			
		} else if (action.equals("selectMemberById")) {
			String id = request.getParameter("inputValue");
			//이 서블릿에서 생성한 memberVO 입니다.
			//
			memberVO = memberDao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			
			nextPage = "/test02/memberInfo.jsp";
			
		//action이 selectMemberByPwd이면 전송된 값을 getParameter()로 가져온 후, 
		//SQL문의 조건식에서 pwd의 값으로 전달합니다.
		} else if (action.equals("selectMembersByPassword")) {
			String password = request.getParameter("inputValue");
			List<MemberVO> membersList = memberDao.selectMembersByPassword(password);
			request.setAttribute("membersList", membersList);
			
			nextPage = "/test02/listMembers.jsp";
			
		} else if(action.equals("insertNewMember")) {
			
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String email = request.getParameter("email");

			memberVO.setId(id);
			memberVO.setPassword(password);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			memberDao.insertNewMember(memberVO);
			
			nextPage="/member2304?action=selectAllMembersList";

// 23.4.6 회원 정보 수정
		} else if(action.equals("updateMemberById")){
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String email = request.getParameter("email");
			  
			memberVO.setId(id);
			memberVO.setPassword(password);
			memberVO.setName(name);
			memberVO.setEmail(email);
			  
			//회원 정보 수정창에서 전송된 회원 정보를 MemberVO의 속성에 설정한 후,
			//updateMember()를 호출하면서 MemberVO 객체를 전달합니다.
			memberDao.updateMemberById(memberVO);
			  
			nextPage="/member2304?action=selectAllMembersList";

// 23.4.7 회원 정보 삭제
		} else if(action.equals("deleteMemberById")){
	    	  
	  	      String id=request.getParameter("id");
	  	      //회원 목록창에서 전달된 ID를 deleteMember()메소드를
	  	      //호출하면서 SQL문으로 전달합니다.
	  	      memberDao.deleteMemberById(id);
		      
	  	    nextPage="/member2304?action=selectAllMembersList";

// 23.5.1 <if> 태그로 동적 SQL문 만들기
// 23.5.2 <choose> 태그로 동적 SQL문 만들기
		} else if(action.equals("searchMember")){
		  
			//검색창에서 입력한  값을 가져옵니다.
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			  
			//memberVO 객체에 설정합니다.
			memberVO.setName(name);
			memberVO.setEmail(email);
			  
			//dao의 searchMember()메소드에 memberVO을 전달하여 검색결과를
			//membersList에 저장합니다.
			List<MemberVO> membersList =memberDao.searchMember1(memberVO);
			  
			//검색결과를 요청에 바인드합니다.
			request.setAttribute("membersList",membersList);
			  
			nextPage="/test03/listMembers.jsp";
			//dispatcher에게 전달하여 listMembers.jsp 페이지에서 표시됩니다.

//23.5.3 <forEach> 태그로 회원 정보 조회하기
		} else if(action.equals("forEachSelect")) {
  
			//ArraryList에 검색할 이름을 저장한 후,  
			List<String> nameList = new ArrayList<String>();
			nameList.add("홍길동");
			nameList.add("차범근");
			nameList.add("이순신");
			
			//dao.foreachSelect메소드에게 ArrayList를 전달합니다.
			List<MemberVO> membersList=memberDao.forEachSelect(nameList);
			
			//검색된 결과를 요청에 바인딩합니다.
			request.setAttribute("membersList",membersList);
			nextPage="/test03/listMembers.jsp";

		} else if(action.equals("forEachInsert2")) {
			
			List<MemberVO> memList = new ArrayList<MemberVO>();
			
			memList.add(new MemberVO("q1", "12345", "신길동", "q1@test.com"));
			memList.add(new MemberVO("q2", "12345", "최길동", "q2@test.com"));
			memList.add(new MemberVO("q3", "12345", "왕길동", "q3@test.com"));
			
			//dao.foreachInsert()메소드의 위의 회원정보가있는 ArrayList 객체를 전달하여 
			//입력작업을 수행합니다.
			int result=memberDao.forEachInsert(memList);
			nextPage="/member2304?action=selectAllMembersList";

		          
// 23.5.5 <sql> 태그와 <include> 태그로 SQL문 중복 제거하기
		} else if(action.equals("searchMember2")){
		
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			List<MemberVO> membersList =memberDao.searchMember1(memberVO);
			
			request.setAttribute("membersList",membersList);
			nextPage="/test03/listMembers.jsp";
		 
		} else if(action.equals("forEachSelect2")) {
		
			List<String> nameList = new ArrayList<String>();
			nameList.add("이순신");
			nameList.add("강감찬");
			nameList.add("홍길동");
			
			List<MemberVO> membersList=memberDao.forEachSelect(nameList);
			
			request.setAttribute("membersList",membersList);
			nextPage="/test03/listMembers.jsp";

			  
// 마이바티스에서 오라클 연동해 LIKE 연산자 검색하는 방법 *
		} else if(action.equals("selectLike")) {
		 	      
			//String name=request.getParameter("name");
			String email=request.getParameter("email");
			//memberVO.setName(name);
			//memberVO.setEmail(email);
			
			//List<MemberVO> membersList =memberDao.selectLike(name);
			List<MemberVO> membersList =memberDao.selectLike(email);
			
			request.setAttribute("membersList",membersList);
			
			nextPage="/test03/listMembers.jsp";


// 마이바티스에서 오라클 연동해 LIKE 연산자 검색하는 방법2

		} else if(action.equals("selectLike2")) {

			String name=request.getParameter("name");
			String email=request.getParameter("email");

			memberVO.setName(name);
			memberVO.setEmail(email);

			List<MemberVO> membersList =memberDao.selectLike2(memberVO);

			request.setAttribute("membersList",membersList);
			nextPage="/test03/listMembers.jsp";
  
	           
//VO 객체 없이 HashMap을 이용한 데이터 조회
		} else if (action.equals("hiredCountEmployees")) {

			List<HashMap<String, String>> hiredCountList  = memberDao.hiredEmployees();
			
			request.setAttribute("hiredCountList",hiredCountList);
			nextPage="/test03/empCountResult.jsp";


// 23.5.4 <forEach> 태그로 회원 정보 추가하기
		} else if(action.equals("forEachInsert1")) {
			
			List<MemberVO> memList = new ArrayList<MemberVO>();
			
			memList.add(new MemberVO("a1111","1234", "박길동", "m1@test.com"));
			memList.add(new MemberVO("a2222","1234", "이길동", "m2@test.com"));
			memList.add(new MemberVO("a3333","1234", "김길동", "m3@test.com"));
			
			//dao.forEachInsert()메소드의 위의 회원정보가있는 ArrayList 객체를 전달하여 
			//입력작업을 수행합니다.
			int result=memberDao.forEachInsert(memList);
			nextPage="/member2304?action=selectAllMembersList";
		
		} else if(action.equals("forEachInsert")) {
			
			List<MemberVO> memList = new ArrayList<MemberVO>();
			
			memList.add(new MemberVO("m1", "1234", "박길동", "m1@test.com"));
			memList.add(new MemberVO("m2", "1234", "이길동", "m2@test.com"));
			memList.add(new MemberVO("m3", "1234", "김길동", "m3@test.com"));
			//dao.foreachInsert()메소드의 위의 회원정보가있는 ArrayList 객체를 전달하여 
			//입력작업을 수행합니다.
			int result=memberDao.forEachInsert(memList);
			nextPage="/member2304?action=selectAllMembersList";
			
//23.4.5 HashMap을 이용한 회원 정보 추가    
		} else if(action.equals("insertMember2")) {
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String name=request.getParameter("name");
			String email = request.getParameter("email");
			   
			//회원 가입창에서 전송된 회원 정보를 HashMap 에 키/값 으로 저장 후
			//MemberDAO의 insertMember2() 인자로 전달합니다.
			   
			//Map memberMap=new HashMap();
			Map<String, String> memberMap=new HashMap<String, String>();
			   
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			
			memberDao.insertMember2(memberMap);
			nextPage="/member2304?action=selectAllMembersList";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);  
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws ServletException, IOException {
		doGet(request, response);
	}

}
