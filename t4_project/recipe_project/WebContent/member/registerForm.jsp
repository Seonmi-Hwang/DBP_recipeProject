<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="../images/favicon.ico">

<title>모두의 레시피 - 회원가입</title>

<link href="../css/signin.css" rel="stylesheet">
<link href="../../css/signin.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/ie-emulation-modes-warning.js"></script>
<script src="../../js/ie-emulation-modes-warning.js"></script>

<script>
function memberCreate() {
	if (form.email_id.value == "") {
		alert("email(ID)을 입력하십시오.");
		form.email_id.focus();
		return false;
	} 
	if (form.pw.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.pw.focus();
		return false;
	}
	if (form.pw.value != form.pw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.pw2.focus();
		return false;
	}
	if (form.mname.value == "") {
		alert("이름을 입력하십시오.");
		form.mname.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email_id.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email_id.focus();
		return false;
	}
	form.submit();
}
</script>
</head>
<body>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center">
			<a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a>
		</p>
	</div>
<!-- registration form  -->
<form class="form-signin" name="form" method="POST" action="<c:url value='/member/register' />">
 	  <h3 class="form-signin-heading" align="center">회원가입</h3> 
	    <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${registerFailed}">
	      <font color="red" size="2"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    <br>	  
	    <label for="inputEmail" class="sr-only">이메일 아이디</label>
	    <input type="email" id="inputEmail" class="form-control" name="email_id" placeholder="이메일 주소" required autofocus>

		<label for="inputPassword" class="sr-only">비밀번호</label>
		<input type="password" id="inputPassword" class="form-control" name="pw" placeholder="비밀번호" required>

		<label for="inputPassword" class="sr-only">비밀번호 확인</label>
		<input type="password" id="inputPassword" class="form-control" name="pw2" placeholder="비밀번호 확인" required>

		<label for="inputName" class="sr-only">이름</label>
		<input type="text" id="inputName" class="form-control" name="mname" placeholder="닉네임" required> 
		
		<!-- <c:if test="${registerFailed}">value="${user.mname}"</c:if> 이건 왜 들어가는거지?!-->
		<br>

		<button class="btn btn-lg btn-primary btn-block" type="button" onClick="memberCreate()">회원가입</button>
		<button class="btn btn-lg btn-primary btn-block" type="button" onClick="history.back()">취소</button>
</form>
</div>
	<script src="../js/ie10-viewport-bug-workaround.js"></script>
	<script src="../../js/ie10-viewport-bug-workaround.js"></script>
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
</body>
</html>