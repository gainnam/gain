package org.edu.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

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
