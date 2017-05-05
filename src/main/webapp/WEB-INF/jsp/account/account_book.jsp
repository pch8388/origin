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
					<td>
					<select name="classification">
						<option value="식비">식비</option>
						<option value="주거비">주거비</option>
						<option value="통신비">통신비</option>
						<option value="생활용품">생활용품</option>
						<option value="의복/미용">의복/미용</option>
						<option value="건강/문화">건강/문화</option>
						<option value="교육/육아">교육/육아</option>
						<option value="교통/차량">교통/차량</option>
						<option value="경조사/회비">경조사/회비</option>
						<option value="세금/이자">세금/이자</option>
						<option value="용돈/기타">용돈/기타</option>
					</select></td>
					<td><input type="text" name="memo" />
					<input type="hidden" name="id" value="${login.id }" /></td> 
				</tr>
			</tbody>
		</table>
		<a href="#this" id="save" class="btn btn-default">저장하기</a>
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