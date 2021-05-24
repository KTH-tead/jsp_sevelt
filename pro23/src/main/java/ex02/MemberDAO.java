package ex02;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ex01.MemberVO;

public class MemberDAO {
	
	//SqlSessionFactory 타입 변수 선언
	//이 변수에 SqlSessionFactory 인스턴스가 저장됩니다.
	//이 변수를 통해 마이바티스의 Connection Pool	
	public static SqlSessionFactory sqlMapper = null;

	//마이바티스의 Connection Pool을 이용하여 데이터베이스 세션을 생성하는 메소드를 정의합니다
	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				//MemberDAO의 각 메서드 호출 시 src/mybatisMapper/MybatisConfig.xml에서
				//설정 정보를 읽은 후 데이터베이스와의 연동 준비를 합니다.
				//SqlSessionFactory 인스턴스와 관련된 설정파일 위치 객체 생성
				String configFileName = "mybatisMapper/MybatisConfig.xml";

				//Resurces 정적클래스의 메소드를 이용하여 MyBatis 설정파일의 내용을
				//읽어와서 Reader 타입의 reader 객체에 저장합니다.
				Reader reader = Resources.getResourceAsReader(configFileName);
				
				//마이바티스를 이용하는 SqlSessionFactory 객체를 생성하여
				//sqlMapper 변수에 저장.
				//MybatisConfig.xml파일의 기본 환경(development)으로 접속됨.
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				
				
				//SqlMapConfig.xml파일의 기본 환경으로 접속됨.
				//String environment="development";
				//sqlMapper = new SqlSessionFactoryBuilder().build(reader,environment);
				
				//사용된 reader 리소르 자원을 해제합니다.
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//SqlSessionFactory 인스턴스 객체가 저장된 sqlMapper 반환
		return sqlMapper;

	}

	public List<MemberVO> selectAllMemberList() {
		//위에서 정의된 getInstance() 메소드로, SqlSessionFactory 인스턴스를 가져와 
		//sqlMapper 에 저장
		sqlMapper = getInstance();
		
		//SqlSessionFactory 인스턴스 객체의 openSession() 메소드로
		//SqlSession 인스턴스 객체를 가져옵니다.
		//SqlSession 인스턴스 객체를 통해, member.xml의 SQL문이 호출됩니다.
		
		SqlSession session = sqlMapper.openSession();

//첫번재 실습용

		
		List<MemberVO> memberList = null;  //결과를 담을 List 객체 생성
		
		//여러개의 레코드를 조회하므로 selectList() 메서드에 실행하고자 하는
		//SQL문의 id를 인자로 전달합니다.
		//아래에서 mapper.member는 SQL매핑XML파일(member.xml)의 namespace에 설정된 값입니다. 
		memberList = session.selectList("MemberMapper.selectAllMembersList");
		session.close();
		return memberList;

/*
//HashMap 실습용		
		List<MemberVO> membersList = null;  //결과를 담을 List 객체 생성
		membersList = session.selectList("mapper.member.selectAllMembersList");
		return membersList;
*/		
	}
	
	public String selectMemberName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		//selectOne() 메소드로 인자로 지정한 SQL문을 실행한 후, 한 개의 문자열을 반환합니다.
		String name = (String) session.selectOne("MemberMapper.selectMemberName");
		session.close();
		return name;
	} 
		
	public String  selectMemberPassword() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		//selectOne() 메소드로 인자로 지정한 SQL문을 실행한 후, 한 개의 정수를 반환합니다.
		String password = (String) session.selectOne("MemberMapper.selectMemberPassword");
		session.close();
		return password;
	}
	
	
}
