package org.edu.controller;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.hsqldb.lib.SimpleLog;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonDataController {

	@Inject
	private IF_MemberDAO memberDAO;
	private Logger logger = Logger.getLogger(SimpleLog.class);
	
	//RestAPI인증 서버(아래): 안드로이드 앱에서 로그인에 사용
	@RequestMapping(value = "/android/login", method = RequestMethod.POST)
	public ResponseEntity<MemberVO> androidLogin(@RequestParam("txtUsername") String user_id, @RequestParam("txtPassword") String user_pw) {
		ResponseEntity<MemberVO> entity = null;
		try {
			MemberVO memberVO = memberDAO.readMember(user_id);
			String bcryptPassword = memberVO.getUser_pw();
			//스프링 시큐리티 4.x BCryptPasswordEncoder로 암호화 된 값을 비교
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);//()속 숫자는 암호화 강도(10은 강약 정도)
			if (passwordEncoder.matches(user_pw, bcryptPassword)) {
				logger.debug("계정정보 일치");
				entity = new ResponseEntity<>(memberVO, HttpStatus.OK);//code 200
			}
			else {
				logger.debug("계정정보 불일치");
				entity = new ResponseEntity<>(HttpStatus.NO_CONTENT);//code 204
			}
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);//code 400
		}
		return entity;//json(key:value)데이터로 반환값 리턴
	}
}
