package sec02.ex03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//Connection Pool 및 PerparedStatement 유형의 SQL 문 처리 유형
public class MemberDAO {

   //데이터베이스 접속 객체 저장 필드
   private Connection con;
   
   private PreparedStatement pstmt;
   
   private DataSource dataFactory ;
   
   public MemberDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/hr");
      } catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   
   public List<MemberVO> listMembers(MemberVO memberVO) {

	      List<MemberVO> memberList = new ArrayList<MemberVO>();

	      String _name = memberVO.getName();
	      
	      try {
	         con=dataFactory.getConnection(); //Connection Pool 접속 객체 생성
	         
	         String searchedMemberSql =
	               "SELECT * FROM hr.t_members ";
	         //_name 값이 존재하면, 
	         //SELECT문에 이름으로 검색하는 WHERE 절을 추가하여 실행
	         if(_name != null && _name.length() != 0) {
	            searchedMemberSql += " WHERE UPPER(name) = ?"; //문자열의 맨 앞에 빈칸이 포함됩니다. 
	            
	            pstmt = con.prepareStatement(searchedMemberSql);
	            
	            pstmt.setString(1, _name);
	            
	         }//_name 값이 없으면, WHERE 절 없는 SELECT문을 실행
	         else {
	         
	            pstmt = con.prepareStatement(searchedMemberSql);
	         }
	         
	         System.out.println("preparedStatement: " + searchedMemberSql);
	         
	         ResultSet rs = pstmt.executeQuery(); //SQL문 실행 결과가 담긴 ResultSet 객체 생성
	         
	         //ResultSet 객체에 담긴 결과를 ArrayList 객체에 옮기기 
	         while (rs.next()) {
	            String id = rs.getString("id");   //조회한 레코드의 각 컬럼 값을 받아옵니다.
	            String password = rs.getString("password");
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            Date joinDate = rs.getDate("joinDate");
	            
	            MemberVO memVO = new MemberVO();   //각 컬럼값을 다시 MemberVO 객체의
	                                    //속성에 설정합니다.
	            memVO.setId(id);                   
	            memVO.setPassword(password);
	            memVO.setName(name);
	            memVO.setEmail(email);
	            memVO.setJoinDate(joinDate);
	            
	            memberList.add(memVO);          //설정된 MemberVO 객체를 다시 ArrayList에 저장합니다.
	            Collections.sort(memberList);  //정렬
	         }
	         rs.close();      //ResultSet 리소스 닫음.
	         pstmt.close();   //PreparedStatement 리소스 닫음.
	         con.close();   //접속 객체 닫음.
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return memberList;   //조회한 레코드의 개수만큼 MemberVO 객체를 저장한 
	   }               //ArrayList를 반환합니다.
   

}