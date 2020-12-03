<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<!-- 대시보드 본문 Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- 본문헤더 Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0">회원상세보기</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">회원상세보기</li>
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
							<h3 class="card-title">READ Member</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<strong><i class="fas fa-book mr-1"></i>user_id</strong>

							<p class="text-muted">admin</p>

							<hr>

							<strong><i class="fas fa-map-marker-alt mr-1"></i>
								user_name</strong>

							<p class="text-muted">관리자</p>
							<!-- 부트스트랩 mr-1오른쪽 여백주기 클래스명(margin-right:.25rem !important;-->
							<hr>

							<strong><i class="fas fa-pencil-alt mr-1"></i>email</strong>

							<p class="text-muted">admin@abc.com
								                          
							</p>

							<hr>

							<strong><i class="far fa-file-alt mr-1"></i>point</strong>

							<p class="text-muted">100</p>
							
							<hr>

							<strong><i class="far fa-plus-square mr-1"></i>enabled</strong>

							<p class="text-muted">true
								                          
							</p>
							
							<hr>

							<strong><i class="fab fa-adn mr-1"></i>levels</strong>

							<p class="text-muted">ROLE_ADMIN
								                          
							</p>
							
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
					
					<!-- 버튼영역 시작 -->
					<div class="card-body">
						<a href="/admin/member/member_list" class="btn btn-primary float-right">LIST ALL</a>
						<button type="button" class="btn btn-danger float-right mr-1">DELETE</button>
						<button type="button" class="btn btn-warning float-right mr-1 text-white">UPDATE</button>
					</div>
					<!-- 버튼영역 끝 -->
					
				</div>
				
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>