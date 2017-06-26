<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<script type="text/javascript" src="<c:url value='/js/jsbn.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/rsa.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/prng4.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/rng.js' />"></script>
</head>
<body>
	<form id="frm" name="frm">
	<input type="hidden" id="RSAModulus" value="${RSAModulus }" />
	<input type="hidden" id="RSAExponent" value="${RSAExponent }" />
	<div class="container">
		<div class="form-group has-feedback">
			<input type="text" name="id" id="id" class="form-control" placeholder="USER_ID" />
			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input type="password" name="pw" id="pw" class="form-control" placeholder="PASSWORD" />
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		
		<div class="col-xs-4">
			<a href="#this" class="btn btn-primary btn-block btn-flat" id="login">Sign In</a>
		</div>
		<div class="col-xs-4">
			<a href="#this" class="btn btn-primary btn-block btn-flat" id="join">Join</a>
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
			
			$("#join").on("click",function(e){
				e.preventDefault();
				fn_joinForm();
			});
		});
		
		function fn_loginForm(){
			var id = $("#id").val();
			var pw = $("#pw").val();
			
			if(!id || !pw){
				alert("ID/Password를 입력해주세요.");
				return false;
			}
			
			try{
				var rsaPublicKeyModulus = $("#RSAModulus").val();
				var rsaPublicKeyExponent = $("#RSAExponent").val();
				fn_submitEncrytedForm(id,pw,rsaPublicKeyModulus,rsaPublicKeyExponent);
			}catch(err){
				alert(err);
			}
			
			return false;
		}
		
		function fn_submitEncrytedForm(id,pw,rsaPublicKeyModulus,rsaPublicKeyExponent){
			var rsa = new RSAKey();
			rsa.setPublic(rsaPublicKeyModulus,rsaPublicKeyExponent);
			
			//RSA 암호화
			var securedId = rsa.encrypt(id);
			var securedPw = rsa.encrypt(pw);
			
			$.ajax({
				url: "/user/loginCheck",
				type: "POST",
				data: {"id":securedId , "pw":securedPw},
				success: function(data){
					if(data.pw){
						console.log(data);
						var comSubmit = new ComSubmit();
						comSubmit.setUrl("/user/loginPost");
						comSubmit.addParam("id",id);
						comSubmit.submit();
					}else{
						console.log(data);
						alert("아이디, 비밀번호를 확인해주세요");
						document.frm.reset();
					}
				}
			});
		}
			
		function fn_joinForm(){
			location.href="/user/join";
		}
	</script>
</body>
</html>