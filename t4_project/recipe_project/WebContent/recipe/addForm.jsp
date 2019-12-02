<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link rel="icon" href="../../images/favicon.ico">

<title>모두의 레시피: 레시피 추가</title>
<link href="../../css/main.css" rel="stylesheet" />
<link href="../../css/bootstrap.min.css" rel="stylesheet" />
<script src="../../js/ie-emulation-modes-warning.js"></script>
<link type="text/css" rel="stylesheet" href="../../css/addForm.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script>
var oTbl;
var statusIngre = 1;
var statusProc = 1;
//Row 추가
function insRow(tableId) {
	if (tableId == 'btnAddIngredient') {
		  oTbl = document.getElementById("addIngredient");
		  statusIngre += 1;
	}
	else {
		  oTbl = document.getElementById("addProcedure");
		  statusProc += 1;
	}
  var oRow = oTbl.insertRow();
  oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
  
  if (tableId == 'btnAddIngredient') {
	  var oCell_1 = oRow.insertCell();
	  //삽입될 Form Tag 
	  var frmTag = "<input type=text name=iname style=width:70%; placeholder=\"ex) 계란\"> ";
	  oCell_1.innerHTML = frmTag;
	  oCell_1.width = "40%";
	  
	  var oCell_2 = oRow.insertCell();
	  frmTag = "<input type=text name=quantity style=width:40%; placeholder=\"ex) 1알\"> ";
	  oCell_2.innerHTML = frmTag;
	  oCell_2.width = "30%";
	  
	  var oCell_3 = oRow.insertCell();
	  frmTag = "<input type=button id=btnRemoveIngre value='삭제' onClick='removeRow(this.id)' style='cursor:hand'> ";
	  oCell_3.innerHTML = frmTag;
	  oCell_3.width = "20%";
  }
  else {
	  oRow.style.height = "120px";
	  oRow.style.border = "1px solid #d1d1d1";
	  var oCell_1 = oRow.insertCell();
	  var frmTag = "<strong>과정 </strong>";
	  frmTag += "<input type=text name=proc_text placeholder=\"ex) 재료를 손질한다.\" size=50> <br> <br>";
	  frmTag += "<strong>순서 </strong>";
	  frmTag += "<input type=text name=proc_id size=10 placeholder=\"ex) 1\"> ";
	  oCell_1.width = "60%";
	  oCell_1.style.paddingLeft = "15px";
	  oCell_1.innerHTML = frmTag;
	  
	  var oCell_2 = oRow.insertCell();
	  frmTag = "<div class=field align=left> <input type=file id=files"+String(statusProc);
	  frmTag += " name=img_url accept=image/* onchange=loadImage("+String(statusProc)+") > ";
	  frmTag += "<img id=preview"+String(statusProc)+" /> </div> ";
	  oCell_2.innerHTML = frmTag;
	  oCell_2.width = "30%";
	  oCell_2.style.paddingLeft = "15px";
	  oCell_2.style.paddingTop = "22px";
	  oCell_2.style.verticalAlign = "top";
	  oCell_2.innerHTML = frmTag;
	  
	  var oCell_3 = oRow.insertCell();
	  frmTag = "<input type=button id=btnRemoveProc value='삭제' onClick='removeRow(this.id)' style='cursor:hand'> ";
	  oCell_3.style.paddingBottom = "22px";
	  oCell_3.style.verticalAlign = "bottom";
	  oCell_3.width = "10%";
	  oCell_3.innerHTML = frmTag;
  }
 
  
}
//Row 삭제
function removeRow(btnId) {
  oTbl.deleteRow(oTbl.clickedRowIndex);
  if (btnId == 'btnRemoveIngre')
	  statusIngre -= 1;
  else
	  statusProc -= 1;
}

function frmCheck()
{
  var frm = document.form;
  
  if (form.rname.value == "") {
		form.rname.placeholder = "레시피 이름을 입력하세요.";
		alert('레시피 이름을 입력하세요.');
		return false;
	}
	
	var inameLength = 0;
	$('input[name*="iname"]').map(function(i, item) {
		if (item.value != "")
			inameLength++;
	});
	if (inameLength == 0) {
		$('input[name*="iname"]').attr('placeholder', '재료 이름을 입력하세요.');
		alert('재료 이름을 입력하세요.');
		return false;
	}
	
	if (form.time.value == "") {
		form.time.placeholder = "10";
		alert('소요 시간을 입력하세요.');
		return false;
	}
	
	var proctextLength = 0;
	$('input[name*="proc_text"]').map(function(i, item) {
		if (item.value != "")
			proctextLength++;
	});
	if (proctextLength == 0) {
		$('input[name*="proc_text"]').attr('placeholder', '조리 과정을 입력하세요.');
		alert('조리 과정을 입력하세요.');
		return false;
	}

	form.submit();
	
  for( var i = 0; i <= frm.elements.length - 1; i++ ){
     if( frm.elements[i].name == "addText[]" )
     {
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
             frm.elements[i].focus();
             return;
          }
      }
   }
 }
//-->
</script>
</head>
<body>
	<div class="container">
		<div class="masthead">
			<br> <br>
			<p align="center">
				<a href="<c:url value="/main" />"><img
					src="<c:url value='/images/logo.png' />" alt="모두의 레시피" /></a>
			</p>
			<p align="right">
				<a
					href="<c:url value='/member/myPage'><c:param name='email_id' value='${curMemberId}'/></c:url>">${memberName}</a>
				&nbsp;&nbsp;&nbsp;<a href="<c:url value='/member/logout' />">로그아웃</a>
			</p>


			<div class="s003">
				<!--검색창 -->
				<form name="search" method="POST"
					action="<c:url value='/recipe/search' />">
					<div class="inner-form">
						<div class="input-field first-wrap">
							<div class="input-select">
								<select data-trigger="" name="category_id">
									<!-- request.getParameter("category_id")로 받으면 됨 -->
									<option value="5">전체 레시피</option>
									<option value="10">일반 레시피</option>
									<option value="20">SNS 인기 레시피</option>
									<option value="30">나만의 레시피</option>
								</select>
							</div>
						</div>
						<div class="input-field second-wrap">
							<input type="search" name="keyword" autocomplete="on"
								placeholder="검색하실 레시피를 입력하세요" />
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

					<!-- 메뉴 카테고리 바 -->
					<c:choose>
						<c:when test="${category_id == 0}">
							<li class="nav-item"><a class="nav-link active"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
									맞춤 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
									레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
									인기 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
									레시피</a></li>
						</c:when>
						<c:when test="${category_id == 10}">
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
									맞춤 레시피</a></li>
							<li class="nav-item"><a class="nav-link active"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
									레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
									인기 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
									레시피</a></li>

						</c:when>
						<c:when test="${category_id == 20}">
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
									맞춤 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
									레시피</a></li>
							<li class="nav-item"><a class="nav-link active"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
									인기 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
									레시피</a></li>
						</c:when>
						<c:when test="${category_id == 30}">
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
									맞춤 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
									레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
									인기 레시피</a></li>
							<li class="nav-item"><a class="nav-link active"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
									레시피</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='0' />
            			 </c:url>">재료
									맞춤 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='10' />
            			 </c:url>">일반
									레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='20' />
            			 </c:url>">SNS
									인기 레시피</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/recipe/list'>
            				<c:param name='category_id' value='30' />
            			 </c:url>">나만의
									레시피</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
		<br>
		<!-- Add Form  -->
		<form name="form" method="POST" enctype="multipart/form-data"
			action="<c:url value='/recipe/add' />">
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
						<table
							style="background-color: YellowGreen; width: 90%; height: 70%">
							<tr>
								<td width="15%" align="center" bgcolor="E6ECDE" height="auto">레시피
									이름</td>
								<td width="85%" bgcolor="ffffff" style="padding-left: 10px">
									<p>
										<input type="text"
											style="width: 50%; height: 30px; font-size: 20px;"
											name="rname" placeholder="레시피 이름을 입력하세요">
									</p>
								</td>
							</tr>
							<tr>
								<td width="15%" align="center" bgcolor="E6ECDE" height="auto">대표
									사진</td>
								<td width="85%" bgcolor="ffffff"
									style="padding-left: 10px; padding-top: 10px">
									<div class="field" align="left">
										<input type="file" id="resultImgFile" accept="image/*"
											name="resultimg"> <img id="resultImg" /> <input
											type="hidden" name="result_url">
									</div>

								</td>
							</tr>
							<tr>
								<td width="15%" align="center" bgcolor="E6ECDE" height="auto">재료
									정보</td>
								<td width="85%" bgcolor="ffffff" style="padding-left: 10px">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="10" bgcolor="#FFFFFF" height="25" align="left"><input
												id="btnAddIngredient" type="button" style="cursor: hand"
												onClick="insRow(this.id)" value="재료 추가"> <font
												color="#FF0000">*</font>추가버튼을 클릭해 보세요.</td>
										</tr>
										<tr>
											<td height="50">
												<table id="addIngredient" width="80%" cellspacing="0"
													cellpadding="0" bgcolor="#FFFFFF" border="0">
													<tr>
														<td width="40%"><input type="text" name="iname"
															style="width: 70%" placeholder="ex) 계란"></td>
														<td width="30%"><input type="text" name="quantity"
															style="width: 40%;" placeholder="ex) 1알"></td>
														<td align="right"></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="15%" align="center" bgcolor="E6ECDE" height="40px">소요
									시간</td>
								<td width="85%" bgcolor="ffffff" style="padding-left: 10px">
									<input type="text" style="width: 40px;" name="time"
									placeholder="10">&nbsp;&nbsp;<strong>분</strong>
								</td>

							</tr>
							<tr>
								<td width="15%" align="center" bgcolor="E6ECDE">조리 과정</td>
								<td width="85%" height="auto" bgcolor="ffffff"
									style="padding-left: 10px; padding-top: 10px">
									<table
										style="width: 100%; border: 0; cellpadding: 0; cellspacing: 0;">
										<tr>
											<td colspan="10" bgcolor="#FFFFFF" height="25" align="left"><input
												id="btnAddProcedure" type="button" style="cursor: hand"
												onClick="insRow(this.id)" value="조리 과정 추가"> <font
												color="#FF0000">*</font>추가버튼을 클릭해 보세요.</td>
										</tr>
										<tr>
											<td height="50">
												<table id="addProcedure" style="width: 100%; cellspacing: 0; cellpadding: 0;">
													<tr height="120px" style="border: 1px solid #d1d1d1;">
														<td width="60%" height="auto" style="vertical-align: top; padding-left: 15px">
															<br>
															<strong>과정 </strong> <input type="text" name="proc_text"
															placeholder="ex) 재료를 손질한다." size="50"><br> <br>
															<strong>순서 </strong><input type="text" name="proc_id"
															size="10" placeholder="ex) 1">
														</td>
														<td width="30%" height="auto"
															style="vertical-align: top; padding-top: 22px">
															<div class="field" align="left">
																<input type="file" id="files1" name="img_url"
																	accept="image/*">
																<img id="preview1" />
															</div>
														</td>
														<td width="10%"/>
													</tr>
												</table>
												<script type="text/javascript" src="../../js/addForm.js"></script>
											</td>
										</tr>
									</table></td>
							</tr>
							
						</table> <br>
						<table style="width: 100%;">
							<tr>
								<td align="left"><input type="button" value="추가"
									onClick="frmCheck()"> &nbsp; <input type="button"
									value="취소" onClick="history.back()"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<!-- Site footer -->
		<br>
		<hr>
		<footer class="footer">
			<p align="center">© TEAM4 Sommangchi</p>
		</footer>
	</div>
	<script src="../../js/extention/choices.js"></script>
	<script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="../../js/bootstrap.min.js"></script>
</body>
</html>