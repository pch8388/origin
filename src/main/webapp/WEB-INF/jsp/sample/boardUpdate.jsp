<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<body>
<div class="container">
	<form id="frm" name="frm" enctype="multipart/form-data">
	
		<table class="table">
			<h2>게시글 상세</h2>
			<tbody>
				<tr>
					<th class="active">글 번호</th>
					<td>
						${map.IDX }
						<input type="hidden" id="IDX" name="IDX" value="${map.IDX }">
					</td>
					<th class="active">조회수</th>
					<td>${map.HIT_CNT }</td>
				</tr>
				<tr>
					<th class="active">작성자</th>
					<td>${map.CREA_ID }</td>
					<th class="active">작성시간</th>
					<td>${map.CREA_DTM }</td>
				</tr>
				<tr>
					<th class="active">제목</th>					
					<td colspan="3">
					<div class="form-group">
						<input type="text" id="TITLE" name="TITLE" class="form-control" value="${map.TITLE }">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="view_text">
					<div class="form-group">
						<textarea rows="20" class="form-control" title="내용" id="CONTENTS" name="CONTENTS">${map.CONTENTS }</textarea>
					</div>
					</td>
				</tr>
				<tr>
					<th class="active">첨부파일</th>
					<td colspan="3">
						<div id="fileDiv">
							<c:forEach var="row" items="${list }" varStatus="var">
								<p>
									<input type="hidden" id="IDX" name="IDX_${var.index }" value="${row.IDX }" />
									<a href="#this" id="name_${var.index }" name="name_${var.index }">${row.ORIGINAL_FILE_NAME }</a>
									<input type="file" id="file_${var.index }" name="file_${var.index }"/>
									(${row.FILE_SIZE }kb)
									<a href="#this" class="btn btn-default" id="delete_${var.index }" name="delete_${var.index }">삭제</a>
								</p>
							</c:forEach>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	
	</form>
	
	<a href="#this" class="btn btn-default pull-right" id="addFile">파일 추가</a>
	<a href="#this" class="btn btn-default pull-right" id="list">목록으로</a>
	<a href="#this" class="btn btn-default" id="update">저장하기</a>
	<a href="#this" class="btn btn-default" id="delete">삭제하기</a>
	</div>
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
		var gfv_count = '${fn:length(list)+1}';
		$(document).ready(function(){
			$("#list").on("click",function(e){ //목록으로 버튼
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#update").on("click",function(e){ //저장하기 버튼
				e.preventDefault();
				fn_updateBoard();
			});
			
			$("#delete").on("click",function(e){ //삭제하기 버튼
				e.preventDefault();
				fn_deleteBoard();
			});
			
			$("#addFile").on("click",function(e){ //파일 추가 버튼
				e.preventDefault();
				fn_addFile();
			});
			
			$("a[name^='delete']").on("click",function(e){ //삭제버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		});
		
		function fn_openBoardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
			comSubmit.submit();
		}
		
		function fn_updateBoard(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/sample/updateBoard.do' />");
			comSubmit.submit();
		}
		
		function fn_deleteBoard(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/deleteBoard.do' />");
			comSubmit.addParam("IDX",$("#IDX").val());
			comSubmit.submit();
		}
		
		function fn_addFile(){
			var str = "<p>" +
					"<input type='file' id='file_"+(gfv_count)+"' name='file_"+(gfv_count)+"'>"+
					"<a href='#this' class='btn btn_default' id='delete_"+(gfv_count)+"' name='delete_"+(gfv_count)+"'>삭제</a>" +
					"</p>";
			$("#fileDiv").append(str);
			$("#delete_"+(gfv_count++)).on("click",function(e){ //삭제 버튼
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