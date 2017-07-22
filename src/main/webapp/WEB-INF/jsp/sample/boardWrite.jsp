<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
	<form id="frm" name="frm" enctype="multipart/form-data">
	<div class="container">
		<h2>게시글 작성</h2>
		<div class="row">
			<div class="col-xs-8">
				<div class="form-group">
					<label for="TITLE">제목</label>
					<input type="text" id="TITLE" name="TITLE" class="form-control"/></div>
			</div>
			<div class="col-xs-4">
				<div class="form-group">
					<label for="id">작성자</label>
					<input type="text" id="id" name="id" class="form-control" value="${login.id }" readonly />
				</div>
			</div>
		</div>
		<div class="form-group">
			<textarea rows="20" class="form-control" title="내용" id="CONTENTS" name="CONTENTS"></textarea></div>
			
		<div id="fileDiv">
			<p>
				<input type="file" id="file" name="file_0" />
				<a href="#this" class="btn btn-default" id="delete" name="delete">삭제</a>
			</p>
		</div>
		<br/><br/>
		
		<a href="#this" class="btn btn-default" id="addFile">파일 추가</a>
		<a href="#this" class="btn btn-default pull-right" id="write">작성하기</a>
		<a href="#this" class="btn btn-default pull-right" id="list">목록으로</a>
	</div>
	</form>
	
	<%@ include file = "/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
		var gfv_count = 1;
	
		$(document).ready(function(){
			$("#list").on("click",function(e){  // 목록으로 버튼
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#write").on("click",function(e){ // 작성하기 버튼
				e.preventDefault();
				fn_insertBoard();
			});
			
			$("#file").bind("change",function(e){
				e.preventDefault();
				var maxSize = 5 * 1024 * 1024;
				var fileSize = this.files[0].size;
				var browser = navigator.appName;
				
				if(fileSize > maxSize){
					alert("첨부파일 사이즈는 5MB 이내만 등록이 가능합니다.");
					$("#file").val("");
					return;
				}
			});
			
			$("#addFile").on("click",function(e){ //파일 추가 버튼
				e.preventDefault();
				fn_addFile();
			});
			
			$("a[name='delete']").on("click",function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		});
		
		function fn_openBoardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardList' />");
			comSubmit.submit();
		}
		
		function fn_insertBoard(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/sample/insertBoard' />");
			comSubmit.submit();
		}
		
		function fn_addFile(){
			var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click",function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}
		
		function fn_deleteFile(obj){
			obj.parent().remove();
		}
		
		
	</script>
</body>
</html>