package kr.or.test;

public class OracleDriverTest {

	public static void main(String[] args) {
		// ojdbc6.jar 스프링에서 사용할 오라클 드라이버 클래스 테스트
		//오라클 드라이버를 수동으로 로드(인스턴스)를 만드는 매서드: Calss.forname("")
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("ojdbc6 Driver를 정상적으로 로드했습니다.");
		} catch (ClassNotFoundException e) {
			// ojbc6.jar클래스를 사용할 수 없을 때
			System.out.println("ojdbc6 Driver를 로드할 수 없습니다.");
		}
		//현재 dependence로 등록된 jdbc 몇 개가 있는 지 확인
		//ojdbc, mysql, hsql
	}

}
