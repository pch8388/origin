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
			<thead>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(list)>0 }">
						<c:forEach items="${list }" var="row">
							<tr>
								<td>${row.account_date }</td>
								<td>${row.use_detail }</td>
								<td>${row.cash }</td>
								<td>${row.card }</td>
								<td>${row.classification }</td>
								<td>${row.memo }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="6">조회된 결과가 없습니다.</td></tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	
	
		<br/>
	</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	
</script>
</body>
</html>