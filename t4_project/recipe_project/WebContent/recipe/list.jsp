<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <link rel="icon" href="../../favicon.ico">

    <title>모두의 레시피</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<div class="container">
	<div class="masthead">
        <h3 class="text-muted">모두의 레시피</h3>
        <nav>
          <ul>
            <li class="active"><a href="<c:url value='/main' />">Home</a></li>
            <li><a href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='0' />
            			 </c:url>">재료 맞춤 레시피</a></li>
            <li><a href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='10' />
            			 </c:url>">일반 레시피</a></li>
            <li><a href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='20' />
            			 </c:url>">SNS 인기 레시피</a></li>
            <li><a href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='30' />
            			 </c:url>">나만의 레시피</a></li>
          </ul>
        </nav>
	</div>

<table style="width:100%"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
	<tr>
	<c:forEach var="recipe" items="${recipeList}">
		<td>
			<table> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 		 			 </c:url>"> ${recipe.rname} </a>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr>
					<td> ${recipe.ingredients} </td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
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
</div>

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>