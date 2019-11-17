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
<body>

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

      <!-- Jumbotron -->
      <div class="jumbotron">
        <h1>Marketing stuff!</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h2>Safari bug warning!</h2>
          <p class="text-danger">As of v8.0, Safari exhibits a bug in which resizing your browser horizontally causes rendering errors in the justified nav that are cleared upon refreshing.</p>
          <p>???</p>
          <p><a class="btn btn-primary" href="#" role="button">View details »</a></p>
        </div>
        <div class="col-lg-4">
          <h2>Heading</h2>
          <p>???</p>
          <p><a class="btn btn-primary" href="#" role="button">View details »</a></p>
       </div>
        <div class="col-lg-4">
          <h2>Heading</h2>
          <p>???</p>
          <p><a class="btn btn-primary" href="#" role="button">View details »</a></p>
        </div>
      </div>

      <!-- Site footer -->
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