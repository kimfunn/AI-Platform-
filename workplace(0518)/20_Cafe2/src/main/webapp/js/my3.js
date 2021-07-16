$(document).ready(function(){
	var login=$.cookie('logined');
	$("#msgDiv").html(login);
	

////////장바구니 넣기
	$(".orderForm").click(function(event){
			console.log(event.target.name);
			let basket=$.cookie("basket");
			console.log(basket);
			
			if(basket){//장바구니 있는 상태		
			    basket=JSON.parse(basket);
			    let flag=true;
			    
			    basket.product.forEach(function(item,index){
			    	if(item.name==event.target.name){//이미 있는 상품이면
			    		item.quantity +=1;
			    		flag=false;			    		
			    	}
			    });
			    
			    if(flag){
			    	basket.product.push({name:event.target.name,quantity:1});//새 품목 추가			    	
			    }	  
			     
			}else{//없는 상태
				basket={product:[{name:event.target.name,quantity:1}]};								
			}
			
			basket=JSON.stringify(basket);
			$.cookie("basket",basket);
			
			window.open('html/orderForm.html','_blank','width=600,height=400');
			
		});

	
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
			  	var obj=JSON.parse(data);
			  	
			  	if(obj.name){
			  		data =  obj.name+ "<input type='button' value='logout' id='logoutBtn' class='btn btn-primary'> <input type='button' value='탈퇴' id='deleteBtn' class='btn btn-warning'>";	
			  		//동적태그 라고 함 (logout)
			  		$.cookie("logined",data);		    
					$("#msgDiv").html(data);	
			  	}else{
			  		alert(obj.msg);
			  		location.reload();
			  	}
			  					   
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

////////////////////////회원탈퇴
	$(document).on("click", "#deleteBtn", function(event) { //로그아웃 처리
		alert("정말 탈퇴하시겠습니까?");
		$.post("memberDelete.jes",
			  {			   
			   
			  },
			  function(data, status){		  	
			  	
			  	$.removeCookie("logined");	    
				location.reload();						   
			  }
		);//end post() 
	});//end 로그아웃 처리

	
	

});
	

