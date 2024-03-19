<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/header.jsp"%>

<title>忘記密碼</title>

<div
	class="d-flex justify-content-center align-items-center vh-100 mx-auto ">
	<form class="needs-validation border rounded mx-auto p-4" novalidate
		method="post" id="loginForm"
		action="${ pageContext.request.contextPath }/app/mvc/forgetpwd">
		<fieldset>
			<h4 class="text-center mb-3">忘記密碼</h4>
			<div style="color: red">${ loginMessage }</div>
			<div>
				<label for="username" class="form-label">🙋請輸入帳號:</label> <input
					type="text" class="form-control" id="username" name="username"
					placeholder="請輸入帳號:" required>
				<div class="invalid-feedback">請輸入帳號:</div>
			</div>
			<div class="d-flex justify-content-center mt-4 mx-3">

				<button type="submit" class="btn mx-3"
					style="background-color: #ccdce8">確認</button>
			</div>
		</fieldset>

	</form>
</div>


<%@ include file="/WEB-INF/view/footer.jsp"%>
