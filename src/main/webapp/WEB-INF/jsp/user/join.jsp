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
		<div class="row">
			<div class="col-xs-8">
				<div class="form-gruop">		
					<label for="id">ID:</label>
					<input type="text" class="form-control" id="id" name="id" />
				</div>
			<div class="col-xs-4">
				<a href="#this" class="btn btn-default" id="idCheck" name="idCheck">중복체크</a>
			</div>
			</div>
		</div>
		<div class="form-group">
			<label for="pw">Password:</label>
			<input type="password" class="form-control" id="pw" name="pw" />
		</div>
		<div class="form-group">
			<label for="name">Name:</label>
			<input type="text" class="form-control" id="name" name="name" />			
		</div>
		<a href="#this" class="btn btn-primary btn-block" id="join" name="join">회원가입</a>
		<a href="#this" class="btn btn-default btn-block" id="reset" name="reset">취소</a>
	</form>
</div>
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#idCheck").on("click",function(e){   //id 중복 체크
			e.preventDefault();
			fn_idChecker();
		});
		$("#join").on("click",function(e){	   //회원가입
			e.preventDefault();
			fn_join();
		});
		$("#reset").on("click",function(e){	    //취소
			e.preventDefault();
			fn_reset();
		});
	});
	
	function fn_idChecker(){
		var id = $("#id").val();
		$.ajax({
			type : "GET",
			url : "/user/idCheck",
			data : {"id":id},
			success : function(data){
				if(data.id){
					alert("사용할 수 없는 아이디입니다.");
					document.frm.reset();
				}else{
					alert("사용 가능한 아이디입니다.");
				}
			}
		});
	}
	
	function fn_join(){
		alert("회원가입 완료");
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("<c:url value='/user/joinPost' />");
		comSubmit.submit();
	}
	
	function fn_reset(){
		alert("취소되었습니다.");
		document.frm.reset();
	}
</script>
</body>
</html>