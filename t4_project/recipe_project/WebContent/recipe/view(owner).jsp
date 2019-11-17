<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>레시피-${recipe.rname} </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function recipeRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  <br>
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>${recipe.rname} </b>&nbsp;&nbsp;</td>
			<td width="470" bgcolor="ffffff" style="padding-right: 10">조회수: ${recipe.hits} </td>
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
				<c:forEach var="proc" items="${recipe.procedure}">
					<LI>${proc}
				</c:forEach>	
			</td>
		  </tr>
	 	</table>
	    <br>
	     <a href="<c:url value='/recipe/update'>	<!-- Get 요청 -->
	     		   <c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 	 </c:url>">수정</a> &nbsp;
 	    <a href="<c:url value='/recipe/delete'>
				   <c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 	 </c:url>" onclick="return recipeRemove();">삭제</a> &nbsp;
 	    <a href="<c:url value='/recipe/list' />">목록</a>	    
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