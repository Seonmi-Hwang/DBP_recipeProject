<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="images/favicon.ico">
<title>마이페이지</title>
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
	    <a href="<c:url value='/member/update'>
	     		   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>">프로필 설정</a>&nbsp;
 	    <a href="<c:url value='/member/delete'>
				   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>" onclick="return memberRemove();">회원 탈퇴</a>
		<c:if test="${updateFailed || deleteFailed}}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
 	    <br><br>
	  </td>
	</tr>
  </table>
 <br>
 <div class="row"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
	<c:forEach var="recipe" items="${recipeList}" varStatus="status">
			<table border="1"> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 		 			 </c:url>"> ${recipe.rname} </a></h4>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr>
					<td colspan="2"><img src="${recipe.result_img}" alt="Recipe Image"  /></td>
				</tr>
				<tr>
					<td colspan="2" height="30px">
						&nbsp;재료 : ${recipe.ingredientsName}
					 </td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
				</tr>  
				<tr>
					<td width="130px">&nbsp;소요시간 : ${recipe.time}분 </td> <!--  recipe.getTime() -->
					<td width="110px">&nbsp;조회수 : ${recipe.hits}회 </td> <!--  recipe.getHits() -->
				</tr>
			</table>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${status.index % 4 == 0}"><br><br></c:if>
	</c:forEach>
</div>
</body>
</html>