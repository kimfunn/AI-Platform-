$(document).ready(function(){
	
	$("#login_btn").click(function(){
		const id = $("#id").val();
		const pw = $("#pw").val();
		$.post(
			"loginById",
			{id,pw},
			function(data, status){
				data = JSON.parse(data);
				if(data.id){
					alert("Data:" + data.id+"님 login ok \n Status: "+status);
			//	$("#loginSpan").html(data.id+"님 환영합니다.");
	
	}else{
		alert("다시 로그인해주세요");
			}
		  }
		);
	});
	
});