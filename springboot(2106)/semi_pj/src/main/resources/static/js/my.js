function f_loginCheck(){
	
	var id= $('input[id="id"]').val();
	var pw = $('input[id="pw"]').val();
	var saveIdCheck=$('#save_id:checked').val();
	
	if(savaIdCheck=='on'){
		localStorage.setItem("saveId",id);
		
	}	else{
		localStorage.setItem("saveId",'N');
	}
	
	var loginData={"id":id,"pw":pw};
	
	$.post({
		
	})
	
	
	
	
	
	
	
	}

