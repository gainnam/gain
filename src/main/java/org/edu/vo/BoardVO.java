package org.edu.vo;

import java.util.Date;

/**
 * 게시판에서 사용되는 데이터 입출력 클래스
 * @author gainnam
 *
 */
public class BoardVO {
	//멤버변수 선언
	private Integer bno;//int는 입력값 null불가능해서 null값 가능한 Integer로 변경.둘 다 정수형.
	private String title;
	private String content;
	private String writer; 
	private Date regdate;
	private Integer view_count;//대문자로 시작=클래스형 변수 ctrl클릭하면 이동가능
	private Integer reply_count;
	
	
	@Override
	public String toString() {
		return "디버그용 BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", view_count=" + view_count + ", reply_count=" + reply_count + "]";
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	public Integer getReply_count() {
		return reply_count;
	}
	public void setReply_count(Integer reply_count) {
		this.reply_count = reply_count;
	}
	
	
}
