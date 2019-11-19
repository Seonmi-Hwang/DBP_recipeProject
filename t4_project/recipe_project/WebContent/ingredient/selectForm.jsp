<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
	.big {
    width: 100px;
	box-sizing: border-box;
	float: left;
	
	}
	
	.small {
    width: 200px;
    height: 270px;
    float: left;
    padding: 10px 13px;
    box-sizing: border-box;
    font-family: Microsoft YaHei,'NS';
	}
	
	fieldset {
	
    display: block;
    margin-inline-start: 2px;
    margin-inline-end: 2px;
    padding-block-start: 0.35em;
    padding-inline-start: 0.75em;
    padding-inline-end: 0.75em;
    padding-block-end: 0.625em;
    min-inline-size: min-content;
    border-width: 2px;
    border-style: groove;
    border-color: threedface;
    border-image: initial;
	}
	
	.f1 {
    position: relative;
    width: 300px;
    float:left
	}
	
	li {
    display: list-item;
    text-align: match-parent;
    
	}
	.big>li{
		line-height: 40px;
		border-bottom: 1px solid #dddddd;
		
	}
	.f1>ul {
    height: 200px;
    border: 1px solid #a73f40;
    background: #fff;
	}
	
	ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
    padding: 5px;
    list-style: none;
    float: left;
	}
	a {
		text-decoration: none;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<fieldset class="f1">
	<ul class = "big">
		<li>
			<a href="javascript:;">빵류</a> 
		</li>
		<li>
			<a href="javascript:;">계란류</a>
		</li>
	</ul>
	<ul class="small" >
		<c:forEach var="ingredient" items="${ingreList}">
		  <li>
		  	${ingre.name}
		  </li>
	  </c:forEach>  
	</ul>
</fieldset>
</body>
<script type="text/javascript">
$(".big li").click(function(){ 	

	//var str = ""
	//var tdArr = new Array();	// 배열 선언
	
	// 현재 클릭된 Row(<tr>)
	//var li = $(this);
}
	
	
</script>
</html>