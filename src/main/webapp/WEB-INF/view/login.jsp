<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>


<script type="text/javascript">

function signin() {
window.location.href = '${ pageContext.request.contextPath }/app/mvc/signin';
	}
</script>
<meta charset="UTF-8">
<title>登入</title>


<!-- <img src="/ManagementSystem/app/img/sashimi.jpg"/>
    <!-- 
    <iframe
	  id="inlineFrameExample"
	  title="Inline Frame Example"
	  width="300"
	  height="200"
	  src="/ManagementSystem/app/img/簡報1.pdf">
	</iframe>
    -->

<div
	class="d-flex justify-content-center align-items-center vh-100 mx-auto ">
	<form class="needs-validation border rounded mx-auto p-4" novalidate
		method="post" id="loginForm"
		action="${ pageContext.request.contextPath }/app/mvc/login">
		<fieldset>
			<h4 class="text-center mb-3">Login</h4>
			<div style="color: red">${ loginMessage }</div>
			<div>
				<label for="username" class="form-label">🙋‍♀帳號:</label> <input
					type="text" class="form-control" id="username" name="username"
					value="bonita" placeholder="請輸入帳號" required>
				<div class="invalid-feedback">請輸入帳號</div>
			</div>
			<div>
				<label for="userpwd" class="form-label">🔑密碼:</label> <input
					type="password" class="form-control" id="userpwd" name="userpwd"
					value="123456" placeholder="請輸入密碼" required>
				<div class="invalid-feedback">請輸入密碼</div>
			</div>
			<div class="d-flex justify-content-center mt-4 mx-3">
				<button class="btn mx-3" style="background-color: #e3f2fd"
					type="submit" >登入</button>
				<button type="button" class="btn mx-3"
					style="background-color: #ccdce8" onclick="signin()">註冊</button>
			</div>
			<div class="d-flex justify-content-center mt-4 mx-auto ">
				<a href='${ pageContext.request.contextPath }/app/mvc/forgetpwdpage'>忘記密碼?</a>
			</div>
		</fieldset>

	</form>
</div>

<%@ include file="/WEB-INF/view/footer.jsp"%>
