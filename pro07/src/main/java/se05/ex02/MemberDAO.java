package se05.ex02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
   
   public boolean isMemberExist(MemberVO memberVO) {
	   boolean result =false;
	   String id = memberVO.getId();
	   String password = memberVO.getPassword();
	   try {
		   con = dataFactory.getConnection();
		   String query = "select decode(count(*),1,'true','false') as result from t_members ";
				  query += "where id=? and password=?";
		  // String selsectTryeFalase = 
			//	   "SELECT (CASE WHEN COUNT(*) =1 THEN 'true'";
				//   + " ELSE '"
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1, id);
	   pstmt.setString(2, password);
	   ResultSet rs = pstmt.executeQuery();
	   rs.next();
	   result = Boolean.parseBoolean(rs.getString("result"));
	   System.out.println("result=" +result);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   	return result;
	   
   }
   

}