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
			<tr>
				<th>날짜</th>
				<th>월급</th>
				<th>기타수입</th>
			</tr>
			<tr>
				<td><input type="date" name="income_date"></td>
				<td><input type="number" name="salary"></td>
				<td><input type="number" name="income">
				<input type="hidden" name="id" value="${login.id }"></td>
			</tr>
		</table>
		<br>
		<a href="#this" id="income_save" class="btn btn-default">저장</a>
		<a href="#this" id="income_list" class="btn btn-default">수입목록</a>
	</form>
	</div>
	
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#income_save").on("click",function(e){
			e.preventDefault();
			fn_income_save();
		});
		$("#income_list").on("click",function(e){
			e.preventDefault();
			fn_income_list();
		});
	});
	
	function fn_income_save(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("/account/account_income_save");
		comSubmit.submit();
	}
	function fn_income_list(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("/account/account_income_list");
		comSubmit.submit();
	}
</script>
</body>
</html>