<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First jsp practice</title>
</head>
<body>
	<h2>사용자 관리 메인</h2>
	
	<%
		Date date = new Date();
		out.println(date);
	%>
	
	<h4>현재 날짜는 <%=date %></h4>
	<br />
	<ol>
		<li><a href="userList.do?cmd=userList">사용자 리스트</a></li>
	</ol>
</body>
</html>