<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>레시피 추가</title>
<script>
	/*
	 function xSize(e)
	 {
	 var xe = document.getElementById('xt'), t;

	 e.onfocus = function()
	 {
	 t = setInterval(
	 function()
	 {
	 xe.value = e.value;
	 e.style.height = (xe.scrollHeight + 12) + 'px';
	 }, 100);
	 }

	 e.onblur = function()
	 {
	 clearInterval(t);
	 }
	

	 xSize(document.getElementById('ta'));
	 }
	 */
/* input 동적으로 추가 
 * var ingredient_arrInput = new Array(0);
	var ingredient_arrInputValue = new Array(0);
	var procedure_arrInput = new Array(0);
	var procedure_arrInputValue = new Array(0);

	function addInput(clicked_id) {
		var div_id = "";
		if (clicked_id == 'add_ingredient') {
			ingredient_arrInput.push(ingredient_arrInput.length);
			ingredient_arrInputValue.push("");
			div_id = 'addIngredient';
		}
		else { //clicked_id == 'add_procedure')
			procedure_arrInput.push(procedure_arrInput.length);
			procedure_arrInputValue.push("");
			div_id = 'addProcedure';
		}
		display(div_id);

	}

	function display(div_id) {
		document.getElementById(div_id).innerHTML = "<ol>";
		
		if (div_id == 'addIngredient') {
			for (intI = 0; intI < ingredient_arrInput.length; intI++) {
				document.getElementById(div_id).innerHTML += createInput(div_id,
						ingredient_arrInput[intI], ingredient_arrInputValue[intI]);
			}
		} else { //div_id == 'addProcedure'
			for (intI = 0; intI < procedure_arrInput.length; intI++) {
				document.getElementById(div_id).innerHTML += createInput(div_id,
						procedure_arrInput[intI], procedure_arrInputValue[intI]);
			}
		}
		
		document.getElementById(div_id).innerHTML += "</ol>";
	}

	function saveValue(div_id, intId, strValue) {
		if (div_id == 'addIngredient')
			ingredient_arrInputValue[intId] = strValue;
		else if (div_id == 'addProcedure')
			procedure_arrInputValue[intID] = strValue;
	}

	function createInput(div_id, id, value) {
		if (div_id == 'addIngredient') {
			return "<li><input type='text' id='ingredient_" + id
					+ "' onChange='javascript:saveValue(" + div_id + ", " + id
					+ ", this.value)' value='" + value + "'/></li>";
		}

		return "<li><input type='text' id='procedure_" + id
				+ "' onChange='javascript:saveValue(" + div_id + ", " + id
				+ ",this.value)' value='" + value + "'/></li>";

	}

	function deleteInput(clicked_id) {
		if (clicked_id == 'delete_ingredient') {
			div_id = 'addIngredient';
			if (ingredient_arrInput.length > 0) {
				ingredient_arrInput.pop();
				ingredient_arrInputValue.pop();
			}
		}
		else { //(clicked_id == 'delete_procedure')
			div_id = 'addProcedure';
			if (ingredient_arrInput.length > 0) {
				procedure_arrInput.pop();
				procedure_arrInputValue.pop();
			}
		
		}
		display(div_id);
	} 
*/	
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
	<form name="form" method="POST" action="<c:url value='/recipe/add' />">
		<input type="hidden" name="recipe_id" value="${category_id}" />
		<!-- AddRecipeController.java에서 list.jsp로부터 받아온 -->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>레시피 추가</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">레시피
								이름</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 50">
								<p>
									<input type="text" style="width: 50%; height: 40;" name="rname"
										placeholder="레시피 이름을 입력하세요">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">재료
								정보</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10">
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addIngredient"></div> <br> <input type="button"
								id="add_ingredient" value="추가" onclick="addInput(this.id);" />
								<input type="button" id="delete_ingredient" value="삭제"
								onclick="deleteInput(this.id);" />
								 -->

							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">소요
								시간</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="time"
								placeholder="소요 시간을 입력하세요">
								<ol>
									<li>&nbsp; <textarea id="ta"
											style="width: 300px; height: 32px; overflow-y: hidden"></textarea>
										<textarea id="xt"
											style="width: 300px; height: 1px; overflow-y: hidden; position: absolute; top: -9px"
											disabled></textarea> <input type="number" name="n" size="10" />
									</li>
					

								</ol></td>
								
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">조리
								과정</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10">
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="추가" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="삭제"
								onclick="deleteInput(this.value);" />
								-->
							</td>
						</tr>
					</table> <br>
					<table style="width: 100%">
						<tr>
							<td align="left"><input type="button" value="추가"
								onClick="recipeModify()"> &nbsp; <input type="button"
								value="취소" onClick="history.back()"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>