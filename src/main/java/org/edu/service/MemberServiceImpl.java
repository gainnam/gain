package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Service;

/**
 * MemberServiceImpl는 DAO 메서드 호출을 구현
 * @author gainnam
 *
 */
@Service
public class MemberServiceImpl implements IF_MemberService{

	@Inject
	IF_MemberDAO memberDAO;//IF_MemberDAO라는 인터페이스를 주입받아서 memberDAO라는 클래스형 변수(오브젝트)를 생성
	
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		// 회원정보 조회 DAO호출(아래)
		return memberDAO.selectMember();
	}

}
