<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

	<!-- 대시보드 본문 Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- 본문헤더 Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">게시글 상세보기</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">게시글 상세보기</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- 본문내용 Main content -->
			<section class="content">
				<div class="container-fluid">

					<div class="row">
						<!-- 부트스트랩의 디자인 클래스 row -->
						<div class="col-12">

							<!-- 그리드시스템중 12가로칼럼 width:100% -->

							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">READ Board</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<strong><i class="fas fa-book mr-1"></i>title</strong>

									<p class="text-muted">
									<!-- 아래와 같은 출력형태는 EL(Express Language)표시라고 합니다. -->
									<%-- ${boardVO.title} --%>
									<!-- 위 자바의 EL출력은 보안에 취약하기 때문에 아래처럼 처리함. -->
									<c:out value="${boardVO.title}"></c:out>
									</p>

									<hr>

									<strong><i class="fas fa-map-marker-alt mr-1"></i>
										content</strong>

									<p class="text-muted">
									${boardVO.content}
									</p>
									<!-- 부트스트랩 mr-1오른쪽 여백주기 클래스명(margin-right:.25rem !important;-->
									<hr>

									<strong><i class="far fa-user mr-1"></i>작성자</strong>

									<p class="text-muted">
									<c:out value="${boardVO.writer}"></c:out>
									</p>

									<hr>

									<strong><i class="far fa-save mr-1"></i>첨부파일</strong>

									<p class="text-muted">
										<a href="#">파일다운로드</a>

									</p>

								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->

							<!-- 버튼영역 시작 -->
							<div class="card-body">
								<a href="/admin/board/board_write" class="btn btn-primary float-right">LIST
									ALL</a> <button class="btn btn-danger float-right mr-1">DELETE</button> 
									<a href="/admin/board/board_write?bno=${boardVO.bno}" class="btn btn-warning float-right mr-1 text-white">UPDATE</a>
							</div>
							<!-- 버튼영역 끝 -->
							<!-- 댓글영역 시작 -->
							<div class="card card-primary card-outline col-12">
								<div class="card-header">
									<h5 class="card-title">Add New Reply</h5>
								</div>
								<form action="#" name="reply_form" method="post">

									<div class="card-body">
										<div class="form-group">
											<label for="replyer">Replyer</label> <input type="text"
												class="form-control" name="replyer" id="replyer"
												placeholder="작성자를 입력해 주세요." required>
											<!-- 폼에서 input같은 입력태그에는 name속성이 반드시 필요, 이유는 DB에 입력할때,
	                    	 값을 전송하게 되는데, 전송값을 담아두는 이름이 name가 되고, 위에서는 writer 입니다. -->
										</div>
										<div class="form-group">
										<label for="replytext">Reply_text</label> <input type="text"
												class="form-control" name="replytext" id="replytext"
												placeholder="내용을 입력해 주세요." required>
											<!-- 게시판에서는 폼을 전송할 때  submit타입을 사용하지만, 댓글은 Ajax로 전송하기 때문에, button타입으로 함 -->
										</div>
										<button type="button"
											class="btn btn-warning float-left mr-1 text-white" id="insertReplyBtn">댓글
											등록</button>
									</div>
								</form>
								<div class="timeline">
									<!-- 여기가 time-label의 before위치 -->
									<div class="time-label">
										<span class="bg-red">Reply List[1]&nbsp;&nbsp;</span>
									</div>
									<!-- 여기가 time-label의 after위치 -->
									<!-- <div>
										<i class="fas fa-envelope bg-blue"></i>
										<div class="timeline-item">
											<h3 class="timeline-header">작성자</h3>

											<div class="timeline-body">댓글 입력 테스트</div>
											<div class="timeline-footer">
												Button trigger modal
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#replyModal">수정</button>
											</div>
										</div>
									</div> -->

								</div>
								<!-- ./timeline -->
								<!-- 페이징처리 시작 -->
								<div class="pagination justify-content-center">
									<ul class="pagination">
										<li class="paginate_button page-item previous disabled"
											id="example2_previous"><a href="#"
											aria-controls="example2" data-dt-idx="0" tabindex="0"
											class="page-link">Previous</a></li>
										<!-- 위 이전게시물링크 -->
										<li class="paginate_button page-item active"><a href="#"
											aria-controls="example2" data-dt-idx="1" tabindex="0"
											class="page-link">1</a></li>
										<li class="paginate_button page-item "><a href="#"
											aria-controls="example2" data-dt-idx="2" tabindex="0"
											class="page-link">2</a></li>
										<li class="paginate_button page-item "><a href="#"
											aria-controls="example2" data-dt-idx="3" tabindex="0"
											class="page-link">3</a></li>
										<!-- 아래 다음게시물링크 -->
										<li class="paginate_button page-item next" id="example2_next">
											<a href="#" aria-controls="example2" data-dt-idx="7"
											tabindex="0" class="page-link">Next</a>
										</li>
									</ul>
								</div>
								<!-- 페이징처리 끝 -->
							</div>
						</div>
						<!-- 댓글영역 끝 -->

					</div>
					<!-- //col-12 -->

				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>

	<%--  자바스크립트용 #template element 제작(아래) jstl의 향상된 for문과 같은 역할
	jstl을 사용하려면, .jsp에서 <%@ taglib url=...처럼 외부 core을 가져와서 사용한 것처럼
	자바스크립트에서도 외부 core를 가져와야 합니다. --%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<!-- 댓글템플릿(빵틀)만들기(아래) -->
	<script id="template" type="text/x-handlebars-template">
{{#each .}}
<div class="template-div" data-rno="{{rno}}">
 <i class="fas fa-envelope bg-blue"></i>
 <div class="timeline-item">
   <h3 class="timeline-header">{{replyer}}</h3>
   <div class="timeline-body">{{replytext}}</div>
   <div class="timeline-footer">
	 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#replyModal">
  		수정
	 </button>
   </div>
 </div>
</div>
{{/each}}
</script>
	<!-- 화면을 재구현Representation하는 함수(아래) -->
	<script>
	var printReplyList = function(data, target, templateObject) {
		var template = Handlebars.compile(templateObject.html());//html태그로 변환
		var html = template(data);//빅데이터를 리스트템플릿에 바인딩 결합시켜주는 역할.
		$(".template-div").remove();//화면에 보이는 댓글리스트만 지우기
		target.after(html);//target은 .time-lavel 클래스영역을 가리킴.
	};
	</script>

	<!-- 댓글 등록 버튼 액션 처리(아래) -->
	<script>
		$(document).ready(
				function() {
					$("#insertReplyBtn").on("click",function() {//댓글등록버튼을 클릭했을 때, 구현내용(아래)
								//alert("debugging");
								//Ajax를 이용해서, 화면을 representation(REST-API방식) 부분 화면을 재구현(아래)
								$.ajax({//통신프로그램.데이터왔다갔다함
									//여기서부터는 프론트 엔드 개발자 영역
									type : 'post',//지금은html이라서 get방식이지만, jsp로 가면, post방식으로 바꿔야함
									url : '/reply/reply_write',//jsp로 가면, ReplyController만들어서 거기서 생성한 RequestMapping에서 지정한 url로 바꿀꺼임!
									//바꾸는 건 백엔드영역
									dataType : 'text',//ReplyController에서 받은 데이터의 형식은 text형식으로 받겠다는 걸 명시
									success : function(result) {//통신이 성공하면(상태가 200(HTTP성공응답)이 되면), 위 경로에서 반환받은 result 데이터(json텍스트)를 
										alert(result);
										//지금은 html이라서 result값을 이용할 수가 없어서 댓글 더미데이터를 만듭니다.(아래)
										result = [
										//{rno:댓글번호, bno:게시물번호,replytext:첫 번째 댓글",replyer:"admin", regdate:타임스탬프}
										{
											rno : 1,
											bno : 15,
											replytext : "첫 번째 댓글",
											replyer : "admin",
											regdate : 1600000000000
										},//첫 번째 댓글 데이터
										{
											rno : 2,
											bno : 15,
											replytext : "두 번째 댓글",
											replyer : "user02",
											regdate : 1600000000001
										} //두 번째 댓글 데이터
										];//위 URL이 공공데이터라 생각하면, 위 데이터를 화면에 구현하면, 빅데이터의 시각화로 불리게 됩니다.
										//printReplyList(빅데이터, 출력할 타겟위치, 빅데이터를 ㅠ가지고 가공한 템플릿화면);
										printReplyList(result,
												$(".time-label"),
												$("#template"));//화면에 출력하는 구현함수를 호출하면 실행.

									}
								})
							});
				});
	</script>
	<!-- 댓글리스트에서 수정 버튼을 클릭했을 때, 팝업창이 뜨는데, 그 팝업창의 내용을 동적으로 변경시켜주는 구현(아래) -->
	<script>
	$(document).ready(function() {
		$(".timeline").on("click", ".template-div", function() {
			//$(this);//클릭한 댓글에 따라서 this는 첫 번째 댓글일 수도 있고, 두 번째 댓글일 수도 있음.
			$("#rno").val($(this).attr("data-rno")); 
			$(".modal-title").html($(this).find(".timeline-header").text());
			$("#replytext").val($(this).find(".timeline-body").text());
			//alert("debugging" + $(this).find(".timeline-body").text());
			//마우스클릭으로 선택한 댓글의 .timeline-body영역의 text문자를 #replytext영역의 값으로 입력하겠다.
		});
		
	});
	</script>
	<!-- Modal -->
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog"
		aria-labelledby="replyModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">작성자</h5><!-- 작성자는 동적으로 바뀌는 값 -->
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
				<input type="hidden" name="rno" id="rno" value=""><!--$(".timelime").on("click" 이 동작이 실행됨  -->
					<input type="text" class="form-control" name="replytext"
						id="replytext" placeholder="작성자를 입력해 주세요." required>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" id="udpateReplyBtn">수정</button>
					<button type="button" class="btn btn-danger" id="deleteReplyBtn">삭제</button>
				</div>
			</div>
		</div>
	</div>

