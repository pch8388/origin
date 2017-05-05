<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
	<div class="container">
	<form id="frm" name="frm">
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
				<tr>
					<td><input type="date" name="account_date" /></td>
					<td><input type="text" name="use_detail" /></td>
					<td><input type="number" name="cash" /></td>
					<td><input type="number" name="card" /></td>
					<td><input type="text" name="classification" /></td>   <!-- dropdown 검색하고 구현 -->
					<td><input type="text" name="memo" /></td> 
				</tr>
			</tbody>
			<tfoot>
				<a href="#this" id="save" class="btn btn-default">저장하기</a>
			</tfoot>
		</table>
	</form>
	</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#save").on("click",function(e){
			e.preventDefault();
			fn_saveAccount();
		});
	});
	
	function fn_saveAccount(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("/account/account_save");
		comSubmit.submit();
	}
</script>
</body>
</html>