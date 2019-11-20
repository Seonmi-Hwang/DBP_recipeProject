<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">
<title>addProcedure.jsp</title>
<script type="text/javascript">
function parse_ingredients(inputbox) {
	  var ingredArray = new Array();
	  var str = inputbox.val();

	  ingredArray = str.split(/\s*,\s*/);
	  $(inputbox).parent().remove();

	  $("#ingredients li").each(function (index, element) {
	    if ($(element).find("input.input-ingred").val() == "" && $(element).find("input.input-amount").val() == "") {
	      $(element).remove();
	    }
	  });
function add_ingredient() {
	  $("#ingredients").append($('<li class="ui-sortable-handle">' +
	          '<input type="text" class="lft input-ingred" name="recipe[ingredients][][name]" placeholder="���� (��. ������ġ)" autocomplete="off"> ' +
	          '<input type="hidden" name="recipe[ingredients][][basefood_id]" class="input-id"> ' +
	          '<input type="text" class="rgt input-amount" name="recipe[ingredients][][amount]" placeholder="�з� (��. 1����)" autocomplete="off"> ' +
	          '<button type="button" class="btn_del" onclick="delete_ingredient(this)"></button>' +
	          '</li>')
	  );
	}

function delete_ingredient(obj) {
  if ($("#ingredients li").length == 1) {
    alert("�ּ� �Ѱ��� �̻��� ��Ḧ ������ּ���.");
  } else {
    $(obj).parent().remove();
  }
}
function parse_ingredients(inputbox) {
	  var ingredArray = new Array();
	  var str = inputbox.val();

	  ingredArray = str.split(/\s*,\s*/);
	  $(inputbox).parent().remove();

	  $("#ingredients li").each(function (index, element) {
	    if ($(element).find("input.input-ingred").val() == "" && $(element).find("input.input-amount").val() == "") {
	      $(element).remove();
	    }
	  });

	  for (var i = 0; i < ingredArray.length; i++) {
	    $("#ingredients").append($('<li class="ui-sortable-handle">' +
	        '<input type="hidden" name="recipe[ingredients][][id]"> ' +
	        '<input type="text" class="lft input-ingred" name="recipe[ingredients][][name]" value="' + ingredArray[i] + '"> ' +
	        '<input type="hidden" class="input-id" name="recipe[ingredients][][basefood_id]"> ' +
	        '<input type="text" class="rgt input-amount" name="recipe[ingredients][][amount]" placeholder="�з�"> ' +
	        '<button type="button" class="btn_del" onclick="delete_ingredient(this)">����</button> ' +
	        '</div>' +
	        '</li>'));
	  }
	}
</script>
</head>
<body>
	<div class="dd-chunk">
		<h3>�������</h3>
		<p>�޸��� ������ ��Ḯ��Ʈ�� �ٿ��ֱ� �غ����� (��. ���� 8��, �ް� 2��, �ұ� �ణ, ���� ����)</p>
		<ul class="lst_mate" id="ingredients">
			<li><input type="hidden" name="recipe[ingredients][][id]" /> <input
				class="lft input-ingred" placeholder="���� (��. ������ġ)" type="text"
				name="recipe[ingredients][][name]" /> <input class="input-id"
				type="hidden" name="recipe[ingredients][][basefood_id]" /> <input
				class="rgt input-amount" placeholder="�з�(��.1����)" type="text"
				name="recipe[ingredients][][amount]" />
				<button type="button" class="btn_del"
					onclick="delete_ingredient(this)">����</button></li>
			<li><input type="hidden" name="recipe[ingredients][][id]" /> <input
				class="lft input-ingred" placeholder="���� (��. ������ġ)" type="text"
				name="recipe[ingredients][][name]" /> <input class="input-id"
				type="hidden" name="recipe[ingredients][][basefood_id]" /> <input
				class="rgt input-amount" placeholder="�з�(��.1����)" type="text"
				name="recipe[ingredients][][amount]" />
				<button type="button" class="btn_del"
					onclick="delete_ingredient(this)">����</button></li>
			<li><input type="hidden" name="recipe[ingredients][][id]" /> <input
				class="lft input-ingred" placeholder="���� (��. ������ġ)" type="text"
				name="recipe[ingredients][][name]" /> <input class="input-id"
				type="hidden" name="recipe[ingredients][][basefood_id]" /> <input
				class="rgt input-amount" placeholder="�з�(��.1����)" type="text"
				name="recipe[ingredients][][amount]" />
				<button type="button" class="btn_del"
					onclick="delete_ingredient(this)">����</button></li>
		</ul>
		<div class="input-error"></div>
	</div>
	<input type="button" onclick="add_ingredient()" value="��� �߰�">
</body>
</html>