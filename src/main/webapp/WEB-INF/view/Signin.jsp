<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<meta charset="UTF-8">
<title>註冊</title>


<div
	class="d-flex justify-content-center align-items-center vh-100 mx-auto ">
	<form class="needs-validation border rounded mx-auto p-4" novalidate
		method="post" id="loginForm"
		action="${ pageContext.request.contextPath }/app/mvc/signin">
		<fieldset>
			<h4 class="text-center mb-3">註冊</h4>
			<span class="col-12 col-md-1  text-nowrap " id="usernameError"
				name="usernameError" style="color: red;"></span>
			<div>
				<label for="username" class="form-label">🙋‍帳號:</label> <input
					type="text" class="form-control" id="username" name="username"
					placeholder="請輸入帳號" required>
				<div class="invalid-feedback">請輸入帳號</div>
			</div>
			<div>
				<label for="userpwd" class="form-label">🔑密碼:</label> <input
					type="password" class="form-control" id="userpwd" name="userpwd"
					placeholder="請輸入密碼" required>
				<div class="invalid-feedback">請輸入密碼</div>
			</div>
			<div>
				<label for="email" class="form-label">🔑信箱:</label> <input
					type="email" class="form-control" id="email" name="email"
					placeholder="請輸入信箱" required>
				<div class="invalid-feedback">請輸入信箱</div>
			</div>
			<span class="col-12 col-md-1  text-nowrap " id="emailError"
				name="emailError" style="color: red;"></span>
			<div class="d-flex justify-content-center mt-4 mx-3">
				<button class="btn mx-3" style="background-color: #e3f2fd"
					type="reset">清除</button>
				<button type="submit" class="btn mx-3"
					style="background-color: #ccdce8">註冊</button>
				<button class="btn mx-3" type="button" style="background-color: #ccdce8"
					onclick='login()'>返回</button>
			</div>
			<div id="forgotPasswordMessage" class="text-danger"></div>
			<div style="color: red">
				<c:forEach items="${errors}" var="error">
					<li>${error.defaultMessage}</li>
				</c:forEach>
			</div>
		</fieldset>
	</form>
</div>

<script>
	function login() {
		window.location.href = '${ pageContext.request.contextPath }/app/mvc/login';
	}
</script>

<script>
	$(document).ready(function() {
		$('#username').blur(function() {
			var username = $(this).val();

			$.ajax({
				type : 'GET',
				url : '/Login/app/mvc/checkUsername',
				data : {
					username : username
				},
				success : function(response) {
					console.log($('#usernameError'))
					if (response === 'exists') {
						$('#usernameError').text('此帳號已存在');
					} else {
						$('#usernameError').text('');
					}
					console.log(response)
				}
			});
		});
	});
	$(document).ready(function() {
		$('#email').blur(function() {
			var email = $(this).val();
			console.log(email);

			$.ajax({
				type : 'GET',
				url : '/Login/app/mvc/checkUseremail',
				data : {
					email : email
				},
				success : function(response) {
					if (response === 'exists') {
						$('#emailError').text('此信箱已註冊');
					} else {
						$('#emailError').text('');
					}
				}
			});
		});
	});
</script>
<%@ include file="/WEB-INF/view/footer.jsp"%>
