<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
function check(){
	//form.submit();
}
</script>
<style>
	ul {
		display: block;
		width: 1000px;
		border: 1px solid #a73f40;
		float:left;
	}
	
	.imgt{                                                         
		heght: 250px;
	}
	
	.img{
        position: relative;  
        width: 200px;                                                          
		heght: 250px;
        background-size: contain;
		
        padding:5px;
    }
    
    .hits {
    	position: absolute;
  		top: 8px;
  		left: 16px;
  		font-size: 18px;
  		border-radius: 5px;
    	background-color: #ffffff;
    }
    
    h3 {
    	text-align: center;
    }
    
    .recipeinfo{
    	width:100px;
    	height:50px;
    }
    
    .f1 {
    	width:110px;
    	height:200px;
    	float:left;
    }
    
    table{
    	float:left;
    	margin: 5px;
    }
</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="./list" method="get">
<input type="text" name="ingre" value="">
<input type="text" name="ingre" value=""><input type="submit" value="재료입력" onclick="check()">
</form>
<ul>
<c:forEach var="recipe" items="${recipeList}" varStatus="status">
			<table border="1"> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${recipe.recipe_id}'/>
			 		 			 </c:url>"> ${recipe.rname} </a></h4>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr height="250px">
					<td colspan="2" ><img src="${recipe.result_img}" alt="Recipe Image" class="img" /></td>
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
			
	</c:forEach>

</ul>
</body>

</html>