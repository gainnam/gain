package org.edu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.edu.service.IF_MemberService;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * CommonController 공통사용(Admin, Home 두 군데서)
 * @author gainnam	
 *
 */
@Controller
public class CommonController {
	
	@Inject
	IF_MemberService memberService;
	
	@SuppressWarnings("serial")
	/*
	 * 첨부파일의 확장자를 비교해서 이미지인지, 엑셀이나 한글과 같은 일반 파일인지 확인하는  List변수
	 * 사용용도: 게시물 상세보기 첨부파일이 이미지면 미리보기 이미지가 보이도록, 이미지가 아니면 다운로드 링크만 보이도록
	 * 사용용도2: 메인페이지 최근 갤러리 게시물에서 첨부파일이 있으면 미리보기 이미지가 나오게 되는데,
	 *  만약 첨부파일이 이미지가 아니면 대체이미지로 보이기 위해서 확장자 체크가 필요합니다.	 
	 *  변수 생성 후 바로 List 3개 입력처리
	 */
	private ArrayList<String> extNameArray = new ArrayList<String>() {
		{
			add("gif");
			add("jpg");
			add("png");
		}
	};
	//첨부파일 업로드 할 경로를 변수값으로 가져옴 servlet-context.xml에 있는 내용
	@Resource(name="uploadPath")
	private String uploadPath;//위 uploadPath영역의 값을 uploadPath변수에 저장.
	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	//파일 업로드 메서드구현(아래)
	public String[] fileUpload(MultipartFile file) throws IOException {
		String realFileName = file.getOriginalFilename();//jsp에서 전송한 파일명->확장자를 구하려고 사용
		//폴더에 저장할 PK용 파일명 만들기(아래)
		UUID uid = UUID.randomUUID();//유니크 아이디 생성 Unique ID
		String saveFileName = uid.toString() + "." + realFileName.split("\\.")[1];
		//split("정규표현식");(Regular Expression):realFileName을 .으로 분할해서 배열변수로 만드는 메서드
		//예를 들면, abc.jpg ->realFileName[1] = jpg 으로 결과가 나옵니다.
		String[] files = new String[] {saveFileName};//saveFileName 스트링형을 배열변수 files로 형변환
		byte[] fileData = file.getBytes();//jsp폼에서 전송된 파일이 fileData변수(메모리)에 저장됩니다.
		File target = new File(uploadPath, saveFileName);//파일저장 하기 바로 전 설정저장.
		FileCopyUtils.copy(fileData, target);//실제로 target폴더에 파일로 저장되는 메서드=업로드 종료
		return files;//1개 이상의 파일 업로드 시 저장된 파일 명을 배열로 저장한 변수
	}

	//REST-API컨트롤러로 사용(아래) @ResponseBody애노테이션으로  json텍스트데이터를 반환함(아래)
	//아래는 RestAPI 백엔드단, Ajax(jsp), Rest-API의 프론트엔드단.
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody
	public String id_check(@RequestParam("user_id") String user_id) {
		String result = "0";//아이디 중복값을 체크하는 변수.변수기본값0은 중복값없음.
		//REST_API 서비스에서는 스프링을 통해서 Ajax로 에러메세지를 받을 수 없기 때문에 여기서 에러를 처리해야 함.
		try {
			MemberVO memberVO = memberService.readMember(user_id);
			if(memberVO != null) {//아이디 중복값이 있을 경우
				result = "1";
			}
		} catch (Exception e) {
			// 위 readMember메서드가 에러발생 시
			result = e.toString();
		}
		return result;//결과값은 0, 1, 에러메세지
	}
}
