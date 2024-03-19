<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--  ${user }-->

<body>
	<div class="container-xl mt-5">
		<div class="border rounded mx-auto p-4">

			<ul>
				<li>姓名 : ${ user.username }</li>
				<p />

				<li>註冊信箱 :${ user.email }</li>
				<p />

				<li>註冊時間 :${ user.signupdate  }</li>
				<p></p>
			</ul>
			<div class=" nav-item"><a
				href="/Login/app/mvc/login" class="text-success">登出</a>
		</div>
		</div>

	</div>
</body>
<%@ include file="/WEB-INF/view/footer.jsp"%>
