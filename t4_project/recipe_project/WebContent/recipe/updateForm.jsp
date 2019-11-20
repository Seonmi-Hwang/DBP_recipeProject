<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>레시피 수정</title>
<script>
function recipeModify() {
	if (form.recipeName.value == "") {
		form.recipeName.value = "레시피 이름을 입력하십시오.";
		return false;
	}
	if (form.ingredient.value == "") {
		form.ingredient.value = "재료를 입력하세요.";
		return false;
	}
	if (form.time.value == "") {
		form.time.value = "소요 시간을 입력하십시오.";
		return false;
	}
	if (form.procedure.value == "") {
		form.procedure.value = "조리 과정을 입력하십시오.";
		return false;
	}
	
	form.submit();
}
</script>
</head>
<body>
	<br>
	<!-- Add Form  -->
	<form name="form" method="POST" action="<c:url value='/recipe/update' />">
		<input type="hidden" name="recipe_id" value="${category_id}" />
		<!-- AddRecipeController.java에서 list.jsp로부터 받아온 -->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>레시피 수정</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen;">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">레시피 이름</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<p>
									<input type="text" style="width: 60%; height: 30px; font-size:20px;" name="rname"
										placeholder="레시피 이름을 입력하세요">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">재료
								정보</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<c:forEach var="i" begin="1" end="5">
									<p>
										<strong>${i}</strong> &nbsp;
										<input type="text" name="iname${i}" placeholder="ex) 계란">
										<input type="text" name="quantity${i}" size="10" placeholder="ex) ${i}알">
									</p>
								</c:forEach>
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addIngredient"></div> <br> <input type="button"
								id="add_ingredient" value="추가" onclick="addInput(this.id);" />
								<input type="button" id="delete_ingredient" value="삭제"
								onclick="deleteInput(this.id);" />
								 -->

							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="40px">소요 시간</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<input type="text" style="width: 40px; " name="time" placeholder="10">&nbsp;&nbsp;<strong>분</strong>
							</td>
								
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE">조리 과정</td>
							<td width="470" height="200" bgcolor="ffffff" style="padding-left: 10px">
								
								<c:forEach var="i" begin="1" end="5">
									<p>
										<strong>${i}</strong> &nbsp;<input type="text" name="procedure" placeholder="ex) 재료를 손질한다.">
										<input type="text" name="proc_id" size="10" placeholder="ex) ${i}">
									</p>
								</c:forEach>
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="추가" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="삭제"
								onclick="deleteInput(this.value);" />
								-->
							</td>
						</tr>
					</table>
					<br>
					<table style="width: 100%;">
						<tr>
							<td align="left">
								<input type="button" value="추가" onClick="recipeModify()"> &nbsp; 
								<input type="button" value="취소" onClick="history.back()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>