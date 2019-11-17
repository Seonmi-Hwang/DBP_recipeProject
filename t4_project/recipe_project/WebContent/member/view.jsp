<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 보기</title>
<script>
function memberRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body>
<br>
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>상세정보 보기</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table style="background-color: YellowGreen">
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
			 	 </c:url>">수정</a> &nbsp;
 	    <a href="<c:url value='/member/delete'>
				   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>" onclick="return memberRemove();">삭제</a> &nbsp;
 	    <a href="<c:url value='/member/list' />">목록</a> 	    
 	    <br><br>	   
 	    
 	    <!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${updateFailed || deleteFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
	  </td>
	</tr>
  </table> 
</body>
</html>