<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
// type="text/javascript">
	 var ilist = new Array();
	 
	 function wname(name){
		//var name = doc/ument.getElementById(name).text;
		document.getElementById("list").innerHTML+="<li>"+name+"</li>";
	};
	 
	function listget(ilist){
		
		for (var n in ilist){
			document.getElementById("list").innerHTML+=n;
	};
		
	
		 //document.getElementById("small").innerHTML="";
		 //var category = document.getElementById("small").text;
		 

	
	
	
	//<li>
			//<a href="<c:url value="/ingredient/cate" > 
 				//<c:param value='계란류' name='category'/> 
  				//</c:url>" onclick="wname()">계란류</a>
		//</li>
</script>
<meta charset="utf-8">
<style>
	.big {
    width: 100px;
	box-sizing: border-box;
	float: left;
	
	}
	
	.list {
    width: 100px;
	box-sizing: border-box;
	float: left;
	
	}
	
	.small {
    width: 200px;
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
    width: 500px;
    float:left
	}
	
	li {
    display: list-item;
    text-align: match-parent;
    border-bottom: 1px solid #dddddd;
    
	}
	.big>li{
		line-height: 40px;
	}
	
	scrollbar-track {
    	background: #f8f8f8;
	}
	
	.f1>ul {
    height: 300px;
    border: 1px solid #a73f40;
    background: #fff;
    overflow:auto;
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
<title>재료</title>
</head>
<body>

<fieldset class="f1">
	<ul class = "big">
		<li>
			<a href="<c:url value="/ingredient/cate" > 
 				<c:param value='빵류' name='category'/> 
  				</c:url>" onclick="listget(ilist)">빵류</a>
		</li>
		<li>
			<a href="<c:url value="/ingredient/cate" > 
 				<c:param value='채소류' name='category'/> 
  				</c:url>"onclick="listget(ilist)">채소류</a>
		</li>
		<li>
			<a href="<c:url value="/ingredient/cate" > 
 				<c:param value='계란류' name='category'/> 
  				</c:url>" onclick="listget(ilist)">계란류</a>
		</li>
	</ul>
	
	<ul class="small" id="small">
		 <c:forEach var="ingredient" items='${ingrecate}'>
			
		 	<li>
		 	<a id="${ingredient.iname}" onclick="wname('${ingredient.iname}'); return false;">
		 	${ingredient.iname}</a>
		 	</li>
		 </c:forEach> 
	</ul>
	<ul class="list" id="list">
		 
	</ul>
</fieldset>
</body>
</html>