<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="../images/favicon.ico">
<title>����� ������: ������ ����</title>

<link href="../css/main.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/ie-emulation-modes-warning.js"></script>

<script>
function memberModify() {
	if (form.pw.value == "") {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
		form.pw.focus();
		return false;
	}
	if (form.pw.value != form.pw2.value) {
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		form.pw2.focus();
		return false;
	}
	if (form.mname.value == "") {
		alert("�̸��� �Է��Ͻʽÿ�.");
		form.mname.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email_id.value)==false) {
		alert("�̸��� ������ �ùٸ��� �ʽ��ϴ�.");
		form.email_id.focus();
		return false;
	}
	form.submit();
}
</script>
<style type="text/css">
	h3, h4 {
		text-align: center;
	}
	
	.row {
		margin-left : 50px;
	}
	
	td img {
		position: relative;  
        width: 330px;                                                          
		heght: 250px;
        background-size: cover;
	}
	
	.ingre {
		height:100px;
		text-align: center;
	
	}
	
	    
</style>
</head>
<body>
<div class="container">
	<div class="masthead">
		<br><br>
		<p align="center"><a href="<c:url value="/main" />"><img src="<c:url value='/images/logo.png' />" alt="����� ������" /></a></p>
        <p align="right"><a href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${memberName}</a>
        &nbsp;&nbsp;&nbsp;<a href="<c:url value='/member/logout' />">�α׾ƿ�</a></p>

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
					<c:when test="${category_id == 0}">
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
					<c:when test="${category_id == 10}">
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
					<c:when test="${category_id == 20}">
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
					<c:when test="${category_id == 30}">
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
<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/member/update' />">
  <input type="hidden" name="email_id" value="${member.email_id}"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>����� ���� ����</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�̸��� ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				${member.email_id}
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw" value="${member.pw}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">��й�ȣ Ȯ��</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="pw2" value="${member.pw}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">�̸�</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="mname" value="${member.mname}">
			</td>
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="����" onClick="memberModify()"> &nbsp;
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
	const choices = new Choices('[data-trigger]', {
		searchEnabled : false,
		itemSelectText : '',
	});
</script>

<!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
<script src="../js/bootstrap.min.js"></script>
</body>
</html>