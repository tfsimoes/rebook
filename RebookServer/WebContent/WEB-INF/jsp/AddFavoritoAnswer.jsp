<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="db" class="beans.DBOperations" scope="application" />
<%
	String idUser = (String) session.getAttribute("idUser");
	String idLivro = (String) session.getAttribute("idLivro");
	
	String result = String.valueOf(db.doAddFavorito(Integer.valueOf(idUser), Integer.valueOf(idLivro)));
	
	out.print("{\"sucess\":\"" + result +"\"}");
%> 