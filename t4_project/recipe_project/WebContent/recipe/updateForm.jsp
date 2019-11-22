<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="images/favicon.ico">

<title>�����Ǽ���-${recipe.rname} </title>
<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<script>
function recipeModify() {
	if (form.rname.value == "") {
		form.rname.placeholder = "������ �̸��� �Է��ϼ���.";
		alert('������ �̸��� �Է��ϼ���.');
		return false;
	}
	if (form.iname.value == "") {
		form.iname.placeholder = "ex) ���";
		alert('��Ḧ �Է��ϼ���.');
		return false;
	}
	if (form.quantity.value == "") {
		form.quantity.placeholder = "ex) 1��";
		alert('������ �Է��ϼ���.');
		return false;
	}
	if (form.time.value == "") {
		form.time.placeholder = "10";
		alert('�ҿ� �ð��� �Է��ϼ���.');
		return false;
	}
	if (form.proc_text.value == "") {
		form.proc_text.placeholder = "ex) ��Ḧ �����Ѵ�.";
		alert('���� ������ �Է��ϼ���.');
		return false;
	}
	if (form.proc_id.value == "") {
		form.proc_id.placeholder = "ex) 1";
		alert('���� ������ �Է��ϼ���.');
		return false;
	}

	form.submit();
}
</script>
</head>
<body>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center">
			<a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="����� ������" /></a>
		</p>
		<p align="right">
			<a
				href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${curMemberId}</a>
		</p>
		<p align="right">
			<a href="<c:url value='/member/logout' />">�α׾ƿ�</a>
		</p>

		<div class="s003">
			<!--�˻�â -->
			<form name="search" method="POST"
				action="<c:url value='/recipe/search' />">
				<div class="inner-form">
					<div class="input-field first-wrap">
						<div class="input-select">
							<select data-trigger="" name="category_id">
								<!-- request.getParameter("category_id")�� ������ �� -->
								<option value="5">��ü ������</option>
								<option value="10">�Ϲ� ������</option>
								<option value="20">SNS �α� ������</option>
								<option value="30">������ ������</option>
							</select>
						</div>
					</div>
					<div class="input-field second-wrap">
						<input type="search" name="keyword" autocomplete="on"
							placeholder="�˻��Ͻ� �����Ǹ� �Է��ϼ���" />
					</div>
					<div class="input-field third-wrap">
						<button class="btn-search" type="submit">
							<svg class="svg-inline--fa fa-search fa-w-16" aria-hidden="true"
								data-prefix="fas" data-icon="search" role="img"
								xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                <path fill="currentColor"
									d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
              </svg>
						</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<nav>
			<ul class="nav nav-pills nav-justified">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/main' />">Home</a></li>

				<!-- �޴� ī�װ� �� -->
				<c:choose>
					<c:when test="${recipe.category_id == 0}">
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">���
								���� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">�Ϲ�
								������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								�α� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">������
								������</a></li>
					</c:when>
					<c:when test="${recipe.category_id == 10}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">���
								���� ������</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">�Ϲ�
								������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								�α� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">������
								������</a></li>

					</c:when>
					<c:when test="${recipe.category_id == 20}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">���
								���� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">�Ϲ�
								������</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								�α� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">������
								������</a></li>
					</c:when>
					<c:when test="${recipe.category_id == 30}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">���
								���� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">�Ϲ�
								������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								�α� ������</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">������
								������</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">���
								���� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">�Ϲ�
								������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
								�α� ������</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">������
								������</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
	<br>
	<!-- Add Form  -->
	<form name="form" method="POST" action="<c:url value='/recipe/update' />">
		<input type="hidden" name="recipe_id" value="${recipe.recipe_id}" />
		<!-- AddRecipeController.java���� list.jsp�κ��� �޾ƿ� -->
		<table style="width: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>������ ����</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen;">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">������ �̸�</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px" >
								<p>
									<input type="text" style="width: 60%; height: 30px; font-size:20px;" name="rname"
										value="${recipe.rname}" placeholder="������ �̸��� �Է��ϼ���">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="22">��� ����</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<c:forEach var="ingredient" items="${recipe.ingredients}">
								<p>
									<input type="text" name="iname" value="${ingredient.iname}" placeholder="ex) ���">
									<input type="text" name="quantity" value="${ingredient.quantity}" size="10" placeholder="ex) 1��">
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
							<td width="120" align="center" bgcolor="E6ECDE" height="40px">�ҿ� �ð�</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<input type="text" style="width: 40px; " name="time" value="${recipe.time}" placeholder="10">&nbsp;&nbsp;<strong>��</strong>
							</td>
								
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE">���� ����</td>
							<td width="470" height="200" bgcolor="ffffff" style="padding-left: 10px">
									<c:forEach var="proc" items="${recipe.procedure}">
									<p>
										<input type="text" name="proc_text" value="${proc.text}" placeholder="ex) ��Ḧ �����Ѵ�.">
										<input type="text" name="proc_id" value="${proc.proc_Id}" size="10" placeholder="ex) ${proc.proc_Id}">
									</p>
								</c:forEach>
								<!-- ���߿� DOM���� ���� input �߰��ϸ�,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="�߰�" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="����"
								onclick="deleteInput(this.value);" />
								-->
							</td>
						</tr>
					</table>
					<br>
					<table style="width: 100%;">
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
</div>

    <script src="../js/extention/choices.js"></script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>
  
      <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>