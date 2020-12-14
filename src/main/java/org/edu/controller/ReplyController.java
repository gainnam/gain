package org.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReplyController.java 클래스
 * 댓글 구현  Rest-API 전용 컨트롤러  = RestAPI 서버
 * @author gainnam	
 *
 */
@RestController
public class ReplyController {
	//댓글 입력 메서드(아래)
	@RequestMapping(value="/reply/reply_write", method=RequestMethod.POST)
	public ResponseEntity<String> reply_write() {
		ResponseEntity<String> responseEntity = 
				new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		//전송내용 text-body"SUCCESS", 전송상태 HTTpStatus.OK(200)
		//ResponseEntity는 json텍스트를 반환하는데, HttpStatus.OK(200):"SUCCESS or OK"
		//HttpStatus.BAD_REQUEST(400):e.getMessage()실패메세지값
		return responseEntity;
		
	}
	//기존 @Controller의 메서드 반환값이 파일위치, 
	//대신에 @RestController의 메서드의 반환값은json텍스트(body, 전송상태값) Ajax로 호출한 jsp로 리턴보내게 됩니다.
	//ResponseEntity 위와 같음.
}