<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>


<!-- Begin Page Content -->
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
				<%@ include file="./includes/postCommon.jsp"%>
				
				<form id="frmOper" action="/post/modifyPost" method="get">
					<input type="hidden" id="aaa" name="boardId" value="${boardId}">
					<input type="hidden" id="bbb" name="postId" value="${post.id}">
				</form>
				
				<button type="submit" data-oper='modify' class="btn btn-primary">수정</button>
				<button type="reset" data-oper='list' class="btn btn-secondary">목록보기</button>

		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@ include file="../includes/footer.jsp"%>
<script>
$(document).ready(function(){
	
	$("button[data-oper='modify']").on("click", function(){
		$('#frmOper').submit();
	})
	$("button[data-oper='list']").on("click", function(){
		$('#frmOper').find("#bbb").remove
		$('#frmOper').attr("action", "/post/list").submit();
	})

});
</script>