<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
    
    // Load the Visualization API and the piechart package.
    google.charts.load('current', {'packages':['corechart']});
      
    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);
      
    function drawChart() {
      var id = "${login.id}";
      var jsonData = $.ajax({
          url: "<c:url value='/account/account_chart_draw' />",
          data: {"id":id},
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
	</script>
</head>
<body>
<div id="wrapper" class="active">  
    <!-- Sidebar -->
            <!-- Sidebar -->
    <div id="sidebar-wrapper">
       <ul class="sidebar-nav" id="sidebar">
          <li><a href="#this" name="account_income">수입입력</a></li>
          <li><a>이달의 수입  <br>&nbsp;&nbsp;&nbsp; ${monthIncome}원</a><br></li>
          <li><a>지출</a> <br>
        &nbsp;&nbsp;&nbsp;현금지출<br>
       &nbsp;&nbsp;&nbsp;카드지출</li>
          <li><a>수입-지출</a></li>
       </ul>
    </div>

<!--Div that will hold the pie chart-->
<div class="container">
<div id="chart_div"></div></div>
	<div class="container">
	<form id="frm" name="frm">
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th></tr>
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
	
		<div class="container">
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>날짜</th>
				<th>사용내역</th>
				<th>현금</th>
				<th>카드</th>
				<th>분류</th>
				<th>태그</th></tr>
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
</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#save").on("click",function(e){
			e.preventDefault();
			fn_saveAccount();
		});
		$("a[name='account_income']").on("click",function(e){
			e.preventDefault();
			fn_accountIncome();
		});
	});
	
	function fn_saveAccount(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("/account/account_save");
		comSubmit.submit();
	}
	function fn_accountIncome(){
		window.open("/account/account_income","income","width=800 height=500");
	}
</script>
</body>
</html>