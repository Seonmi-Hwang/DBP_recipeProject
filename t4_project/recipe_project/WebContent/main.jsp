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
          <h2>오늘의 추천 레시피</h2>
          <p>???</p>
          <p><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
        </div>
        <div class="col-lg-4">
          <h2>SNS 인기 레시피</h2>
          <p>???</p>
          <p><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
       </div>
        <div class="col-lg-4">
          <h2>나만의 레시피</h2>
          <p>???</p>
          <p><a class="btn btn-primary" href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>" role="button">더 알아보기 »</a></p>
        </div>
      </div>

      <!-- Site footer -->
      <br><br>
      <footer class="footer">
        <p>© TEAM4 Sommangchi</p>
      </footer>

</div> <!-- /container -->

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>