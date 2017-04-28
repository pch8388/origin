<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
	<form id="frm" name="frm">
	<div class="container">
		<div class="form-group has-feedback">
			<input type="text" name="id" class="form-control" placeholder="USER_ID" />
			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input type="password" name="pw" class="form-control" placeholder="PASSWORD" />
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		<div class="row">
			<div class="checkbox icheck">
				<label>
					<input type="checkbox" name="useCookie"> Remember Me
				</label>
			</div>
		</div>
		<div class="col-xs-4">
			<a href="#this" class="btn btn-primary btn-block btn-flat" id="login">Sign In</a>
		</div>
	</div>
	</form>
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#login").on("click",function(e){
				e.preventDefault();
				fn_loginForm();
			});
		});
		
		function fn_loginForm(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/user/loginPost' />");
			comSubmit.submit();
		}
	</script>
</body>
</html>