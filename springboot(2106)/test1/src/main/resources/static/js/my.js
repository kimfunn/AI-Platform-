$(document).ready(function(){
	$('#homeDiv').click(function(){
		alert();
	})
});






/*$(document).ready(function(){
	//로그인 처리	
	$('#loginBtn').click(function(){
		
		var id=$('#id').val();
		var pw=$('#pw').val();
		
		alert(id+':'+pw);		
		
		$.post('login',
			  {			   
			    id,
			    pw
			  },
			  function(data){	
				console.log(data);
				alert(data);
			  	var obj=JSON.parse(data);
			  	console.log(obj);
			  	alert(data);

			  					   
			  }
		);
	});

});

*/