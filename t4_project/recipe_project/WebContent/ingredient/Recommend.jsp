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
		width: 300px;
		height: 300px;
		border: 1px solid #a73f40;
	
	}
</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="./list" method="get">
<input type="text" name="ingre">
<input type="text" name="ingre"><input type="submit" value="재료입력" onclick="check()">
</form>
<ul>
<c:forEach var="recipe" items="${recipeList}" varStatus="i">
	<li>${recipeList[i.index]}</li>
</c:forEach>
</ul>
</body>

</html>