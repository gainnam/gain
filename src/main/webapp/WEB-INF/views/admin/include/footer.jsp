<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 관리자단 푸터 시작 footer.jsp -->
  <footer class="main-footer">
    <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.1.0-rc
    </div>
  </footer>
  

  <!-- 로그아웃 영역 Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="/resources/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="/resources/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="/resources/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="/resources/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="/resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="/resources/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="/resources/plugins/moment/moment.min.js"></script>
<script src="/resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="/resources/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/resources/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="/resources/dist/js/pages/dashboard.js"></script>
<!-- 왼쪽메뉴 선택된 부분 active클래스 주는 j쿼리 명령어 추가(아래_) -->
<script>$(document).ready(function(){
	//현재 선택된URL 값을 가져오는 명령(아래)
	var current = location.pathname;
	var current_2 = current.split("/")[2];//split함수로 current에 있는 문자를 분리
	
	//alert(current);//admin(관리자홈),admin/member_list(관리자관리),
	//admin/board_list(게시물관리)
	//active클래스명을 동적으로 추가할 영역은 nav-item 안쪽의 a태그의 class명을 추가하면 됨.
	$(".nav-treeview li a").each(function(){
		
		if( $(this).attr('href').indexOf(current_2) != -1 ) {//위 문제를 처리하는 대체 함수 indexOf함수
			$ (this).addClass("active");//선택한 메뉴의 배경색상을 흰색으로 보이게 처리   
		} else {
			$(this).removeClass("active");//선택하지 않은 메뉴의 배경색을 원상복귀
		}
	});
	});
//each함수로 a 태그 2개를 찾는 명령.
//$(this)현재 함수의 구현 대상 본인을 가리니다.
//2개의 값을 비교: a태그의 값/admin/member_list, /admin/board_list
//2개의 값을 비교: 비교대상 current_2(현재 웹브라우저의 url값 중 제일 마지막 값)
//if( $(this).attr('href').includes(current_2) == true ){ //includes 포함하고 있는 문자열을 비교.
//위에 사용한 includes란 함수는 크롬에서나 작동이 됨. IE에서는 작동이x. 크로스 브라우징 처리를 해줘야 함.
 </script>
</body>
</html>
<!-- 관리자단 푸터 끝 -->