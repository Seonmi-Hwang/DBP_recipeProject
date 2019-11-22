<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>������ �߰�</title>
<link type="text/css" rel="stylesheet" href="../../css/addForm.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">

	function recipeAdd() {
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

</head>
<body>
	<br>
	<!-- Add Form  -->
	<form name="form" method="POST" action="<c:url value='/recipe/add' />">
		<input type="hidden" name="category_id" value="${category_id}" />
		<!-- AddRecipeController.java���� list.jsp�κ��� �޾ƿ� -->
		<table style="width: 100%; height: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>������ �߰�</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen; width: 80%; height: 70%">
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
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">���
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
							<td width="15%" align="center" bgcolor="E6ECDE">���� ����</td>
							<td width="85%" height="auto" bgcolor="ffffff" style="padding-left: 10px; padding-top: 10px">
								<table style="height: 100%; width: 100%">
									<c:forEach var="i" begin="1" end="5">
										<tr height="120px">
											<td width="60%" height="auto" style="vertical-align: top">
												<strong>${i}</strong> &nbsp; <br>
												&nbsp;&nbsp;&nbsp;���� <input type="text" name="proc_text" placeholder="ex) ��Ḧ �����Ѵ�." size="40"><br><br>
												&nbsp;&nbsp;&nbsp;���� <input type="text" name="proc_id" size="10" placeholder="ex) ${i}">
											</td>
											<td width="40%" height="auto" style="vertical-align: top; padding-top: 22px">
												<div class="field" align="left">
													<input type="file" id="files${i}" name="img_url" accept="image/*" onchange="loadImage(${i})"/>
													<img id="preview${i}"/> 
												</div>
											</td>	
										</tr>
									</c:forEach>
								</table>
								<script type="text/javascript" src="../../js/addForm.js"></script>
									
									
								<!-- ���߿� DOM���� ���� input �߰��ϸ�,
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