<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="../images/favicon.ico">

<title>모두의 레시피: 마이페이지</title>

<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/ie-emulation-modes-warning.js"></script>

<script>
function memberRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
<style type="text/css">
	h3, h4 {
		text-align: center;
	}
	
	.row {
		margin-left : 50px;
	}
	
	td img {
		position: relative;  
		width: 330px;
		height: 250px;
		background-size: contain;
	}
	
	.ingre {
		height:100px;
		text-align: center;
	
	}
	
	    
</style>
</head>
<c:choose>
	<c:when test="${deleteComplete == 1}">
		<body onLoad="alert('삭제가 완료되었습니다.')">
	</c:when>
	<c:when test="${deleteComplete == 0}">
		<body onLoad="alert('삭제에 실패했습니다.')">
	</c:when>
	<c:otherwise>
		<body>
	</c:otherwise>
</c:choose>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center"><a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a></p>
        <p align="right"><a href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>" role="button" class="btn btn-primary">My Page</a>
        &nbsp;&nbsp;&nbsp;<a href="<c:url value='/member/logout' />">로그아웃</a></p>

		<div class="s003">
			<!--검색창 -->
			<form name="search" method="POST"
				action="<c:url value='/recipe/search' />">
				<div class="inner-form">
					<div class="input-field first-wrap">
						<div class="input-select">
							<select data-trigger="" name="category_id">
								<!-- request.getParameter("category_id")로 받으면 됨 -->
								<option value="5">전체 레시피</option>
								<option value="10">일반 레시피</option>
								<option value="20">SNS 인기 레시피</option>
								<option value="30">나만의 레시피</option>
							</select>
						</div>
					</div>
					<div class="input-field second-wrap">
						<input type="search" name="keyword" autocomplete="on"
							placeholder="검색하실 레시피를 입력하세요" />
					</div>
					<div class="input-field third-wrap">
						<button class="btn-search" type="submit">
							<svg class="svg-inline--fa fa-search fa-w-16" aria-hidden="true"
								data-prefix="fas" data-icon="search" role="img"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                <path fill="currentColor"
									d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
              </svg>
						</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<nav>
			<ul class="nav nav-pills nav-justified">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/main' />">Home</a></li>

				<!-- 메뉴 카테고리 바 -->
				<c:choose>
					<c:when test="${category_id == 0}">
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
								맞춤 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
								레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								인기 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
								레시피</a></li>
					</c:when>
					<c:when test="${category_id == 10}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
								맞춤 레시피</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
								레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								인기 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
								레시피</a></li>

					</c:when>
					<c:when test="${category_id == 20}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
								맞춤 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
								레시피</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								인기 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
								레시피</a></li>
					</c:when>
					<c:when test="${category_id == 30}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
								맞춤 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
								레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								인기 레시피</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
								레시피</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
								맞춤 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
								레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								인기 레시피</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
								레시피</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
<br>
  <div style="text-align:center;">
	<h1>My page</h1><br>	  	    
		<p>이메일 ID : &nbsp;&nbsp;${member.email_id}</p>
		<p>닉네임 : &nbsp;&nbsp;${member.mname}</p>
		<p>비선호 재료 : &nbsp;&nbsp;${nonPrefer}</p>

	    <br>
	    <a href="<c:url value='/member/update'>
	     		   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>" role="button" class="btn btn-info">프로필 설정</a>&nbsp;
 	    <a href="<c:url value='/member/delete'>
				   <c:param name='email_id' value='${member.email_id}'/>
			 	 </c:url>" onclick="return memberRemove();" role="button" class="btn btn-danger">회원 탈퇴</a>
		<c:if test="${updateFailed || deleteFailed}}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>    
 	    <br><br>
  </div>
  	 	<br><h3 align="center">내가 작성한 레시피</h3>
	 <div class="row"> <!-- 한 카테고리의 레시피들을 표현해줄 테이블 -->
		<c:forEach var="recipe" items="${recipeList}" varStatus="status">
			<div class="col-lg-4">
				<table border="1" style="margin-top : 20px;"> <!-- 레시피 한 개를 표현할 테이블 -->
					<tr>
						<td colspan="2" style="text-align:center;"><h4><a href="<c:url value='/recipe/view'>
						   				<c:param name='recipe_id' value='${recipe.recipe_id}'/>
						   				<c:param name='category_id' value='${recipe.category_id}'/>
				 		 			 </c:url>"> ${recipe.rname} </a></h4>  
				  		</td> <!--  recipe.getRecipeName() -->
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><img src="${recipe.result_img}" alt="Recipe Image"  /></td>
					</tr>
					<tr>
						<td colspan="2" class="ingre">&nbsp; ${recipe.ingredientsName}</td> <%-- 모든 원소들을 출력 | recipe.getIngredients() --%>
					</tr>  
					<tr>
						<td width="130px">&nbsp;소요시간 : ${recipe.time}분 </td> <!--  recipe.getTime() -->
						<td width="110px">&nbsp;조회수 : ${recipe.hits}회 </td> <!--  recipe.getHits() -->
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
	      <!-- Site footer -->
      <br><hr>
      <footer class="footer">
        <p align="center">© TEAM4 Sommangchi</p>
      </footer>
</div>
<script src="../js/extention/choices.js"></script>
<script>
	const choices = new Choices('[data-trigger]', {
		searchEnabled : false,
		itemSelectText : '',
	});
</script>

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="../js/bootstrap.min.js"></script>
</body>
</html>