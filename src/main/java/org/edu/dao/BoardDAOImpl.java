package org.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements IF_BoardDAO {

	@Inject // sql session tempelte주입받음
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// sql session 탬플릿(select, update, delete)같은 메서드가 포함)mapper쿼리 지정(아래)
		// sql session templete = mapper query(위 쿼리 + insert)를 실행할 때, 개발자가 DB커넥션,
		// disconnection 등을
		// 생각할 필요없이 사용 가능한 메서드 집합을 구성해 놓은 것이 sqlSession templete이다.
		return sqlSession.selectList("boardMapper.selectBoard", pageVO);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// sql세션템플릿 사용해서 게시물개수 구하기 매퍼쿼리 연결(아래)
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}

	@Override
	public BoardVO readBoard(Integer bno) throws Exception {
		// 게시물 상세보기 mapper query 연결(아래)
		return sqlSession.selectOne("boardMapper.readBoard", bno);
	}

	@Override
	public List<String> readAttach(Integer bno) throws Exception {
		// 게시물에 딸린 첨부파일 보기 mapper query 연결(아래)
		return sqlSession.selectList("boardMapper.readAttach", bno);
	}

	@Override
	public void updateViewCount(Integer bno) throws Exception {
		// 게시물 상세보기 시 조회 수 카운팅 업데이트 mapper query연결(아래)
		sqlSession.update("boardMapper.updateViewCount", bno);
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// 게시물 등록 매퍼쿼리 연결(아래)
		sqlSession.insert("boardMapper.insertBoard", boardVO);
		
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
		// 게시물 삭제 매퍼쿼리 연결(아래)
		sqlSession.delete("boardMapper.deleteBoard", bno);
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// 게시물 수정 매퍼쿼리 연결(아래)
		//위쪽의 메서드인 updateBoard메서드의 매게변수 해석(아래) Board클래스는 개발자가 생성한 참조형 데이터타입
		//jsp에서 update_form태그에서 전송된 값 boardVO클래스에 담겨서 데이터를 받습니다.
		//함수는 오브젝트 생성해야지만 사용이 가능합니다. 자바에선 new, 스프링에서 Inject
		//BoardVO 개발자 선언한 클래스. 데이터 클래스,, 오브젝트 클래스-C언어 구저체
		//String도 클래스
		sqlSession.update("boardMapper.updateBoard", boardVO);
		
	}
}
