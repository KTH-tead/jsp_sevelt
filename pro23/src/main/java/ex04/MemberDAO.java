package ex04;

import java.io.Reader;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				
				//sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				
				String environment="oraclehr";
				sqlMapper = new SqlSessionFactoryBuilder().build(reader, environment);
				
							
				//사용된 reader 리소르 자원을 해제합니다.
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//SqlSessionFactory 인스턴스 객체가 저장된 sqlMapper 반환
		return sqlMapper;

	}

//전체 회원목록 조회
	public List<MemberVO> selectAllMembersList() {
		//위에서 정의된 getInstance() 메소드로, SqlSessionFactory 인스턴스를 가져와 
		//sqlMapper 에 저장
		sqlMapper = getInstance();
		
		//SqlSessionFactory 인스턴스 객체의 openSession() 메소드로
		//SqlSession 인스턴스 객체를 가져옵니다.
		//SqlSession 인스턴스 객체를 통해, member.xml의 SQL문이 호출됩니다.
		
		SqlSession session = sqlMapper.openSession();

//첫번재 실습용
		
		List<MemberVO> membersList = null;  //결과를 담을 List 객체 생성
		
		//여러개의 레코드를 조회하므로 selectList() 메서드에 실행하고자 하는
		//SQL문의 id를 인자로 전달합니다.
		//아래에서 mapper.member는 SQL매핑XML파일(member.xml)의 namespace에 설정된 값입니다. 
		membersList = session.selectList("MemberMapper.selectAllMembersList");
		session.close();
		return membersList;

/*
//HashMap 실습용		
		List<MemberVO> membersList = null;  //결과를 담을 List 객체 생성
		membersList = session.selectList("mapper.member.selectAllMembersList");
		return membersList;
*/		
	}

//회원이름조회 기초 실습
	public String selectMemberName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		//selectOne() 메소드로 인자로 지정한 SQL문을 실행한 후, 한 개의 문자열을 반환합니다.
		String name = (String) session.selectOne("MemberMapper.selectMemberName");
		session.close();
		return name;
	} 

//회원암호 조회 기초 실습
	public String  selectMemberPassword() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		//selectOne() 메소드로 인자로 지정한 SQL문을 실행한 후, 한 개의 정수를 반환합니다.
		String password = (String) session.selectOne("MemberMapper.selectMemberPassword");
		session.close();
		return password;
	}

//회원검색: By 아이디
	public MemberVO selectMemberById(String id){
	      sqlMapper=getInstance();
	      SqlSession session=sqlMapper.openSession();

	      //SQL-매핑파일의 해당 문장에 사용된 #{변수이름}에서 변수이름과 selectOne의 파라미터
	      //이름이 동일해야 합니다.
	      MemberVO memberVO = session.selectOne("MemberMapper.selectMemberById", id);
	      session.close();
	      return memberVO;
	   }
//회원검색: By 암호
	public List<MemberVO> selectMembersByPassword(String password) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = session.selectList("MemberMapper.selectMembersByPassword", password);
		session.close();
		return membersList;
	}
//새 회원 등록	
	public int insertNewMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = 0;
		result = session.insert("MemberMapper.insertNewMember", memberVO);
		System.out.println("insert행수: " + result);
		session.commit();
		session.close();
		return result;
	}
	
//id 중복 검사 메소드
	public boolean selectDuplicateIdCheck(String id) {
	
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		boolean result = false;
		
		String resultStr = session.selectOne("MemberMapper.selectDuplicateIdCheck", id);
				
		result = Boolean.parseBoolean(resultStr);
		
		//System.out.println(result);
		session.close();
		return result;  //boolean 유형의 true/false 값이 전달됩니다.
	}
	

// 23.4.6 회원 정보 수정 
	public int updateMemberById(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		//update문 호출 시SqlSession 객체의 update() 메소드를 이용
		int result = session.update("MemberMapper.updateMemberById", memberVO);
		session.commit();
		session.close();
		return result;
	}   

	/* 23.4.7 회원 정보 삭제 */
    public int deleteMemberById(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.delete("MemberMapper.deleteMemberById", id);
		//JDBC를 사용하는 경우, DML문 실행 후, 반드시 커밋합니다.
		session.commit();
		session.close();
		return result;
    } 
    
    //동적 SQL문
    
    //23.5.1 <if> 태그로 동적 SQL문 만들기 
    //23.5.2 <choose><when>태그로 동적 SQL문 만들기 
    public List<MemberVO> searchMember1(MemberVO  memberVO){
        sqlMapper=getInstance();
        SqlSession session=sqlMapper.openSession();
        
        //회원 검색창에서 전달된 이름과 나이값을 memberVO에 설정하여 SQL문으로전달.
        
        //23.5.1  <if>태그로 동적 SQL문 만들 때
        //List<MemberVO> list = session.selectList("MemberMapper.searchMember",memberVO);
        //23.5.2  <choose><when>태그로 동적 SQL문 만들 때 
        List<MemberVO> list = session.selectList("MemberMapper.searchMember2",memberVO);
        //
        //List<MemberVO> list = session.selectList("MemberMapper.searchMember3",memberVO);
        session.close();
        return list;		
    }

    
// 23.5.3 <forEach> 태그로 회원 정보 조회하기
	public List<MemberVO> forEachSelect(List<String> nameList){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		
		List<MemberVO> list=session.selectList("MemberMapper.forEachSelect",nameList);
		//List<MemberVO> list=session.selectList("mapper.member.foreachSelect2",nameList);
		session.close();
		return list;		
	}
	
//LIKE 연산자 사용 실습 메소드 
	public List<MemberVO> selectLike(String email){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		
		List<MemberVO> list=session.selectList("MemberMapper.selectLike", email);
		session.close();
		
		return list;		
	}
//LIKE 연산자 사용 실습 메소드2 
	public List<MemberVO> selectLike2(MemberVO memberVO){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		
		List<MemberVO> list=session.selectList("MemberMapper.selectLike2", memberVO);
		session.close();
		
		return list;		
	}

//23.5.4 <forEach> 태그로 회원 정보 추가하기
	public int forEachInsert(List<MemberVO> memList){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();
		    
		//SQL문을 호출하여 데이터 입력작업 수행
		//int result = session.insert("mapper.member.forEachInsert",memList);
		//int result = session.insert("mapper.member.forEachInsert2",memList);
		int result = session.insert("MemberMapper.insertwithsequence",memList);
		session.commit();
		System.out.println(result);
		return result ;		
	}
    
//VO 객체 없이 HashMap을 이용한 데이터 조회
	public List<HashMap<String, String>> hiredEmployees(){
		sqlMapper=getInstance();
		SqlSession session=sqlMapper.openSession();

		List<HashMap<String, String>> hiredCountList  =
		session.selectList("MemberMapper.hiredCountEmployees");
		
 		

		System.out.println(hiredCountList.toString());

		session.close();
		return hiredCountList ;		
	}
   
    
// 23.4.5 HashMap을 이용한 회원 정보 입력
	public int insertMember2(Map<String,String> memberMap){
		sqlMapper=getInstance();
		SqlSession session = sqlMapper.openSession();

		int result = session.insert("MemberMapper.insertMember2",memberMap);
		session.commit();
        session.close();
		return result;		
	}

}
