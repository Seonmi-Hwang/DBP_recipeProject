<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
function check(){
	form.submit();
}
</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="Recommend.jsp" method="post">
<input type="text" name="ingre">
<input type="text" name="ingre"><input type="submit" value="재료입력" onclick="check()">
</form>
<c:forEach var="recipe" items="recipeList">
<p>${recipe:recipe_id}</p>
</c:forEach>
</body>

</html>