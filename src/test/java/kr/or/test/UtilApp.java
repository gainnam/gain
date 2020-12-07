package kr.or.test;

public class UtilApp {

	public static void main(String[] args) {
		// 클래스의 실행 진입점
		// java.lang Math클래스를 사용해서 랜덤하게 숫자를 뽑아 봅니다.
		// 제비뽑기로 생각하면, 꽝이 0
		System.out.println("1부터 100까지 랜덤한 숫자 출력하기 = " 
		+ (int)(Math.random() * 100+1) 
			//결과가 float(실수)로 나오기 때문에, 정수형(int)을 형변환해서 출력
			//아이디 자동생성기 만들 때, 매스클래스의 랜덤메서드 사용하게 됩니다.
			//기본형을 객체로 표현했다는 int(Not null) -> Integer(null허용),
			//float -> Float, double -> Double
				);

	}

}
