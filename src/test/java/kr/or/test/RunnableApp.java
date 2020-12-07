package kr.or.test;

public class RunnableApp implements Runnable {

	public static void main(String[] args) {
		//Runnable 인터페이스를 구현하는 내용
		(new Thread(new RunnableApp())).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("러너블 시작 시 자동실행됩니다!");
	}

}
