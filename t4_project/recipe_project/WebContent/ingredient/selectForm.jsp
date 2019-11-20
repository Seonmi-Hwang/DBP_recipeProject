<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
//<-- type="text/javascript"--?>
document.getElementById("small").innerHTML="<p>dkdk</p><c:forEach var='Í≥ÑÎûÄÎ•ò' items='${ingrecate}'>"+
				 " <li>${ingredient.iname}</li>"+
				 "</c:forEach>";
	 function wname(){
<<<<<<< HEAD
		 var category = document.getElementById("small").text;
		 document.getElementById("small").innerHTML="<c:url value='/'>"
			+"<c:param name='category' value='∞Ë∂ı∑˘'/>"
				+"</c:url>";
				//"<c:forEach var='∞Ë∂ı∑˘' items='${ingreList}'>"+
				 //" <li>${ingredient.iname}</li>"+
				 //"</c:forEach>";
		 };
	
=======
		 //document.getElementById("small").innerHTML="";
		 //var category = document.getElementById("small").text;
		 return document.getElementById("small").innerHTML="<p>dk</p><c:forEach var='Í≥ÑÎûÄÎ•ò' items='${ingrecate}'>"+
		 " <li>${ingredient.iname}</li>"+
		 "</c:forEach>";
		 
		 }
	$(document).ready(function(){
		$("a").click(function(){ 	
			$("a").hide();
		
			//var str = ""
			//var tdArr = new Array();	// Î∞∞Ïó¥ ÏÑ†Ïñ∏
			
			// ÌòÑÏû¨ ÌÅ¥Î¶≠Îêú Row(<tr>)
			//var li = $(this);
		});
	});
>>>>>>> yoon
	
	
	
	//<li>
			//<a href="<c:url value="/ingredient/cate" > 
 				//<c:param value='Í≥ÑÎûÄÎ•ò' name='category'/> 
  				//</c:url>" onclick="wname()">Í≥ÑÎûÄÎ•ò</a>
		//</li>
		
		//<c:forEach var="ingredient" items='${ingreList}'>
		//<li><input type="checkbox" name="color" value="blue">${ingredient.iname}</input></li>
	//</c:forEach>
</script>
<meta charset="utf-8">
<style>
	.big {
    width: 100px;
	box-sizing: border-box;
	float: left;
	
	}
	
	.small {
    width: 200px;
    height: 100px;
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
    border-bottom: 1px solid #dddddd;
    
	}
	.big>li{
		line-height: 40px;
		
		
	}
	.f1>ul {
    height: 100px;
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
<title>Ïû¨Î£å</title>
</head>
<body>

<fieldset class="f1">
	<ul class = "big">
		<li>
			<a>${ingredient.icategory}</a> 
		</li>
		<li>
			<a href="<c:url value="/ingredient/cate" > 
 				<c:param value='Í≥ÑÎûÄÎ•ò' name='category'/> 
  				</c:url>" onclick="wname(); return true;">Í≥ÑÎûÄÎ•ò</a>
		</li>
	</ul>
	
	<ul class="small" id="small">
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	
		 <c:forEach var="1000" items='${ingreList}'>
		 <jsp:include page="selectForm.jsp">
        <jsp:param name="myVar" value="∞Ë∂ı∑˘"/>
    </jsp:include>
		 <li>
		 ${ingredient.iname}
		 </li>
=======
		<c:param name="category" value="∞Ë∂ı∑˘"></c:param>
		 <c:forEach var="ingredient" items='${ingreList}'>
			
		 	<li>${ingredient.iname}</li>
>>>>>>> yoon
=======
		
		 <c:forEach var="ingredient" items='${ingreList}'>
			
		 	<li><a>${ingredient.iname}</a></li>
>>>>>>> yoon
		 </c:forEach> 
	</ul>
=======
	
		</ul>
	
>>>>>>> yoon
</fieldset>
</body>
</html>