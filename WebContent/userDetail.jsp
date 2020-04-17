<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 상세 정보</h2>
	
	${userDetail }
	<br />
	<ul>
		<li>아이디 : ${userDetail.userid }</li>
		<li>이름 : ${userDetail.name }</li>
		<li>성별 : ${userDetail.gender }</li>
		<li>지역 : ${userDetail.city }</li>
	</ul>
</body>
</html>