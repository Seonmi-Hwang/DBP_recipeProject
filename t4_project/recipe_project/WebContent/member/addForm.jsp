<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script>
function categoryChange(e) {
	
}
</script>
<body>
Category: <select name="mainCategory" style="width:200px" onchange="categoryChange(this)">
		<c:forEach var = "cat" items = "${catList}">
        	<option value="">${cat}</option>
        </c:forEach>
    </select>
Name: <select name="subCategory" style="width:200px">
        <option value="">전체</option>
    </select>
</body>
</html>