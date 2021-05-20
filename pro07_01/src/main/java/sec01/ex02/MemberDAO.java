package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	//로딩된 jdbc 드라이버로 DB접속 객체 생성 시 전달 4개 값 정보
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@172.16.3.31:1521:orcl";
	private static final String user = "hr";
	private static final String pwd = "oracle4U";
	
	
	private Connection con;
	private PreparedStatement pstmt;
	
	private void connDB() {	//DB 접속 메소드
		try {
			Class.forName(driver);	//ojdbc 드라이버 로드
			System.out.println("Oracle 드라이버 로딩 성공")
			;
			con = DriverManager.getConnection(url, user, pwd);  //접속객체 생성
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB();	// 네 가지 정보로 데이터베이스를 연결합니다.
			
			String query = "select * from t_member where password=?";
			System.out.println(query);

			pstmt = con.prepareStatement(query);
			System.out.println("PreparedStatement 생성 성공");
			
			pstmt.setNString(1, "1212");

			ResultSet rs = pstmt.executeQuery(); //SQL문으로 회원 정보를 조회합니다.

			while (rs.next()) {
				String id = rs.getString("id");	//조회한 레코드의 각 컬럼 값을 받아옵니다.
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();	//각 컬럼값을 다시 MemberVO() 객체의
				vo.setId(id);					// 속성에 설정합니다.
				vo.setPassword(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);		//설정된 MemberVO 객체를 다시 ArrayList에 저장합니다.
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	//조회한 레코드의 개수만큼 MemberVO 객체를
	}					//저장한 ArrayList를 반환합니다.


}
