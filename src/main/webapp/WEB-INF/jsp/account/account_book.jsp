<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div id="wrapper" class="active">  
    <!-- Sidebar -->
            <!-- Sidebar -->
    <div id="sidebar-wrapper">
       <ul class="sidebar-nav" id="sidebar">

       </ul>
    </div>

<!--Div that will hold the pie chart-->
<div class="container">
<div id="chart_div"></div></div>
	<div class="container">
	<form id="frm" name="frm">
		<table class="table table-bordered">
			<tr>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th></tr>
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
		</table>
		<a href="#this" id="save" class="btn btn-default pull-right">저장하기</a>
	</form>
	</div>
	
		<div class="container">
			<select id="year" name="year"></select>           <!-- 연도별조회 -->
			<select id="month" name="month"></select>         <!-- 월별조회 -->
			<a href="#this" id="lookup" class="btn btn-default">조회</a>
		<br>
			
		<table class="table table-bordered">
			<thead>
			<tr>
				<th><input type=checkbox id="chkAll"></th>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th></tr>
			</thead>
			<tbody id="spend">
			
			</tbody>
		</table>
		<a href="#this" id="chkDel" class="btn btn-default pull-right">선택삭제</a>
		<div id="PAGE_NAVI" class="text-center"></div>
		<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
			
		<br/>
	</div>
</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
    
    // Load the Visualization API and the piechart package.
    google.charts.load('current', {'packages':['corechart']});
      
    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);
    
    function drawChart() {             //차트 그리는 function
      var id = "${login.id}";
      var jsonData = $.ajax({
          url: "/account/account_chart_draw",
          data: {"id":id,"date":todayYearMonth},
          dataType: "json",
          async: false
          }).responseText;
          console.log(jsonData);
      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(jsonData);

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(data, {width: 1000, height: 400});
    }
    
	var win1 = null;
	var today = new Date();
	var todayYear = today.getFullYear();
	var todayMonth = today.getMonth()+1 < 10 ? "0"+(today.getMonth()+1) : today.getMonth()+1;
	var todayYearMonth = todayYear+"-"+todayMonth+"%";                 //조회하고 싶은 년,월을 저장하는 변수  . default로 현재날짜의 년,월이 저장되어있음. 쿼리 조회를 위해 string format을 화면에서 처리후 controller로 전송
	console.log(todayYearMonth);
	$(document).ready(function(){
		fn_year();
		fn_month();
		fn_accountList(1,todayYearMonth);
		fn_monthIncome(todayYearMonth);
		$("#save").on("click",function(e){
			e.preventDefault();
			fn_saveAccount();
		});
		$("a[name='account_income']").on("click",function(e){
			e.preventDefault();
			fn_accountIncome();
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
		$("#lookup").on("click",function(e){
			e.preventDefault();
			fn_lookup();
		});
	});
	
	function fn_month(){              //select box 월 부분 처리
		var d = new Date();
	    var mon = d.getMonth()+1;
		for(i=1; i<13; i++){
			$("#month").append($('<option />').val(i).html(i+"월"));
		}
		$("[value='"+mon+"']").attr("selected","true");
	}
	function fn_year(){              //select box 연도 부분 처리
		for (i = new Date().getFullYear(); i > 2015; i--){
		    $("#year").append($('<option />').val(i).html(i+"년"));
		}
	}
	
	function fn_lookup(){            //조회버튼 기능
		var yearDate = $("#year").val();
		var monthDate = $("#month").val() < 10 ? "0"+$("#month").val() : $("#month").val();
		todayYearMonth = yearDate+"-"+monthDate+"%";
		console.log(todayYearMonth);
		fn_accountList(1,todayYearMonth);
		google.charts.setOnLoadCallback(drawChart);
		fn_monthIncome(todayYearMonth);
	}
	
	function fn_chkDelete(){             //선택삭제 버튼 기능
		var checkBoxValues = [];
		$("input[name='chk']:checked").each(function(){
			checkBoxValues.push($(this).val());
		});
		console.log(checkBoxValues);
		
		$.ajax({
			url:"/account/account_delete",
			data: {"checkBoxValues":checkBoxValues},
			type: "POST",
			success: function(data){
				fn_accountList(1,todayYearMonth);
			}
		});
	}
	
	function fn_saveAccount(){             //저장버튼 기능
		var formData = $("#frm").serialize();
		$.ajax({
			url: "/account/account_save",
			type: "POST",
			data: formData,
			success: function(data){
				fn_accountList(1,todayYearMonth);
				$("#frm")[0].reset();
			}
		});
	}
	function fn_accountIncome(){           //수입입력 버튼
		win1 = window.open("/account/account_income","income","width=800 height=500");
		checkChild();
	}
	
	function checkChild(){            //수입입력창 종료되면 account_book 리로드
		if(win1.closed){
			window.location.reload(true);
		}else{
			setTimeout("checkChild()",1);
		}
	}
	
	function fn_monthIncome(date){            //이달의 수입 nav-bar ajax처리
		$.ajax({
			url: "/account/monthIncome",
			type: "POST",
			data: {"date":date},
			success: function(data){
				fn_monthIncomeCallback(data);
			}
		});	
	}
	
	function fn_monthIncomeCallback(data){
		var dtoSum = data.dtoSum;
		var body =$("#sidebar");
		body.empty();
		var str = "<li><a href='#this' name='account_income' class='btn btn-primary'>수입입력</a></li>" +
					"<li><a>이달의 수입 <br>&nbsp;&nbsp;&nbsp;" + data.monthIncome + "원</a><br></li>" +
					"<li><a>지출<br>" +
					"&nbsp;&nbsp;&nbsp;" + dtoSum.money + "원<br>" +
					"&nbsp;&nbsp;&nbsp;현금지출<br>" +
					"&nbsp;&nbsp;&nbsp;" + dtoSum.cash + "원<br>" +
					"&nbsp;&nbsp;&nbsp;카드지출<br>" +
					"&nbsp;&nbsp;&nbsp;" + dtoSum.card + "원</a></li>" +
					"<li><a>수입-지출<br>" +
					"&nbsp;&nbsp;&nbsp;" + data.sub + "원</a></li>";
					
       body.append(str);
	}
	
	function fn_accountList(pageNo,date){        //지출 목록 
		$.ajax({
			url: "/account/account_list",
			type: "POST",
			data: {"PAGE_INDEX":pageNo,
				"PAGE_ROW":20,
				"date":date},
			success: function(data){
				fn_accountListCallback(data);	
			}
		});
	}
	
	function fn_accountListCallback(data){
		var total = data.TOTAL;
		var body = $("#spend");
		body.empty();
		if(total == 0){
			var str = "<tr>"+
							"<td colspan='7'>조회된 결과가 없습니다.</td>" +
						"</tr>";
			body.append(str);
		}else{
			var params = {
					divId : "PAGE_NAVI",
					pageIndex : "PAGE_INDEX",
					totalCount : total,
					eventName : "fn_accountList"
			};
			gfn_renderPaging(params);
			
			var str = "";
			$.each(data.list,function(key,value){
				str += "<tr>" +
							"<td><input type='checkbox' name='chk' value='"+value.idx+"' />" + "</td>" +
							"<td>" + value.account_date + "</td>" +
							"<td>" + value.use_detail + "</td>" +
							"<td>" + value.cash + "</td>" +
							"<td>" + value.card + "</td>" +
							"<td>" + value.classification + "</td>" +
						"</tr>";
			});
			body.append(str);
		}
	}
</script>
</body>
</html>