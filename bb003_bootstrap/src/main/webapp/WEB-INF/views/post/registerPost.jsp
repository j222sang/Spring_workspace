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
			<form action="/post/registerPost" method="post">
				<div class="form-group">
					<label>제목</label> <input class="form-control" name="title">
					<%
					request.setCharacterEncoding("UTF-8");
					%>
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" name="content" rows="3"></textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-icon-split">등록</button>
				<button type="reset" class="btn btn-secondary">초기화</button>
				<input type="hidden" name="boardId" value="${boardId}">
			</form>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@ include file="../includes/footer.jsp"%>