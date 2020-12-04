package kr.or.test;

import org.springframework.beans.factory.annotation.Autowired;

public class LoopApp {

	@Autowired
	public static void main(String[] args) {
		// 반복문 중에 for문
		int[] numbers = {11,21,31,41,51,61,71,81,91,101};//배열변수 선언
		int cnt;//반복횟수 카운트(셈)하는 역할 변수 선언
		//for문(반복의 시작값정의;종료값정의;증가값) {반복할 때 구현내용}
		for(cnt=0; cnt<10; cnt++) {//cnt++ = cnt+1;의미는 cnt가 1씩 증가한다
			System.out.println("현재 카운터는" + numbers[cnt]);
			//println함수는 한 번 출력 후 newline 엔터치는 역할.2 
			
			//10번 반복을 하는데, numbers[0], numbers[1], numbers[2], ... , numbers[9]
		}                                                                                                                                                                                                                                                                              

		
		//향상된 for문, 스프링jsp에서 제일 많이 사용햐게 될 구문.
		String[] names = {"사용자1","사용자2","사용자3","사용자4","사용자5"};
		char name = '사';//임베디드장비와 자바프로그램 통신 시 이것ㄷ을 사용합니다.
		//임베디드 프로글매에서 char을 사용하는 이유는 메모리를 효율적으로 사용하기 위해서 char을 사용합니다.
		//마지막 달에 아두이노 프로그램 배울 떄 char형을 사용하게 될 것.
		//자바에서 큰 따옴표는 문자열데이터를 감싸줄 때, 작은 따옴표는 1개문자 데이터를 감싸줄 때 
		//배열타입[] 1차원배열 = 가로선1row 하나의 줄(라인)
		//향상된  for문에서는 시작값, 끝값, 증가값 필요 없습니다.
		for(String user_name:names) {
			System.out.println("등록된 사용자 이름은" + user_name);
		};
		
		//향상된 for문, 회원정보를 jsp로 출력하기 현장과 비슷하게 구현
		String[][] members = {
				{"admin", "관리자", "admin@abc.com", "true", "2020-12-04", "ROLE_ADMIN"},
				{"user", "사용자1", "user@abc.com", "false", "2020-12-04", ".ROLE_USER"}
		};
				//배열타입[][] 2차원배열 = 표 1row, 2row 리스트가 있는 줄(여러 개 라인)
				System.out.println("=====회원리스트 입니다.=====");
				System.out.println("user_id |  user_name |  email |  use |  regdate |  levels");
				for(String[] member:members) {
					System.out.println(member[0] + "|" +member[1] + "|" +member[2]
				+ "|" + member[3] + "|" + member[4] + "|" + member[5]);
				}
				
		}

}
