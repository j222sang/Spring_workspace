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
         <form id="frmpost" action="/post/modifyPost" method="post">
			<%@ include file="./includes/postCommon.jsp"%>
            
            <button type="submit" data-oper='modify' class="btn btn-primary">수정완료</button>
            <button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
            <button type="submit" data-oper='list' class="btn btn-secondary">목록</button>
         
            <input id="boardId" type="hidden" name="boardId" value="${boardId}">
            <input type="hidden" name="postId" value="${post.id}">

         </form>

      </div>
   </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="../includes/footer.jsp"%>


<script type="text/javascript">
$(document).ready(function(){
	
	controlInput('수정');
	
	var frmpost = $("#frmpost");
	//처리 우선 순위가 있고 script가 html보다 우선순위가 앞선다
	$("button").on("click", function (eventInfo) {
		//이벤트 처리의 전파를 막아서 어떤 미리 개발되어 있는 이벤트 처리를 막는 함수
		eventInfo.preventDefault();
		//this : 이벤트가 일어난 객체
		var oper = $(this).data("oper");
		
		if (oper === "remove") {
			frmpost.attr("action", "/post/removePost");
		}else if (oper === "list") {
			//move to list
			var boardIdInput = frmpost.find("#boardId");
			frmpost.attr("action", "/post/list").attr("method", "get");
			//form에 담겨있는 모든 하위 요소를 없애라
			frmpost.empty();
			frmpost.append(boardIdInput);
		}
		frmpost.submit();
	});
});
</script>

