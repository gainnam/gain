package kr.or.test;

import java.util.Calendar;

/**
 * 외부Enum클래스를 이용해서 열거형 자료형 테스트
 * @author 남가인
 *
 */
public class Step3 {

	public static void main(String[] args) {
		// 메인 메서드-스레드 시작.
		Week today = null;//Week 자료형용  today변수
		//String today2= null;과 같다고 생각하면 됨.
		//MemberVO memberVO = new MemberVO(); 이것또한 같음.
		//MemberVO자료형용 memberVO객체변수/오브젝트 선언
		//현재 날짜가 무슨 요일인지 한글로 확인하는 프로그램(아래)
		Calendar calendar = Calendar.getInstance();//객체선언 calendar 객체변수/오브젝트선언/인스턴스 변수
		//calendar오브젝트에서 현재 요일을 반환받기(1~7까지 반환)
		int intweek = calendar.get(Calendar.DAY_OF_WEEK);//calendar객체, Calendar클래스
		//스위치 조건문으로 1~7까지 반환값을 한글로 변환(아래)
		switch(intweek) {
		case 2:
			today = Week.월;
			break;//break는 case 문장을 빠져나가는 명령
		case 3: 
			today = Week.화;//Week자료형에 바로접근 Week.
			break;
		case 4:
			today = Week.수;
			break;
		case 5:
			today = Week.목;
			break; 
		case 6:
			today = Week.금;
			break;
		case 7:
			today = Week.토;
			break;
		case 1:
			today = Week.일;
			break;
		}
		System.out.println("오늘 요일은: " +today+ "요일 입니다.");
		
		if(today == Week.수) {
			System.out.println("수요일에는 축구를 합니다.");}
			else {
				System.out.println("열심히 자바 공부를 합니다.");
			}
		
		//Calendar클래스는 달력에서 날짜, 본인 컴퓨터 날짜라서 데이터가 훼손될 가능성이 있습니다.
		//이 때, 해결책은 서버시간을 가져와서 출력하면, 사용자가 날짜를 수정 못하게 됩니다.
		}
	}


