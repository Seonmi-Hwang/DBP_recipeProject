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

    <link href="../css/bootstrap.min.css" rel="stylesheet">
	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	
</head>
<style>
div, h3, h4, tr, td {
	text-align : center;
}

img {
	width: 240px; 
	height: 192px;
	style="text-align:center"
}
</style>
<body>

<div class="container">
	<div class="masthead">
		<br>
        <h3 class="text-muted">모두의 레시피</h3> <br>
        <nav>
          <ul class="nav nav-pills nav-justified">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/main' />">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='0' />
            			 </c:url>">재료 맞춤 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='10' />
            			 </c:url>">일반 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='20' />
            			 </c:url>">SNS 인기 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='categoryId' value='30' />
            			 </c:url>">나만의 레시피</a></li>
          </ul>
        </nav>
	</div>
	
<br>
	
<div class="row"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
	<c:forEach var="recipe" items="${recipeList}" varStatus="status">
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
	</c:forEach>
</div>
     

      <!-- Site footer -->
      <br><br>
      <footer class="footer">
        <p>© TEAM4 Sommangchi</p>
      </footer>

</div>

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>