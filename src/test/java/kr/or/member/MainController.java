package kr.or.member;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import kr.or.member.MemberService;
import kr.or.member.MemberVO;

public class MainController {

	public static void main(String[] args) throws IOException {
		// 실행 메서드
		MemberVO memberVO = new MemberVO();// 자바 오브젝트 생성하는 방법
		memberVO.setName("홍길동");
		memberVO.setAge(45);
		memberVO.setPhoneNum("000-0000-0000");
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setName("성춘향");
		memberVO2.setAge(18);
		memberVO2.setPhoneNum("111-1111-1111");
		MemberVO memberVO3 = new MemberVO();
		memberVO3.setName("각시탈");
		memberVO3.setAge(28);
		memberVO3.setPhoneNum("222-2222-2222");
		// 위 각각 3개의 레코드를 1군데 담을 배열 클래스오브젝트를 하나 생성(아래)
		MemberVO[] members = new MemberVO[3];
		members[0] = memberVO;
		members[1] = memberVO2;
		members[2] = memberVO3;
		// 전에 사용했던 출력보다는 개선된 방식으로 출력(아래) MemberService 서비스전용 클래스만들어서 처리.
		MemberService memberService = new MemberService();// 자바 오브젝트객체 생성하는 방법\
		memberService.printMember(members);// 서비스클래스의 메서드호출
	
		//서버시간 가져오기 코딩(아래)
		//서버시간을 가져올 수 있는 common-net 메이븐 디펜던시사용 라이브러리를 추가.
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);
		//네트워크로 서버시간을 가져올 때, 응답이 1초를 넘기면, 재접속해라.
		timeClient.open();
		//pool.ntp.org는 서버시간을 보내줄 실제 서버 주소.
		InetAddress address = InetAddress.getByName("pool.ntp.org");
		TimeInfo timeInfo = timeClient.getTime(address);
		//서버시간을 담은 timeinfo 객체변수를 사용(아래)
		//TimeStamp 1970년 부터 초 단위로 계산된 현재까지의 초합산결곽ㅄ(아래)
		long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
		//위 반환받은 타임스탬프초 값을 삶이 알아볼 수 있는 시간으로 변환(아래)
		Date nowDate = new Date(returnTime);
		//System.out.println("현재 서버시간은-영어버전 " +nowDate);
		//Wed Dec 09 11:24:03 KST 2020 -> 2020-12-09 11:24:03 AM
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//System.out.println("현재 서버시간은-한국버전 " +formatDate.format(nowDate));
		//위에는 개선된 버젼 일단 실행안되게 해둠
		LocalDateTime localDateTime = nowDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		System.out.println("서버 시간은 " + localDateTime);
		System.out.println("로컬PC시간은 " + LocalDateTime.now());
		
	}
	
}