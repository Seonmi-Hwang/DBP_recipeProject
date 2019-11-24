function recipeAdd() {
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
		
		/* 재료 수량 체크 */
//		var status = true;
//		var quantity = {};
//		$('input[name*="iname"]').map(function(idx, item) {
//			if (item.value != "") {
//				$('input[name*="quantity"]').map(function(i, quantityItem) {
//					if (i == idx) {
//						if (quantityItem.value == "") {
//							status = false;
//							quantity[item.value] = 'false';
//							break;
//						}
//						quantity[item.value] = 'true';
//					}
//				});
//			}
//		});
//		
//		for (var key in quantity) {
//			if (quantity[key] == 'false')
//				alert("재료 " + key + "의 수량을 입력하지 않았습니다!");
//		}
//		if (status == false)
//			return false;
			
		
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
	}

$('input[id^="files"]').change(function() {
	var tagId = $(this).attr('id');
	var idx = tagId.charAt(tagId.length-1);

	var fileList = this.files;

	var reader = new FileReader();
	reader.readAsDataURL(fileList[0]);

	reader.onload = function() {
		// 썸네일이미지 생성
		var tempImage = new Image(); // drawImage 메서드에 넣기 위해 이미지 객체화
		tempImage.src = reader.result; // data-uri를 이미지 객체에 주입
		tempImage.onload = function() {
			// 리사이즈를 위해 캔버스 객체 생성
			var canvas = document.createElement('canvas');
			var canvasContext = canvas.getContext("2d");

			// 캔버스 크기 설정
			canvas.width = 240; // 가로 250px
			canvas.height = 192; // 세로 250px

			// 이미지를 캔버스에 그리기
			canvasContext.drawImage(this, 0, 0, canvas.width, canvas.height);
			// 캔버스에 그린 이미지를 다시 data-uri 형태로 변환
			var dataURI = canvas.toDataURL("image/jpeg");

			// 썸네일 이미지 보여주기
			$('#preview'+idx).attr("src", dataURI);

		};
	};
});

$("#resultImgFile").change(function() {
	var fileList = this.files;

	var reader = new FileReader();
	reader.readAsDataURL(fileList[0]);

	reader.onload = function() {
		// 썸네일이미지 생성
		var tempImage = new Image(); // drawImage 메서드에 넣기 위해 이미지 객체화
		tempImage.src = reader.result; // data-uri를 이미지 객체에 주입
		tempImage.onload = function() {
			// 리사이즈를 위해 캔버스 객체 생성
			var canvas = document.createElement('canvas');
			var canvasContext = canvas.getContext("2d");

			// 캔버스 크기 설정
			canvas.width = 480; // 가로 250px
			canvas.height = 384; // 세로 250px

			// 이미지를 캔버스에 그리기
			canvasContext.drawImage(this, 0, 0, canvas.width, canvas.height);
			// 캔버스에 그린 이미지를 다시 data-uri 형태로 변환
			var dataURI = canvas.toDataURL("image/jpeg");

			// 썸네일 이미지 보여주기
			$('#resultImg').attr("src", dataURI);

		};
	};
});
