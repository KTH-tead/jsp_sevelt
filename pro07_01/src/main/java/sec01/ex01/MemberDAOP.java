package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Statement 유형의 SQL 문 처리 유형
public class MemberDAOP {
	
	//데이터베이스 접속 시 필요한 정보
	//드라이버 클래스 정보
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	//사용할 jdbc 드라이버 정보 및 접속 정보 
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	//데이터베이스 접속 계정명
	private static final String user = "hr";
	//데이터베이스 접속 계정의 암호
	private static final String pwd = "oracle4U";
	
	
	//데이터베이스 접속 객체 저장 필드
	private Connection con;
	
	//SQL 문장이 저장되는 객체(2가치 유형)
	//1.Statement 유형: 정적 SQL문장
	//2.PreparedStatement 유형: 동적 SQL 문장(바인딩 처리 문장, OLTP 환경에서 일반적으로 많이 사용함)
	//2가지 유형 중 한가지를 선택하여 사용해야 함.
	private PreparedStatement stmtP;
	
	//데이터베이스 저복 메소드를 선언
	private void connDB() {	//DB 접속 메소드
		try {
			//ojdbc 드라이버 로드
			Class.forName(driver);	
			System.out.println("========== Oracle 드라이버 로딩 성공 ==========");
			
			//드라이버를 통해 오라클 데이터베이스와 접속 객체 생성
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("========== Connection 생성 성공 ==========");

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			//connDB()메서드를 호출합니다.
			//드라이버로드.con 객체 생성
			connDB();	
			
			String query = "select * " + 
			               "from hr.t_members " +
					       "where password = ?";
			System.out.println(query);

			stmtP = con.prepareStatement(query);
			
			//?에 값 바인딩
			stmtP.setString(1,"1212");
			
			//SELECT문 처리 결과를 저장할 ResultSet 객체를 선언하여
			//문장객체의 executeQuery(SQL문)메서드의 처리결과로 초기화합니다.
			ResultSet rs = stmtP.executeQuery(); 

			//ResultSet 객체의 행들을 하나씩 꺼내어
			//행당 하나의 memberVO 객체를 이용하여 저장
			while (rs.next()) {
				String id = rs.getString("id");	//조회한 레코드의 각 컬럼 값을 받아옵니다.
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();	//각 컬럼값을 다시 MemberVO() 객체의
				vo.setId(id);					// 속성에 설정합니다.
				vo.setPassword(password);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				////값이 저장된 MemberVO 객체를 ArrayList 객체인 list에 저장합니다.
				list.add(vo);		
			}
			rs.close();
			stmtP.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	//조회한 레코드의 개수만큼 MemberVO 객체를
	}					//저장한 ArrayList를 반환합니다.
	
}
