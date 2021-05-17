<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h3>회원 가입</h3>
	<form action ="/party/createPartyByRedirect" method="post">
		계정 이름 : <input type="text" name = "name">
		<br>
		성별 : 
		<div>
			<input type="radio" name = "sex" value="true" checked>
			<label for = "male">남자</label>
			
		</div>
		<div>
			<input type="radio" name = "sex" value="false" >
			<label for = "female">여자</label>
		</div>
		<br>
		생년월일<input type="date" name="birthDate">
		<br>
		<input type="hidden" name="listContactPoint[0].contactPointType" value="addr">
		주소<input type="text" name="listContactPoint[0].value" >

		<input type="hidden" name="listContactPoint[1].contactPointType" value="phoneNum">
		폰넘버<input type="text" name="listContactPoint[1].value" >


		<br>
		예시 : <input type="text" name = "nameOfM">
		 		
 		<button  type="submit">가입</button>
	</form>
	
</body>
</html>