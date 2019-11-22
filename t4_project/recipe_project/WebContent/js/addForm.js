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
