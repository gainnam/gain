package kr.or.test;
//오리지날 자바형식 프로그램 형식: 자바이론공부 -> 스프링 웹프로그램과는 형식이 틀립니다.
public class ClassApp {

	public static void main(String[] args) {
		// 이 클래스의 진입매서드  main 실행
		Circle c = new Circle(5);//C라는 인스턴스 오브젝트 클래스 생성 new 키워드 생성자circle(반지름값5)
		//Circle클래스를 c 이름으로  prefix해서 사용하는 겁니다.
		Circle c2 = new Circle(10);
		//자바프로그램의 특징은 OOP프로그래밍의 대표적인 사례, Object=위에서 new키워드로 생성한 c 오브젝트
		//자바에서 OOP를 사용방식을 성명하는 예,
		double print_result = c.getgainnam();
		/*System.out.println("첫번째 원의 넓이는" + print_result + "입니다.");*/
		double print_result2 = c2.getgainnam();
		/*System.out.println("두번째 원의 넓이는" + print_result2 + "입니다.");*/
		
		Employee employee = new Employee();
		Salesman salesman = new Salesman();
		Development developement = new Development();
		/*employee.doJob();
		salesman.doJob();
		developement.doJob();*/
		GraphicObject graphicobject = new Triangle();
		graphicobject.draw();
	}

//추상클래스에 대한 연습(아래)
//추상클래스 또는 인터페이스를 사용하는 목적은 두꺼운 책에서 목차를 만드는 것과 똑같은 역할.
abstract class GraphicObject {
	int x, y;//그래픽오브젝트 클래스 멤버변수 선언
	abstract void draw();//명세만있고 구현 내용이 없는 추상메서드 선언
	//다르게 해석하면, 책에서 목차만 있고, 내용이 없는 구조. 내용은 별도의 클래스에 있음.
	abstract void draw1();
	abstract void draw2();
	abstract void draw3();
	abstract void draw4();
	abstract void draw5();
}


class Triangle extends GraphicObject {
	//매서드 오버라이드(메서드 이름이 같은 것을 상속관계에서 실행)= 매서드 재정의, 대표적인 다형성을 구현
	@Override
	void draw() {
		System.out.println("  *");
		System.out.println(" ** ");
		System.out.println("*****");
	}
	
}

//클래스 상속에 대한 연습(아래)
class Employee {//회사의 모든 직원들 클래스
	int nSalary;//회사직원 봉급 멤버변수
	String szDept;//회사직원의 부서명 멤버변수
	public void doJob()	{
		System.out.println("환영합니다. 직원분들!");
	}
	 
}
class Salesman extends Employee {
	public Salesman( ) {//클래스명과 똑같은 매서드를 생성자 메서드 라고 합니다.
		szDept = "판매 부서";//부모클래스  Employee의 szDept멤버변수를 자식클래스에서도 사용가능
	}
	public void doJob() {
		System.out.println("환영합니다." + szDept + "입니다.");
	}
}

class Development extends Employee {
	public Development() {
		szDept = "계발 부서";
	}
	public void doJob() {
		System.out.println("환영합니다." + szDept + "입니다.");
	}
}
class Circle {
	private int r;//멤버변수 반지름 이클래스에서 전연변수
	public Circle(int a) {//생성자메서드a인자=매개변수=파라미터 값은 new키워드로 오브젝트 만들어질 때 발생
		r = a;}
	
	public double getgainnam() {
		return r*r*3.14;//원의 넓이를 반환
	}
}
}

	
			
