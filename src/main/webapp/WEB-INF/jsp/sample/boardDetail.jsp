<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<div class="container">
<h2>게시글 상세</h2>
	<table class="table">
		<tbody>
			<tr>
				<th class="active">글 번호</th>
				<td>${map.IDX }</td>
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
				<td colspan="3">${map.TITLE }</td>
			</tr>
			<tr>
				<td colspan="4">${map.CONTENTS }</td>
			</tr>
			<tr>
				<th class="active">첨부파일</th>
				<td colspan="3">
					<c:choose>
						<c:when test="${fn:length(list) > 0 }">
							<c:forEach var="row" items="#{list }">
								<input type="hidden" id="IDX" value="${row.IDX }" />
								<a href="#this" name="file">${row.ORIGINAL_FILE_NAME }</a>
								(${row.FILE_SIZE }kb)
							</c:forEach>
						</c:when>
						<c:otherwise>
							첨부파일이 없습니다.
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	
	<a href="#this" class="btn btn-default pull-right" id="list">목록으로</a>
	<a href="#this" class="btn btn-default pull-right" id="reply">답글달기</a>
	<c:if test="${login.id == map.CREA_ID }">
		<a href="#this" class="btn btn-default" id="update">수정하기</a>
	</c:if>
</div>
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#list").on("click",function(e){ //목록으로 버튼
				e.preventDefault();
				fn_openBoardList();
			});
			
			$("#reply").on("click",function(e){ //답글달기 버튼
				e.preventDefault();
				fn_boardReply();
			});
		
			$("#update").on("click",function(e){ //수정하기 버튼
				e.preventDefault();
				fn_openBoardUpdate();
			});
			
			$("a[name='file']").on("click",function(e){ //파일 이름
				e.preventDefault();
				fn_downloadFile($(this));
			});
		});
		
		function fn_openBoardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardList' />");
			comSubmit.submit();
		}
		
		function fn_boardReply(){
			var idx = "${map.IDX}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/boardReply' />");
			comSubmit.addParam("IDX",idx);
			comSubmit.submit();
		}
		
		function fn_openBoardUpdate(){
			var idx = "${map.IDX}";
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/sample/openBoardUpdate' />");
			comSubmit.addParam("IDX",idx);
			comSubmit.submit();
		}
		
		function fn_downloadFile(obj){
			var idx = obj.parent().find("#IDX").val();
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/common/downloadFile' />");
			comSubmit.addParam("IDX",idx);
			comSubmit.submit();
		}
	</script>
</body>
</html>