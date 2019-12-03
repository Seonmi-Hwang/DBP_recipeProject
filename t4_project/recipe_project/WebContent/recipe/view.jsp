<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="../images/favicon.ico">

<title>모두의 레시피: ${recipe.rname} </title>
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src="../js/ie-emulation-modes-warning.js"></script>
<script>
function recipeRemove() {
	return confirm("정말 삭제하시겠습니까?");
}
</script>
<style>
.rimg {
	width: 300px;
	height: 300px;
}
</style>	
</head>
<body>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center"><a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a></p>
		<p align="right"><a href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${memberName}</a>
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
  <table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table style="width:100%"> 
		  <tr>
			<td width="55%">
			<div style="height:22;"><b style="background-color: f4f4f4">&nbsp;&nbsp;${recipe.rname} </b>&nbsp;&nbsp;</div>
			</td> 
	    	<td>
	    		<table style="width: 100%">
	    			<tr>
	    				<td width="40%" bgcolor="ffffff" style="padding-right: 10; text-align: left">
				    		<c:choose>
				    			<c:when test="${recipe.category_id eq '30'}">
				    				작성일: ${recipe.createdDate}
								</c:when> 
							</c:choose>
						</td>
						<td width="35%" bgcolor="ffffff" style="padding-right: 10; text-align: left">
				    		<c:choose>
				    			<c:when test="${recipe.category_id eq '30'}">
				    				작성자: ${recipe.writer}
								</c:when> 
							</c:choose>
						</td>
						<td width="25%" bgcolor="ffffff" style="padding-right: 10; text-align: right">조회수: ${recipe.hits} </td> 
	    			</tr>
	    		</table>
	    	</td>
	    	
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
			<td width="15%" align="center" bgcolor="E6ECDE" height="auto">대표 사진</td>
			<td width="85%" bgcolor="ffffff" style="padding-left: 10px; padding-top: 10px">
				<div class="field" align="left">
					<img src="${recipe.result_img}" class="rimg" onerror="this.src='https://image.flaticon.com/icons/svg/1609/1609793.svg'">
				</div>	
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">재료정보</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="ingredient" items="${recipe.ingredients}">
					<LI>${ingredient.iname} (${ingredient.quantity})
				</c:forEach>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">소요 시간</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${recipe.time} 분<%-- <%=recipe.getTime()%> --%>
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">조리 과정</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="proc" items="${recipe.procedure}">
					<p>${proc.proc_Id}. &nbsp;${proc.text}</p>
				</c:forEach>	
			</td>
		  </tr>
	 	</table>
	    <br>
	    
	    	<table style="width: 100%;">
				<tr>
					<td align="left">
						<c:if test="${memberName eq recipe.writer}">
							<a href="<c:url value='/recipe/update'>	<!-- Get 요청 -->
					     			   <c:param name='recipe_id' value='${recipe.recipe_id}'/>
							 		 </c:url>">수정</a> &nbsp;
				 	    	<a href="<c:url value='/recipe/delete'>
								 	  <c:param name='recipe_id' value='${recipe.recipe_id}'/>
								 	  <c:param name='email_id' value='${curMemberId}'/>
							</c:url>" onclick="return recipeRemove();">삭제</a> &nbsp;

						
	    				</c:if>
	    				<c:choose>
		    				<c:when test='${category_id == 0}'>
						 	    <a href="#" onclick="history.back()">목록</a>
					 	    </c:when>
					 	    <c:otherwise>
						 	    <a href="<c:url value='/recipe/list'>
						 	    			<c:param name='category_id' value='${recipe.category_id}'/>
						 	    		</c:url>">목록</a>
					 	    </c:otherwise>
				 	    </c:choose>
				 	    <br>
				 	       <!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
				        <c:if test="${updateFailed || deleteFailed}">
					      <font color="red"><c:out value="${exception.getMessage()}" /></font>
					    </c:if>  
					</td>
				</tr>
			</table>
 	    <br><br>	   
	  </td>
	</tr>
  </table>  
        <!-- Site footer -->
      <br><hr>
      <footer class="footer">
        <p align="center">© TEAM4 Sommangchi</p>
      </footer>
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
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>