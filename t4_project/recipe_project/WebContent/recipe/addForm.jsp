<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>������ �߰�</title>
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
	/* input �������� �߰� 
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
		if (form.rname.value == "") {
			form.rname.placeholder = "������ �̸��� �Է��ϼ���.";
			alert('������ �̸��� �Է��ϼ���.');
			return false;
		}
		if (form.iname[0].value == "") {
			form.iname[0].placeholder = "ex) ���";
			alert('��Ḧ �Է��ϼ���.');
			return false;
		}
		if (form.quantity[0].value == "") {
			form.quantity[0].placeholder = "ex) 1��";
			alert('������ �Է��ϼ���.');
			return false;
		}
		if (form.time.value == "") {
			form.time.placeholder = "10";
			alert('�ҿ� �ð��� �Է��ϼ���.');
			return false;
		}
		if (form.proc_text[0].value == "") {
			form.proc_text[0].placeholder = "ex) ��Ḧ �����Ѵ�.";
			alert('���� ������ �Է��ϼ���.');
			return false;
		}
		if (form.proc_id[0].value == "") {
			form.proc_id[0].placeholder = "ex) 1";
			alert('���� ������ �Է��ϼ���.');
			return false;
		}

		form.submit();
	}
</script>
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
</head>
<body>
	<br>
	<!-- Add Form  -->
	<form name="form" method="POST" action="<c:url value='/recipe/add' />">
		<input type="hidden" name="category_id" value="${category_id}" />
		<!-- AddRecipeController.java���� list.jsp�κ��� �޾ƿ� -->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>������ �߰�</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen;">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">������
								�̸�</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<p>
									<input type="text"
										style="width: 60%; height: 30px; font-size: 20px;"
										name="rname" placeholder="������ �̸��� �Է��ϼ���">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">���
								����</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<c:forEach var="i" begin="1" end="5">
									<p>
										<strong>${i}</strong> &nbsp;
										<input type="text" name="iname" placeholder="ex) ���">
										<input type="text" name="quantity" size="10" placeholder="ex) ${i}��">
									</p>
								</c:forEach>
								<!-- ���߿� DOM���� ���� input �߰��ϸ�,
								<div id="addIngredient"></div> <br> <input type="button"
								id="add_ingredient" value="�߰�" onclick="addInput(this.id);" />
								<input type="button" id="delete_ingredient" value="����"
								onclick="deleteInput(this.id);" />
								 -->

							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="40px">�ҿ�
								�ð�</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<input type="text" style="width: 40px;" name="time"
								placeholder="10">&nbsp;&nbsp;<strong>��</strong>
							</td>

						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE">���� ����</td>
							<td width="470" height="200" bgcolor="ffffff"
								style="padding-left: 10px"><c:forEach var="i" begin="1"
									end="5">
									<p>
										<strong>${i}</strong> &nbsp;<input type="text"
											name="proc_text" placeholder="ex) ��Ḧ �����Ѵ�."> <input
											type="text" name="proc_id" size="10" placeholder="ex) ${i}">
									</p>
								</c:forEach> <!-- ���߿� DOM���� ���� input �߰��ϸ�,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="�߰�" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="����"
								onclick="deleteInput(this.value);" />
								--></td>
						</tr>
					</table> <br>
					<table style="width: 100%;">
						<tr>
							<td align="left">
								<input type="button" value="�߰�" onClick="recipeAdd()"> &nbsp;
								<input type="button" value="���" onClick="history.back()"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>