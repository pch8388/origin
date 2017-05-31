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
			<tr>
				<th><input type=checkbox id="chkAll"></th>
				<th>날짜</th>
				<th>월급</th>
				<th>기타수입</th>
			</tr>
		</thead>
		<tbody id="incomeList">
		
		</tbody>
		
		</table>
		<a href="#this" id="chkDel" class="btn btn-default pull-right">선택삭제</a>
		<div id="PAGE_NAVI" class="text-center"></div>
		<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
		<br>
		<a href="#this" id="income" class="btn btn-default">저장페이지로</a>
	</div>
	
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		fn_incomeList(1);
		
		$("#income").on("click",function(e){
			e.preventDefault();
			fn_account_income();
		});
		$("#chkAll").on("click",function(){
			if($("#chkAll").prop("checked")){
				$("input[name='chk']").prop("checked",true);
			}else{
				$("input[name='chk']").prop("checked",false);
			}
		});
		$("#chkDel").on("click",function(e){
			e.preventDefault();
			fn_chkDelete();
		});
	});
	
	function fn_chkDelete(){             //선택삭제 버튼 기능
		var checkBoxValues = [];
		$("input[name='chk']:checked").each(function(){
			checkBoxValues.push($(this).val());
		});
		console.log(checkBoxValues);
		
		$.ajax({
			url:"/account/income_delete",
			data: {"checkBoxValues":checkBoxValues},
			type: "POST",
			success: function(data){
				fn_incomeList(1);
			}
		});
	}
	
	function fn_account_income(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("/account/account_income");
		comSubmit.submit();
	}
	
	function fn_incomeList(pageNo){
		$.ajax({
			url: "/account/account_incomeList",
			type: "POST",
			data: {"PAGE_INDEX":pageNo,
				"PAGE_ROW":20},
			success: function(data){
				fn_incomeListCallback(data);
			}
		});
	}
	
	function fn_incomeListCallback(data){
		var total = data.TOTAL;
		var body = $("#incomeList");
		body.empty();
		if(total==0){
			var str = "<tr>"+
						"<td colspan='3'>조회된 결과가 없습니다.</td>" +
					"</tr>";
			body.append(str);
		}else{
			var params = {
					divId : "PAGE_NAVI",
					pageIndex : "PAGE_INDEX",
					totalCount : total,
					eventName : "fn_incomeList"
			};
			gfn_renderPaging(params);
			
			var str = "";
			$.each(data.list,function(key,value){
				str += "<tr>" +
							"<td><input type='checkbox' name='chk' value='"+value.idx+"' />" + "</td>" +
							"<td>" + value.income_date + "</td>" +
							"<td>" + value.salary + "</td>" +
							"<td>" + value.income + "</td>" +
					  "</tr>";
			});
			body.append(str);
		}
	}
</script>
</body>
</html>