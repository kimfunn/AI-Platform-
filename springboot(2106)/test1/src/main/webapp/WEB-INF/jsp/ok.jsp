<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
<script>
 $(document).ready(function(){
	 $('#yesBtn').click(function(){
		 alert("^^");
		 $.post('coffeeOrder',
				{},
				function(data){
					console.log(data);
				}
		 );
	 });
 });
</script>

</head>
<body>

	<c:choose>
		<c:when test="${emotion eq 'sad'}"> 
			슬퍼 보이시는데, 커피 한잔 어떠세요? 
			<button id="yesBtn">예</button><button id="noBtn">아니오</button>
		</c:when>
		<c:otherwise> file upload ok </c:otherwise>
	</c:choose>

</body>
</html>
