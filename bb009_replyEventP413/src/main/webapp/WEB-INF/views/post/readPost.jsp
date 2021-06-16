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
				<input type="hidden" name="boardId" value="${boardId}">
				 <input type="hidden" id="postId" name="postId" value="${post.id}">
				<input type="hidden" name="pageNumber" value="${pagenation.pageNumber}">
				<input type="hidden" name="amount" value="${pagenation.amount}">
				 <input type="hidden" name="searching" value="${pagenation.searching}" />

			</form>

		</div>
		
		<div class="card-footer">
			<div calss="card-header">
			댓글
			<button id="btnOpenReplyModalForNew" class="btn-primary btn=xs pull-right">댓글달기</button>
			</div>
			<div class="card-body">
				<ul id="ulReply">

				</ul>
			</div>
	</div>
</div>
<!-- /.container-fluid -->



<!-- 댓글 입력 모달창 -->
<div id="modalReply" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        		<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>	<!-- End of modal-header -->
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> 
					<input class="form-control" name='replyContent' value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> 
					<input class="form-control" name='replyDate' value=''>
				</div>
			</div>	<!-- End of modal-body -->
			<div class="modal-footer">

				<button id='btnModifyReply' type="button" class="btn btn-warning">Modify</button>
				<button id='btnRemoveReply' type="button" class="btn btn-danger">Remove</button>
				<button id='btnRegisterReply' type="button" class="btn btn-primary">Register</button>
				<button id='btnCloseModal' type="button" class="btn btn-default">Close</button>
			</div>	<!-- End of modal-footer -->
		</div>	<!-- End of modal-content -->
	</div>	<!-- End of modal-dialog -->
</div>
<!-- End of 댓글 입력 모달창 -->



</div>
<!-- End of Main Content -->
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript" src="\resources\js\post\reply.js"> </script>
<script type="text/javascript" src="\resources\js\util\dateFormat.js"> </script>
	
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


		var ulReply = $("#ulReply");
		var originalId = "${post.id}";
		showReplyList(1);
		
		function showReplyList(pageNum) {
			replyService.getReplyList(
					{orgId:originalId, page:pageNum || 1},
					function(listReply) {
						if(listReply == null || listReply.length == 0 ){
							//정보가 없을 시 UL에 담긴 내용 청소.	
							ulReply.html("");
							return;
						}
						// 댓글정보가 있으면 li로 만들어서 ul에 담을 것.
						strLiTags = "";
								
						for (var i = 0, len = listReply.length || 0; i < len; i++){
							strLiTags += '<li class="clearfix" data-reply_id = "' + listReply[i].id + '">';
							strLiTags += '	<div>';
							strLiTags += '		<div>';
							strLiTags += '			<strong>' + listReply[i].writer.name + '</strong>';
							strLiTags += '			<small>'+ dateFormatService.getWhenPosted(listReply[i].updateDate) + '</small>';
							strLiTags += '		</div>';
							strLiTags += '		<p>' + listReply[i].content + '</p>';
							strLiTags += '	</div>';
							strLiTags += '</li>';

						}
						ulReply.html(strLiTags);
					}, 
					function(errMsg) {
						alert("댓글 등록 오류  : " + errMsg);
					}
			);
			
		}
		//목록조회
		replyService.getReply(
			"0000V0001A",
			function(replyObj) {
				alert("댓글 상세 : " + replyObj);
			}, 
			function(errMsg) {
				alert("댓글 조회 오류  : " + errMsg);
			}
		);
		

		//생성
		//replyService 모듈
		var modalReply = $("#modalReply");
		var inputReplyContent = modalReply.find("input[name='replyContent']");
		var inputReplyDate = modalReply.find("input[name='replyDate']");
		
		var btnModifyReply = $("#btnModifyReply");
		var btnRemoveReply = $("#btnRemoveReply");
		var btnRegisterReply = $("#btnRegisterReply");
	
		
		$("#btnOpenReplyModalForNew").on("click", function(e) {
		// 모달에 들어 있는 모든 내용 청소	
			modalReply.find("input").val("");
		// 신규 댓글 달기 시에는 등록일자는 Daefault 처리. 따라서 보여줄 필요가 없어요.
			inputReplyDate.closest("div").hide();
		
		
			btnModifyReply.hide();
			btnRemoveReply.hide();
			btnRegisterReply.show();
			
			modalReply.modal("show");
		});
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

		//수정
		replyService.updateReply(
			{
				id:"0000V0001A",
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