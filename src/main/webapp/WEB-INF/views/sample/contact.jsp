<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp" %>
<section class="contact-section"><!-- division디비전이라는 div 영역표시 태그 대신에 사용 -->
<h1>CONTACT</h1>
<div class="container">
<!-- 폼태그의 필수 속성, name, action-데이터를 전송할 대상값, method-데이터를 전송하는 방법값 -->
<!-- input 한줄입력 태그, textarea여러 줄 입력태그 -->
<!-- label은 이름표, 입력태그와 라벨은 1:1매칭이 되는 관계 -->
<form name="message_form" action="/contact" method="post">
<!-- spring에서는 action으로 폼데이터를 전송할 위치를 지정할 때, contact.html직접 접근x /contact처럼 
컨트롤러를 통해O -->
<!-- 필드셋은 폼이 DB테이블의 필드와 1:1매칭되는 관계 -->
<!-- 플레이스홀더: 공간에 자리를 차지하지 않으면서 입력힌트를 보여주는 역할 -->
<fieldset>
<legend>현재폼의 타이틀영역입니다.</legend>
<div class="input-class">
<label for="name">이름</label>
<input name="name" id="name" type="text" placeholder="이름을 입력해주세요.">
<label for="phone">연락처</label>
<input name="phone" id="phone" type="text" placeholder="연락처를 입력해주세요.">
<label for="email">이메일</label>
<input name="email" id="email" type="email" placeholder="email을 입력해주세요.">
<!-- 입력제약조건 html5에서 지원하는 기능 type에 꼭 email라고 형식을지정해야지만 제약조건 작동이 됨. -->
</div>
<div class="textarea-class">
<label for="message">메세지</label>
<textarea name="message" id="message" placeholder="내용을 입력해주세요."></textarea>
</div>
</fieldset>
<div class="submit-btn">
<button type="submit">메세지보내기</button>
</div>
</form>
<!-- form 태그의 목적은 input,textarea,checkbox,radio,select데이터를
submit(전송)하는 것이 목적. -->
<!-- 폼전송은 get(비보안용), post(보안용-입력/수정/삭제) -->
<!-- get방식은 유일하게 검색할 때 사용됩니다. -->
</div>
</section>
<%@ include file="./include/footer.jsp" %>