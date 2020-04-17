<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- contentType에 설정한 charset은 응답(response) 데이터의 인코딩 
	 <meta charset에 설정한 값은 jsp에서 요청 데이터의 인코딩-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 등록</h2>
	<br />
	<form action="userList.do" method="post">
		<input type="hidden" name="cmd" value="userInsert"> <!-- post 방식이라서 form 안에 코드로 전달/ get 일때는 쿼리스트링을 이용했음 -->
		<label>아이디 : </label>
		<input type="text" name="userid"><br> <!-- name에는 VO의 필드명과 똑같게 해주기! 또한 getParameter에 지정하는 명이 됨 -->
		<label>이름 : </label>
		<input type="text" name="name"><br>
		<label>성별 : </label>
		<input type="radio" name="gender" value="여">여
		<input type="radio" name="gender" value="남">남<br>
		<label>지역 : </label>
		<select name="city">
			<option value="">지역선택</option>
			<option value="경기">경기</option>
			<option value="서울">서울</option>
			<option value="제주">제주</option>
			<option value="강원">강원</option>
		</select><br><br>
		<input type="submit" value="등록 ">
	</form>
</body>
</html>