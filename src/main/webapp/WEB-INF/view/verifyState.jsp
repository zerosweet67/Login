<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<meta charset="UTF-8">
<title>驗證</title>
<div
	class="d-flex justify-content-center align-items-center vh-100 mx-auto ">
	<form class="needs-validation border rounded mx-auto p-4 was-validated " novalidate
		method="post" id="loginForm"
		action="${ pageContext.request.contextPath }/app/mvc/verify">
		<fieldset>
			<label for="validationTextarea" class="form-label h4">驗證</label>
			<div>驗證碼已寄至 : ${ email }</div>
			<div>請盡速進行驗證</div>
			<input type="hidden" class="form-control" name="OTPcode"
				value="${OTPcode}"> <input type="hidden"
				class="form-control" name="username" value="${username}"> <input
				type="hidden" class="form-control" name="email" value="${email}">
			<div>
				<label for="verify" class="form-label">🙋請輸入信箱驗證碼:</label> 
				<input type="text" class="form-control is-invalid" id="verify" name="verify"
					placeholder="請輸入信箱驗證碼" required>
				<div class="invalid-feedback">請輸入信箱驗證碼</div>
			</div>

			<div class="d-flex justify-content-center mt-4 mx-3">

				<button type="submit" class="btn mx-3"
					style="background-color: #ccdce8" id="btn" name="btn">驗證</button>
			</div>
		</fieldset>

	</form>
</div>
<%@ include file="/WEB-INF/view/footer.jsp"%>