<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>마이페이지</title>
</head>
<body>
<br>
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>My page</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table>
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${member.member_id}
			</td>
		  </tr>
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">이메일 ID</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${member.email_id}
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">이름</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${member.mname}
			</td>
		  </tr>
	 	</table>
	    <br>
	    <a href="<c:url value='/member/update/form'>
	     		   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>">프로필 설정</a>
		<c:if test="${updateFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
 	    <br><br>
	  </td>
	</tr>
  </table> 
</body>
</html>