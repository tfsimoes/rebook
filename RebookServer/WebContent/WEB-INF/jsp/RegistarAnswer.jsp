<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="db" class="beans.DBOperations" scope="application" />
<%
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	
	String result = String.valueOf(db.doRegist(username, password));
	
	out.print("{\"sucess\":\"" + result +"\"}");
%> 