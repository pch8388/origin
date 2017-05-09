<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<tr>
				<th>날짜</th>
				<th>월급</th>
				<th>기타수입</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(list)>0 }">
					<c:forEach items="${list }" var="row">
						<tr>
							<td>${row.income_date }</td>
							<td>${row.salary }</td>
							<td>${row.income }
							<input type="hidden" id="idx" name="idx" value="${row.idx }" ></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">저장된 데이터가 없습니다.</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		<br>
		<a href="#this" id="income" class="btn btn-default">저장페이지로</a>
	</div>
	
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#income").on("click",function(e){
			e.preventDefault();
			fn_account_income();
		});
	});
	
	function fn_account_income(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("/account/account_income");
		comSubmit.submit();
	}
</script>
</body>
</html>