<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>������ ����</title>
<script>
function recipeModify() {
	if (form.recipeName.value == "") {
		form.recipeName.value = "������ �̸��� �Է��Ͻʽÿ�.";
		return false;
	}
	if (form.ingredient.value == "") {
		form.ingredient.value = "��Ḧ �Է��ϼ���.";
		return false;
	}
	if (form.time.value == "") {
		form.time.value = "�ҿ� �ð��� �Է��Ͻʽÿ�.";
		return false;
	}
	if (form.procedure.value == "") {
		form.procedure.value = "���� ������ �Է��Ͻʽÿ�.";
		return false;
	}
	
	form.submit();
}
</script>
</head>
<body>
<br>
<!-- Add Form  -->
<form name="form" method="POST" action="<c:url value='/recipe/add' />">
  <input type="hidden" name="recipe_id" value="${category_id}"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>������ ����</b>&nbsp;&nbsp;</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10"></td>
		  </tr>
	    </table>  
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">������ �̸�</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="recipeName" value="${recipe.rname}">
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">�������</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="ingredient" items="${recipe.ingredients}">
					<LI> <input type="text" style="width: 240" name="ingredient" value="${ingredient}">
				</c:forEach>
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">�ҿ� �ð�</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="time" value="${recipe.time}">
			</td>
		  </tr>		  
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">���� ����</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<c:forEach var="proc" items="${recipe.procedure}">
					<LI> <input type="text" style="width: 240" name="procedure" value="${proc}">
				</c:forEach>	
			</td>
		  </tr>
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="����" onClick="recipeModify()"> &nbsp;
			<input type="button" value="���" onClick="history.back()">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>