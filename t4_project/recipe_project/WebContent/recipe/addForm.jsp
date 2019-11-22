<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>레시피 추가</title>
<link type="text/css" rel="stylesheet" href="../../css/addForm.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">

	function recipeAdd() {
		if (form.rname.value == "") {
			form.rname.placeholder = "레시피 이름을 입력하세요.";
			alert('레시피 이름을 입력하세요.');
			return false;
		}
		if (form.iname[0].value == "") {
			form.iname[0].placeholder = "ex) 계란";
			alert('재료를 입력하세요.');
			return false;
		}
		if (form.quantity[0].value == "") {
			form.quantity[0].placeholder = "ex) 1알";
			alert('수량을 입력하세요.');
			return false;
		}
		if (form.time.value == "") {
			form.time.placeholder = "10";
			alert('소요 시간을 입력하세요.');
			return false;
		}
		if (form.proc_text[0].value == "") {
			form.proc_text[0].placeholder = "ex) 재료를 손질한다.";
			alert('조리 과정을 입력하세요.');
			return false;
		}
		if (form.proc_id[0].value == "") {
			form.proc_id[0].placeholder = "ex) 1";
			alert('조리 순서를 입력하세요.');
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
		<!-- AddRecipeController.java에서 list.jsp로부터 받아온 -->
		<table style="width: 100%; height: 100%">
			<tr>
				<td width="20"></td>
				<td>
					<table>
						<tr>
							<td bgcolor="f4f4f4" height="25">&nbsp;&nbsp;<b>레시피 추가</b>&nbsp;&nbsp;
							</td>
						</tr>
					</table> <br>
					<table style="background-color: YellowGreen; width: 80%; height: 70%">
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">레시피
								이름</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<p>
									<input type="text"
										style="width: 60%; height: 30px; font-size: 20px;"
										name="rname" placeholder="레시피 이름을 입력하세요">
								</p>
							</td>
						</tr>
						<tr>
							<td width="120" align="center" bgcolor="E6ECDE" height="auto">재료
								정보</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<c:forEach var="i" begin="1" end="5">
									<p>
										<strong>${i}</strong> &nbsp;
										<input type="text" name="iname" placeholder="ex) 계란">
										<input type="text" name="quantity" size="10" placeholder="ex) ${i}알">
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
							<td width="120" align="center" bgcolor="E6ECDE" height="40px">소요
								시간</td>
							<td width="470" bgcolor="ffffff" style="padding-left: 10px">
								<input type="text" style="width: 40px;" name="time"
								placeholder="10">&nbsp;&nbsp;<strong>분</strong>
							</td>

						</tr>
						<tr>
							<td width="15%" align="center" bgcolor="E6ECDE">조리 과정</td>
							<td width="85%" height="auto" bgcolor="ffffff" style="padding-left: 10px; padding-top: 10px">
								<table style="height: 100%; width: 100%">
									<c:forEach var="i" begin="1" end="5">
										<tr height="120px">
											<td width="60%" height="auto" style="vertical-align: top">
												<strong>${i}</strong> &nbsp; <br>
												&nbsp;&nbsp;&nbsp;과정 <input type="text" name="proc_text" placeholder="ex) 재료를 손질한다." size="40"><br><br>
												&nbsp;&nbsp;&nbsp;순서 <input type="text" name="proc_id" size="10" placeholder="ex) ${i}">
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
									
									
								<!-- 나중에 DOM으로 동적 input 추가하면,
								<div id="addProcedure"></div> <br> <input type="button"
								id="add_procedure" value="추가" onclick="addInput(this.id);" /> <input
								type="button" id="delete_procedure" value="삭제"
								onclick="deleteInput(this.value);" />
								--></td>
						</tr>
					</table> <br>
					<table style="width: 100%;">
						<tr>
							<td align="left">
								<input type="button" value="추가" onClick="recipeAdd()"> &nbsp;
								<input type="button" value="취소" onClick="history.back()"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>