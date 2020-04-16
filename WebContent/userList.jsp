<%@page import="jdbc.user.vo.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 리스트</h2>
	
	<%-- JSTL을 쓴다면 이렇게 안해도 된다!
		// 1. request 객체에 저장된 List 객체를 가져오기 - 형변환 필요
		List<UserVO> list = (List<UserVO>) request.getAttribute("userList");
		out.println(list);
		
	--%>
	
	<%-- JSTL 사용 --%>
	<table border="1">
		<tr>
			<th>이름</th>
		</tr>
		
		<!-- 데이터 반복시켜 출력 -->
		<c:forEach var="uservo" items="${userList }">
			<tr>
				<td><a href="userList.do?userid=${uservo.userid }&cmd=userDetail">${uservo.name }</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>