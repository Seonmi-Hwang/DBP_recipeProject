<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
</head>
<script>
function login() {
	if (form.userId.value == "") {
		alert("����� ID�� �Է��Ͻʽÿ�.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		form.password.focus();
		return false;
	}		
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<body>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- login form  -->
<form name="form" method="POST" action="<c:url value='/user/login' />">
  <table style="width:100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� - �α���</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <!-- �α����� ������ ��� exception ��ü�� ����� ���� �޽����� ��� -->
        <c:if test="${loginFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">����� ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="userId">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="password" style="width:240" name="password">
			</td>
		  </tr>
	    </table>
	    <br>	  
	    <table style="width:100%">
		  <tr>
			<td align=left>
			<input type="button" value="�α���" onClick="login()"> &nbsp;
			<input type="button" value="ȸ������" onClick="userCreate('<c:url value='/user/register/form' />')">
			</td>						
		  </tr>
	    </table>
	  </td>	  
	</tr>
  </table>  
</form>
</body>
</html>