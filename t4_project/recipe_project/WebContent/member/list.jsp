<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Member 관리</title>
</head>
<body>
<br>
<table style="width:100%">
  <tr>
  	<td width="20"></td>
    <td><a href="<c:url value='/user/logout' />">로그아웃(&nbsp;${curUserId}&nbsp;)</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <td width="190" align="center" bgcolor="E6ECDE" height="22">member_id</td>
		  <td width="200" align="center" bgcolor="E6ECDE">email_id</td>
		  <td width="200" align="center" bgcolor="E6ECDE">mname</td>
		</tr>
  	
	  <c:forEach var="member" items="${memberList}">  			  	
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	${member.member_id}
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			<a href="<c:url value='/member/view'>
					   <c:param name='email_Id' value='${member.email_id}'/>
			 		 </c:url>">
			  ${member.email_id}</a>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${member.mname}
		  </td>
		</tr>
	  </c:forEach>  
	  </table>	  	 
	  <br>   
	  <a href="<c:url value='/member/register/form' />">사용자 추가</a>
	</td>
  </tr>
</table> 
</body>
</html>