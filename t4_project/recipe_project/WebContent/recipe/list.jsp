<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>레시피 목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/recipe.css' />" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<%-- <table> --%>
<%-- <c:forEach var="category" items="${categories}"> --%>
<%--	<tr> --%>
		<table style="width:100%"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
			<tr>
			<c:forEach var="recipe" items="${recipeList}">
				<td>
					<table> <!-- 레시피 한 개를 표현할 테이블 -->
						<tr>
							<td> ${recipe.recipeId} </td> <!-- recipe.getRecipeID() --> 
						</tr>
						<tr>
							<td> ${recipe.recipeName} </td> <!--  recipe.getRecipeName() -->
						</tr>
						<tr>
							<td> ${recipe.ingredients} </td> <!-- recipe.getIngredients() -->
						</tr>
						<tr>
							<td> ${recipe.time} </td> <!--  recipe.getTime() -->
							<td> ${recipe.hits} </td> <!--  recipe.getHits() -->
						</tr>
					</table>
				</td>
			</c:forEach>
			</tr>
		</table>
<%--	</tr> --%>
<%-- </c:forEach> --%>
<%-- </table> --%>
</body>
</html>