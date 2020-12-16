package org.edu.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * JUnit 자바단 위 테스트이고, DataSource 지정 후 DB(Hsql, Mysql, Oracle) 접속,
 * 데이터베이스 테이블에 Creat, Read, Update, Delete 테스트용 클래스
 * 제이유닛4클래스를 사용.
 * 단위테스트는 톰캣이 식ㄹ행되지 않아도 작동이 되야 합니다.
 * 그래서, 테스트 클래스 상단에 servelet-context.xml 이러한 설정파일을 불러들여서 실행이 가능합니다.
 * @author  gainnam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**.xml"})
@WebAppConfiguration
public class DataSourceTest {
	
	@Inject
	DataSource dataSource;//자바에서처럼 new keyword로 object 생성 안하고, 스프링에서는 @Inject로 object생성해서 사용
	
	@Inject
	IF_MemberDAO memberDAO;
	
	@Inject//사용하면 안되는 이유: 클래스 상단에 @Controller,@Service, @Repository 이런 내용만 @Inject합니다.
	MemberVO memberVO;//기존 자바처럼 new MemberVO()오브젝트를 생성하지않고, 주입해서 사용.(runtime에서 에러가 남)
	
	public String memberPrimaryKey() {
		//사용자 프라이머리키 생성하는 메서드 년월일시분처 + 밀리초
		Date primaryKey = new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println("프라이머리키 : " + newFormat.format(primaryKey));
		return "user_" + newFormat.format(primaryKey);
	}
	
	@Test
	public void updateMember() throws Exception {
		//CRUD 중 Update 테스트 구현 특징, user_id는 프라이커리키 이기 때문에 수정대상이 아닙니다.
		//MemberVO memberVO = new MemberVO();
		memberVO.setUser_id("admin");
		memberVO.setUser_name("홍길동");
		memberVO.setUser_pw("");//빈칸으로 보낸다면->암호를 수정하지 않는 사람을 가정하는 것.
		memberVO.setEmail("test@test.com");
		memberVO.setPoint(100);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		String user_id = memberVO.getUser_id();//memberVO의 오브젝트의 데이터는 1개의 레코드이기때문에 반환값이 1개만
		memberDAO.updateMember(memberVO);
	}
	
	@Test
	public void readMember() throws Exception {
		//CRUD 중 Read 테스트 구현
		//MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.readMember("admin");
		System.out.println("admin 에 대한 상세정보 입니다.");
		System.out.println(memberVO.toString());
	}
	
	@Test
	public void deleteMember() throws Exception {
		//CRUD 중 Delete 테스트 구현(쿼리 -> DAO -> memberDAO주입받은 오브젝트사용)
		memberDAO.deleteMember("user_20201215145621755");//삭제메서드 -> 쿼리 호출
	}
	
	@Test
	public void insertMember() throws Exception {
		//CRUD 중 Create 테스트
		//MemberVO memberVO = new MemberVO();
		memberVO.setUser_id("user03");
		memberVO.setUser_name("사용자03");
		memberVO.setUser_pw("1234");
		memberVO.setEmail("user03@abc.com");
		memberVO.setPoint(100);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_USER");
		Date reg_date = new Date();
		memberVO.setReg_date(reg_date);//매퍼쿼리에서 처리로 대체
	}
	@Test
	public void selectMember() throws Exception {
		List<MemberVO> memberList = memberDAO.selectMember("user_name", "홍");
		System.out.println("회원리스트 테스트 입니다.");
		System.out.println("회원리스트 테스트 " + memberList.toString());
	}
	@Test
	public void dbConnectionTest() throws Exception {
		try {//내부에서 {}에러발생 시 실행을 중지하고, catch{}구문이 실행됩니다. 예외처리라고 함.
			Connection connection = dataSource.getConnection();//DB커넥션 오브젝트 만드는 과정. new 사용ㄴ
		System.out.println("데이터베이스 접속이 성공하였습니다.");
	} 	catch (SQLException e) {
		System.out.println("데이터베이스 접속에 실패하였습니다. 왜냐하면" + e);
		//e.printStackTrace();
	}//try~catch는 테스트에서만 사용하고, throws Exception으로 예외를 스프링
	}
	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("JUnit테스트 시작입니다.");
	}

}
