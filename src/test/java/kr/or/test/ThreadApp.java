package kr.or.test;

public class ThreadApp extends Thread  {

	public static void rum() {
		System.out.println("스레드 시작 시 자동실행됩니다!");
	}
	public static void main(String[] args) {
		//메인스레드 시작 진입접 main()
		(new ThreadApp()).start();//아래 2줄과 이 1줄은 같음.
		//ThreadApp threadApp = new ThreadApp();(일반적으론 이렇게)
		//threadApp.start();
	}
}
