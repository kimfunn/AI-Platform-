$(document).ready(function(){
	var login=$.cookie('logined');
	$("#msgDiv").html(login);
	
	/////////////회원가입처리
	$("#memberInsertBtn").click(function(){//회원 가입 처리
	
		const name=$("#name").val();
		const id=$("#id").val();
		const pw=$("#pw").val();
		
		//alert(name+":"+id+":"+pw);
		
		$.post("../memberInsert.jes",
			  {
			    name,
			    id,
			    pw
			  },
			  function(data, status){
			    alert( data);
			   	window.close();
			  });
		
	});
	
	//////////////로그인처리/////////////////////
	$("#loginBtn").click(function(){//로그인 처리	
		
		const id=$("#id").val();
		const pw=$("#pw").val();
		
		//alert(id+":"+pw);		
		
		$.post("login.jes",
			  {			   
			    id,
			    pw
			  },
			  function(data, status){	
			  	//alert(data);	
			  	data += "<input type='button' value='logout' id='logoutBtn' class='btn btn-primary'>";	
			  	//동적태그 라고 함 (logout)
			  	$.cookie("logined",data);		    
				$("#msgDiv").html(data);						   
			  }
			  
		);//end post() 
		
	});//end 로그인 처리
	
	/////////////////////로그아웃처리
$(document).on("click", "#logoutBtn", function(event) { //로그아웃 처리
	
		$.post("logout.jes",
			  {			   
			   
			  },
			  function(data, status){		  	
			  	
			  	$.removeCookie("logined");	    
				location.reload();						   
			  }
		);//end post() 
	});//end 로그아웃 처리


	
	

});
	

