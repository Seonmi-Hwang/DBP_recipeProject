<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="images/favicon.ico">

<title>레시피수정-${recipe.rname} </title>
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<script>
function recipeModify() {
	if (form.rname.value == "") {
		form.rname.placeholder = "레시피 이름을 입력하세요.";
		alert('레시피 이름을 입력하세요.');
		return false;
	}
	if (form.iname.value == "") {
		form.iname.placeholder = "ex) 계란";
		alert('재료를 입력하세요.');
		return false;
	}
	if (form.quantity.value == "") {
		form.quantity.placeholder = "ex) 1알";
		alert('수량을 입력하세요.');
		return false;
	}
	if (form.time.value == "") {
		form.time.placeholder = "10";
		alert('소요 시간을 입력하세요.');
		return false;
	}
	if (form.proc_text.value == "") {
		form.proc_text.placeholder = "ex) 재료를 손질한다.";
		alert('조리 과정을 입력하세요.');
		return false;
	}
	if (form.proc_id.value == "") {
		form.proc_id.placeholder = "ex) 1";
		alert('조리 순서를 입력하세요.');
		return false;
	}

	form.submit();
}
</script>
</head>
<body>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center">
			<a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a>
		</p>
		<p align="right">
			<a
				href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${curMemberId}</a>
		</p>
		<p align="right">
			<a href="<c:url value='/member/logout' />">로그아웃</a>
		</p>

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
					<c:when test="${recipe.category_id == 0}">
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
					<c:when test="${recipe.category_id == 10}">
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
					<c:when test="${recipe.category_id == 20}">
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
					<c:when test="${recipe.category_id == 30}">
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
	<!-- Add Form  -->
	<form name="form" method="POST" action="<c:url value='/recipe/update' />">
		<input type="hidden" name="recipe_id" value="${recipe.recipe_id}" />
		<!-- AddRecipeController.java에서 list.jsp로부터 받아온 -->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>레시피 수정</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen;">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">레시피 이름</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px" >
								<p>
									<input type="text" style="width: 60%; height: 30px; font-size:20px;" name="rname"
										value="${recipe.rname}" placeholder="레시피 이름을 입력하세요">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">재료 정보</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<c:forEach var="ingredient" items="${recipe.ingredients}">
								<p>
									<input type="text" name="iname" value="${ingredient.iname}" placeholder="ex) 계란">
									<input type="text" name="quantity" value="${ingredient.quantity}" size="10" placeholder="ex) 1알">
								</p>
								</c:forEach>									
								
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addIngredient"></div> <br> <input type="button"
								id="add_ingredient" value="추가" onclick="addInput(this.id);" />
								<input type="button" id="delete_ingredient" value="삭제"
								onclick="deleteInput(this.id);" />
								 -->

							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="40px">소요 시간</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<input type="text" style="width: 40px; " name="time" value="${recipe.time}" placeholder="10">&nbsp;&nbsp;<strong>분</strong>
							</td>
								
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE">조리 과정</td>
							<td width="470" height="200" bgcolor="ffffff" style="padding-left: 10px">
									<c:forEach var="proc" items="${recipe.procedure}">
									<p>
										<input type="text" name="proc_text" value="${proc.text}" placeholder="ex) 재료를 손질한다.">
										<input type="text" name="proc_id" value="${proc.proc_Id}" size="10" placeholder="ex) ${proc.proc_Id}">
									</p>
								</c:forEach>
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="추가" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="삭제"
								onclick="deleteInput(this.value);" />
								-->
							</td>
						</tr>
					</table>
					<br>
					<table style="width: 100%;">
						<tr>
							<td align="left">
								<input type="button" value="수정" onClick="recipeModify()"> &nbsp; 
								<input type="button" value="취소" onClick="history.back()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>

    <script src="../js/extention/choices.js"></script>
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