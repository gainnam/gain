package kr.or.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * OpenApi 클래스로 HRD-net에서 제공하는 구직훈련과정API 목록을 출력하는 자바 앱
 * 
 * @author gainnam
 *
 */
public class OpenApi {
	// 외부연계 역할 메서드(아래)
	public static void serviceApi() {
		BufferedReader bufferedReader = null;// HRD-net에서 전송받은 데이터를 임시 저장하는 공간.
		String urlStr = "http://www.hrd.go.kr/hrdp/api/apipo/APIPO0101T.do?returnType=XML&srchTraOrganNm=%ED%9C%B4%EB%A8%BC&pageSize=10&srchTraArea1=44&authKey=k2WZQI4EhGYrMfow5z1ZcVcla95dyqIA&sort=ASC&outType=1&srchTraStDt=20201108&srchTraArea2=44133&pageNum=1&sortCol=TR_STT_DT&srchTraEndDt=20210317&srchTraPattern=N1&srchPart=-99&apiRequstPageUrlAdres=/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp&apiRequstIp=118.34.43.143";
		try {
			URL url = new URL(urlStr);
			try {
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");//URL쿼리스트링으로 파라미터를 보낸다.
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "euc-kr"));
				String result="";
				String line;
				int cnt = 0;
				//while반복문
				while((line=bufferedReader.readLine()) !=null) {
					cnt = cnt + 1;
					result = result + line + "\n";//\n은 Newline나타내는 기호(줄바꿈=엔터)
				}
				System.out.println("버퍼리더로 읽어들인 최종 결과값은 아래입니다. while 반복횟수는" + cnt);
				//System.out.println(result);//2줄로 읽어서 끝
				//XmlUtils.java클래스를 이용해서 xml태그 내용을 예쁘게 출력(아래)
				String result_xmlUtils = XmlUtils.formatXml(result);
				System.out.println(result_xmlUtils);
			} catch (IOException e) {
				// Http웹 접근에러상황 발생
				System.out.println("Http웹접근 에러입니다. 왜냐하면 "+ e.toString());
			}
		} catch (MalformedURLException e) {
			// 외부 연계 URL에 접근하지 못했을 때, 에러상활발생
			System.out.println("URL접근에 실패하셨습니다. 왜냐하면" + e.toString());
		}finally {
			//콘솔화면에 현재 PC시간을 표시해서 진짜 스케쥴대로(5초단위로) 작동되는지 확인
			Calendar calendar = Calendar.getInstance();
			System.out.println(calendar.getTime());
		}
	}

	// static메서드는 new키워드로 객체오브젝트 생성없이 바로 접근이 가능한 메서드를 말합니다.
	public static void main(String[] args) {
		//메인스레드는 1개, 다른 스레드를 추가로 실행할 때, Runnable메서드를 사용함.(아래)
		//추가스레드를 스케쥴로 실행할 때, 실행간격으로 사용할 변수(5초로 지정)
		int sleepSec = 10;
		//주기적인 스레드작업(Concurrent동시작업)을 위한 코딩: new키워드로 실행 가능한 오브젝트 변수 exec 변수 생성
		//final: 현재클래스에서만 사용하겠다는 명시적인 의미
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {//스레드를 이용해서 5초 단 위로 메서드 반복실행
			public void run() {
				serviceApi();
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
		
		// 일반 메서드와 스테틱 메서드의 호출 차이
		// StaticTest staticTest = new StaticTest();
		// staticTest.test2();//!static은 오브젝트 생성 후 접근이 가능
		// StaticTest.test();//static은 오브젝트 생성없이 클래스에 바로 접근 가능
		/*
		 * int sum = 0; 
		 * while(sum<10) { 
		 * sum = sum + 2;//sum=sum+1로 누적로직 확인.
		 * System.out.println("지금까지 누적된 값은" + sum + "입니다.");
		 *  }
		 */
	}

}

/*class StaticTest {// static은 메서드, 변수에도 해당되는 이론
	public static void test() {
		// static은 메모리 기준으로 고정메모리를 할당받아 다른데서도 막 사용가능(바로 실행가능)
		// 메서드를 만드는 순간(읽어들여 로딩하는 순간)다른데서 사용 가능
		System.out.println("스테틱 테스트");
	}

	public void test2() {
		// 일반 메서드는 new키워드로 오브젝트를 생성할 때 메모리 할당을 받음.(그래야 실행 가능)
		System.out.println("일반 테스트");
	}
}
*/
