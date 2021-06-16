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

			<button data-oper='modify' class="btn-primary">수정</button>
			<button data-oper='list' class="btn-secondary">목록</button>

			<form id="frmOper" action="/post/modifyPost" method="get">
				<input type="hidden" name="boardId" value="${boardId}"> <input
					type="hidden" id="postId" name="postId" value="${post.id}">
				<input type="hidden" name="pageNumber"
					value="${pagenation.pageNumber}"> <input type="hidden"
					name="amount" value="${pagenation.amount}"> <input
					type="hidden" name="searching" value="${pagenation.searching}" />

			</form>

		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript" src="\resources\js\post\reply.js">
	
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var frmOper = $("#frmOper");
		$("button[data-oper='modify']").on("click", function() {
			$("#frmOper").submit();
		});

		$("button[data-oper='list']").on("click", function() {
			$("#frmOper").find("#postId").remove();
			$("#frmOper").attr("action", "/post/listBySearch").submit();
		});

		var originalId = "${post.id}";
/*
		//목록조회
		replyService.getReplyList(
				{orgId:originalId, page:1},
				function(listReply) {
					for (var i = 0, len = listReply.length || 0; i < len; i++){
						console.log(listReply[i]);
					}
				}, 
				function(errMsg) {
					alert("댓글 등록 오류  : " + errMsg);
				}
		);
*/		
		replyService.getReply(
			"000010000z",
			function(replyObj) {
				alert("댓글 상세 : " + replyObj);
			}, 
			function(errMsg) {
				alert("댓글 조회 오류  : " + errMsg);
			}
		);
		
/*
		//생성
		//replyService 모듈
		replyService.addReply(
			originalId, 			
			{
				content : "테스트용도로 넣는 댓글"
			}, 
			function(newReplyId) {
				alert("신규 댓글 id : " + newReplyId);
			}, 
			function(errMsg) {
				alert("댓글 등록 오류  : " + errMsg);
			}
		);
*/
		//수정
		replyService.updateReply(
			{
				id:"000010000z",
				content:"수정된 댓글 테스트"
			},
			function(resultMsg) {
				alert("댓글수정 성공: " + resultMsg);
			}, 
			function(errMsg) {
				alert("댓글수정 오류  : " + errMsg);
			}
		);
/*		
		//삭제
		replyService.removeReply(
				"0000V0000f",
				function(delResult) {
					alert("댓글 삭제 성공: " + delResult);
				}, 
				function(errMsg) {
					alert("댓글 삭제 오류  : " + errMsg);
				}

			);
*/	
	});

</script>