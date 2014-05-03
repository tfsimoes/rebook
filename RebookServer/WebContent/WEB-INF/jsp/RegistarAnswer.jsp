<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="db" class="beans.DBOperations" scope="application" />
<%
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	
	String result = String.valueOf(db.doRegist(username, password));
	
	out.print("{\"sucess\":\"" + result +"\"}");
%> 
</body>
</html>