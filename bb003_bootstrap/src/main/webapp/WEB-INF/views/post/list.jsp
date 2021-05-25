<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>
<!-- End of Topbar -->

<!-- Begin Page Content -->
<div class="container-fluid">



	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">${boardName} 글 목록</h6>
		</div>
		<div class="card-body">
		<a href="/post/registerPost?boardId=${boardId}">글쓰기</a>
		<br>
		<a href="/">메인으로</a>
			
			
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead>
						<tr>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>수정일</th>
						</tr>

					</thead>
					<tbody>
					<c:forEach items="${listPost}" var="post">
						<tr>
							<td><a
								href="/post/readPost?boardId=${boardId}&postId=${post.id}">${post.title}</a></td>
							<td>${post.writer.name}</td>
							<td>${post.readCnt}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${post.updateDate}"/></td>
							
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료 되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<script type="text/javascript">
$(document).ready(function(){
	var result = '<c:out value="${result}" />';
	checkModal(result);
	
	function checkModal(result){
		if(result === ''){
			return;
		}
		if(result != "삭제 성공"){
			$(".modal-body").html("게시글" + result + "번이 등록되었습니다");
		}
		if(result === "삭제 성공"){
			$(".modal-body").html("게시글을 성공적으로 삭제하였습니다");
		}
		$("#myModal").modal("show");
	}
});
</script>

<%@ include file="../includes/footer.jsp"%>