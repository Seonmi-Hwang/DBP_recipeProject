<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 수정</title>
<script>
function memberModify() {
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
<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/member/update' />">
  <input type="hidden" name="email_id" value="${member.email_id}"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 정보 수정</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이메일 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				${member.email_id}
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw" value="${member.pw}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw2" value="${member.pw}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="mname" value="${member.mname}">
			</td>
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="수정" onClick="memberModify()"> &nbsp;
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