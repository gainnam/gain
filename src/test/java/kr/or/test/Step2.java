package kr.or.test;
/**
 * 클래스자료형 사용에 대해서
 * @author 남가인
 *
 */
class MemberVO {
	//멤버번수 만들기(아래)
	private String name;
	private int age;
	private String phoneNum;
	
	//멤버변수는 보안때문에  private으로 만들었으나, get, set은 public으로 생성됩니다.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "디버그용 MemberVO [name=" + name + ", age=" + age + ", phoneNum=" + phoneNum + "]";
	}
}

public class Step2 {

	public static void main(String[] args) {
		// 실행 매서드
		MemberVO memberVO = new MemberVO();//자바 오브젝트 생성하는 방법
		memberVO.setName("홍길동");
		memberVO.setAge(45);
		memberVO.setPhoneNum("000-0000-0000");
		MemberVO memverVO2 = new MemberVO();
		memberVO.setName("성춘향");
		memberVO.setAge(18);
		memberVO.setPhoneNum("111-1111-1111");
		MemberVO memverVO3 = new MemberVO();
		memberVO.setName("각시탈");
		memberVO.setAge(28);
		memberVO.setPhoneNum("222-2222-2222");
		//위 각각 3개의 레코드를 1군데 담을 배열 클래스오브젝트를 하나 생성
		MemberVO[] members = new MemberVO[3];
		memebers[0] = memberVO;
		memebers[1] = memberVO2;
		memebers[2] = memberVO3;
		
	}

}
