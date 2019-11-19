<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <link rel="icon" href="../../favicon.ico">

    <title>모두의 레시피</title>

    <link href="../css/main.css" rel="stylesheet" />
    <link href="../css/bootstrap.min.css" rel="stylesheet">
	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	
</head>
<style>
h3, h4, tr, td {
	text-align : center;
}

img {
	width: 240px; 
	height: 192px;
	text-align : center;
}
</style>
<body>
<div class="row"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
		<table border="1"> <!-- 레시피 한 개를 표현할 테이블 -->
			<tr>
				<td colspan="2"><h4><a href="<c:url value='/recipe/view'>
					  				<c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 					 </c:url>"> ${recipe.rname} </a></h4>  
			  	</td> <!--  recipe.getRecipeName() -->
			</tr>
			<tr>
				<td colspan="2"><img src="${recipe.result_img}" alt="Recipe Image"  /></td>
			</tr>
			<tr>
				<td colspan="2" height="30px">
					<c:forEach var="ingredient" items="${recipe.ingredients}">
	            		<LI>${ingredient}
	           		</c:forEach>
				</td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
			</tr>  
			<tr>
				<td width="130px"> 소요시간 : ${recipe.time}분 </td> <!--  recipe.getTime() -->
				<td width="110px"> 조회수 : ${recipe.hits}회 </td> <!--  recipe.getHits() -->
			</tr>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${status.index % 4 == 0}"><br><br></c:if>
</div>
</body>
</html>