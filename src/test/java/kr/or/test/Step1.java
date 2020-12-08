package kr.or.test;
/**
 * 내장변수와 배열 사용에 대해서
 * @author 남가인
 *
 */
public class Step1 {

	public static void main(String[] args) {
		//필드=클래스에 내부변수 만들기(아래)
		String name;//String은 데이터 타입 name이라는 변수가 담을 수 있는 형태를 지정.
		int age;//int타입이라서 숫자만 담을 수 있음.
		String phoneNum;//String타입이라서 문자열만 담을 수 있음.
		//변수에 값을 대입연산자로 대입(아래)
		name="홍길동";
		age=45;
		phoneNum="000-0000-0000";
		//위 내용을 출력하는 매서드 1개 사용(아래)
		printMember(name, age, phoneNum);
		name="성춘향";
		age=18;
		phoneNum="111-1111-1111";
		printMember(name, age, phoneNum);
		name="각시탈";
		age=28;
		phoneNum="222-2222-2222";
		printMember(name, age, phoneNum);
		
		//배열을 이용해 좀 더 간편하게 입력하고, 출력해보기.
		String[] names = {"홍길동", "성춘향", "각시탈"};
		int[] ages = {45, 18, 28};
		String[] phoneNums = {"000-0000-0000", "111-1111-1111", "222-2222-2222"};
		printMember(names, ages, phoneNums);//오버로드형 메서드 다형성의 대표사례
	}

	private static void printMember(String[] names, int[] ages, String[] phoneNums) {
		// member를 출력하는데 배열값을 받아서 구현(아래)
		int dataLength = names.length;//이름 배열에 있는 갯수
		for(int cnt=0; cnt<dataLength; cnt++) {
			System.out.println("cnt변수의 변화는= " + cnt);
			System.out.println("회원의 이름은: " +names[cnt]+ "| 나이는: " +ages[cnt]+ "| 연락처는: " +phoneNums[cnt]);
		}
		
	}

	private static void printMember(String name, int age, String phoneNum) {
		//member를 출력하는 프린트 메서드 구현내용
		System.out.println("회원의 이름은: " +name+ "나이는: " +age+ "전화번호는: " +phoneNum);	
	}

}
