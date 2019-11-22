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

<title>모두의 레시피 - 로그인</title>

<link href="../css/signin.css" rel="stylesheet">
<link href="../../css/signin.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/ie-emulation-modes-warning.js"></script>
<script src="../../js/ie-emulation-modes-warning.js"></script>

</head>
<script>
function login() {
	if (form.email_id.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.email_id.focus();
		return false;
	} 
	if (form.pw.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.pw.focus();
		return false;
	}		
	form.submit();
}

function memberCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<body>
<br>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center">
			<a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a>
		</p>
	</div>
<!-- login form  -->
<form class="form-signin" name="form" method="POST" action="<c:url value='/member/login' />">
  <h3 class="form-signin-heading" align="center">로그인</h3>
	    <!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${loginFailed}">
	  	  <font color="red" size="2"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    <br>	
	    <label for="inputEmail" class="sr-only">이메일 아이디</label>
	    <input type="email" id="inputEmail" class="form-control" name="email_id" placeholder="이메일 아이디" required autofocus>

		<label for="inputPassword" class="sr-only">비밀번호</label>
		<input type="password" id="inputPassword" class="form-control" name="pw" placeholder="비밀번호" required>
		<div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>  
		<button class="btn btn-lg btn-primary btn-block" type="button" onClick="login()">로그인</button>
		<button class="btn btn-lg btn-primary btn-block" type="button" onClick="memberCreate('<c:url value='/member/register/form' />')">회원가입</button>
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