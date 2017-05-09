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
				
			</tbody>
		</table>
	
	
		<br/>
	</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		fn_accountList();
	});
	
	function fn_accountList(){
		var body = $("table>tbody");
		var str = "";
		$.ajax({
			url:"/account/account_list",
			async:false,
			success: $.each(data.list,function(key,value){
				str += "<tr>" +
							"<td>" + value.account_date + "</td>" +
							"<td>" + value.use_detail + "</td>" +
							"<td>" + value.cash + "</td>" +
							"<td>" + value.card + "</td>" +
							"<td>" + value.classification + "</td>" +
							
						"</tr>";
			});
			console.log(str);
			body.append(str);
		});
	}
</script>
</body>
</html>