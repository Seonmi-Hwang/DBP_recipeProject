<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>레시피-${recipe.rname} </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  <br>
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td width="120" bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>${recipe.rname} </b>&nbsp;&nbsp;</td>
			<td width="300" bgcolor="ffffff" style="padding-right: 10; text-align: right;">조회수: ${recipe.hits} </td>
			<td width="170" bgcolor="ffffff" style="padding-right: 10; text-align: right;">
				작성일: <fmt:formatDate value="${recipe.createdDate}" pattern="yyyy/MM/dd" /></td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table style="background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">레시피 이름</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${recipe.rname}
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">재료정보</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="ingredient" items="${recipe.ingredients}">
					<LI>${ingredient}
				</c:forEach>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">소요 시간</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${recipe.time} <%-- <%=recipe.getTime()%> --%>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">조리 과정</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<table>
					<c:forEach var="proc" items="${recipe.procedure}">
						<tr>
							<td><LI> ${proc.text} </LI></td>
							<td>${proc.img_url}</td>
						</tr>
					</c:forEach>
				</table>
					
			</td>
		  </tr>
	 	</table>
	    <br>
	    
	  </td>
	</tr>
  </table>  
</body>
</html>