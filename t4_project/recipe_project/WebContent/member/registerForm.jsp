<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="icon" href="images/favicon.ico">
<title>회원가입</title>
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
<br>
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/member/register' />">
  <table style="width: 100%">
    <tr>
      <td width="20"></td>
	  <td>
	    <table>
		  <tr>
		    <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원 가입</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	 
	    <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이메일 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="email_id">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw2">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="mname" 
				 	<c:if test="${registerFailed}">value="${user.mname}"</c:if>>
			</td>
		  </tr>
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="회원 가입" onClick="memberCreate()"> &nbsp;
			<input type="button" value="취소" onClick="history.back()">
			</td>
		  </tr>
	    </table>
	  </td>
    </tr>
  </table>  
</form>
</body>
</html>