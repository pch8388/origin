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
				<th><input type="checkbox" name="checkbox" id="checkbox"/></th>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th>
			</thead>
			<tbody>
				<c:forEach var="i" begin="0" end="14">
					<tr>
						<td><input type="checkbox" name="checkbox_" /></td>
						<td><input type="date" name="account_date" /></td>
						<td><input type="text" name="use_detail" /></td>
						<td><input type="number" name="cash" /></td>
						<td><input type="number" name="card" /></td>
						<td><input type="text" name="classification" /></td>   <!-- dropdown 검색하고 구현 -->
						<td><input type="text" name="memo" /></td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
</body>
</html>