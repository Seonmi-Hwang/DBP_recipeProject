<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 
	<link rel="icon" href="images/favicon.ico">
 
    <title>모두의 레시피</title> 

    <link href="css/main.css" rel="stylesheet" />
    <link href="css/bootstrap.min.css" rel="stylesheet" />
	<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
	
</head>
<style>
h3, h4 {
	text-align : center;
}

td img {
	width: 240px; 
	height: 192px;
	text-align : center;
}

</style>
<script>

</script>
<body>

<div class="container">
	<div class="masthead">
		<br><br>
        <p align="center"><a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피"/></a></p>
        <p align="right"><a href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${curMemberId}</a></p>
        <p align="right"><a href="<c:url value='/member/logout' />">로그아웃</a></p>
          
    <div class="s003"> <!-- 검색창 -->
      <form name="search" method="POST" action="<c:url value='/recipe/search' />" >
        <div class="inner-form">
          <div class="input-field first-wrap">
            <div class="input-select">
              <select data-trigger="" name="category_id">    
                <option value="5">전체 레시피</option>
                <option value="10">일반 레시피</option>
                <option value="20">SNS 인기 레시피</option>
                <option value="30">나만의 레시피</option>
              </select>
            </div>
          </div>
          <div class="input-field second-wrap">
            <input id="search" type="text" name="keyword" placeholder="검색하실 레시피를 입력하세요" />
          </div>
          <div class="input-field third-wrap">
            <button class="btn-search" type="submit">
              <svg class="svg-inline--fa fa-search fa-w-16" aria-hidden="true" data-prefix="fas" data-icon="search" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                <path fill="currentColor" d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
              </svg>
            </button>
          </div>
        </div>
      </form>
    </div>
        <br>
        <nav> 
          <ul class="nav nav-pills nav-justified"> <!-- 메뉴 카테고리 바 -->
            <li class="nav-item"><a class="nav-link active" href="<c:url value='/main' />">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료 맞춤 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS 인기 레시피</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의 레시피</a></li>
          </ul>
        </nav>
	</div>

	<br>

      <!-- Jumbotron -->
      <div class="jumbotron">
        <h1>재료 맞춤 레시피</h1>
        <p class="lead">냉장고 속 재료를 입력하여 레시피를 추천받으세요!</p>
        <p><a class="btn btn-lg btn-success" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>" role="button">재료입력</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h3>오늘의 추천 레시피</h3><br>
          <table border="1" style="margin-left: auto; margin-right: auto;"> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${commonTopRecipe.recipe_id}'/>
			 		 			 </c:url>"> ${commonTopRecipe.rname} </a></h4>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr>
					<td colspan="2"><img src="${commonTopRecipe.result_img}" alt="Recipe Image"  /></td>
				</tr>
				<tr>
					<td colspan="2" height="30px">
						&nbsp;재료 : ${commonTopRecipe.ingredientsName}
					 </td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
				</tr>  
				<tr>
					<td width="130px">&nbsp;소요시간 : ${commonTopRecipe.time}분 </td> <!--  recipe.getTime() -->
					<td width="110px">&nbsp;조회수 : ${commonTopRecipe.hits}회 </td> <!--  recipe.getHits() -->
				</tr>
			</table>
			<br>
          <p align="center"><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
        </div>
        <div class="col-lg-4">
          <h3>SNS 인기 레시피</h3><br>
           <table border="1" style="margin-left: auto; margin-right: auto;"> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${snsTopRecipe.recipe_id}'/>
			 		 			 </c:url>"> ${snsTopRecipe.rname} </a></h4>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr>
					<td colspan="2"><img src="${snsTopRecipe.result_img}" alt="Recipe Image"  /></td>
				</tr>
				<tr>
					<td colspan="2" height="30px">
						&nbsp;재료 : ${snsTopRecipe.ingredientsName}
					 </td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
				</tr>  
				<tr>
					<td width="130px">&nbsp;소요시간 : ${snsTopRecipe.time}분 </td> <!--  recipe.getTime() -->
					<td width="110px">&nbsp;조회수 : ${snsTopRecipe.hits}회 </td> <!--  recipe.getHits() -->
				</tr>
			</table>
			<br>
          <p align="center"><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
       </div>
        <div class="col-lg-4">
          <h3>나만의 레시피</h3><br>
           <table border="1" style="margin-left: auto; margin-right: auto;"> <!-- 레시피 한 개를 표현할 테이블 -->
				<tr>
					<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
					   				<c:param name='recipe_id' value='${myTopRecipe.recipe_id}'/>
			 		 			 </c:url>"> ${myTopRecipe.rname} </a></h4>  
			  		</td> <!--  recipe.getRecipeName() -->
				</tr>
				<tr>
					<td colspan="2"><img src="${myTopRecipe.result_img}" alt="Recipe Image"  /></td>
				</tr>
				<tr>
					<td colspan="2" height="30px">
						&nbsp;재료 : ${myTopRecipe.ingredientsName}
					 </td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
				</tr>  
				<tr>
					<td width="130px">&nbsp;소요시간 : ${myTopRecipe.time}분 </td> <!--  recipe.getTime() -->
					<td width="110px">&nbsp;조회수 : ${myTopRecipe.hits}회 </td> <!--  recipe.getHits() -->
				</tr>
			</table>
			<br>
          <p align="center"><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
        </div>
      </div>

      <!-- Site footer -->
      <br><br>
      <footer class="footer">
        <p align="center">© TEAM4 Sommangchi</p>
      </footer>

</div> <!-- /container -->
    <script src="js/extention/choices.js"></script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>


    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>