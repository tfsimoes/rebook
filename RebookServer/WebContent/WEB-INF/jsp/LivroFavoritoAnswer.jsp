<%@page import="utils.LivroUserSee"%>
<%@page import="utils.Livro"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="db" class="beans.DBOperations" scope="application" />
<%
	String idUser = (String) session.getAttribute("idUser");

	ArrayList<LivroUserSee> livros = db.doBuscaLivrosFavoritos(Integer.parseInt(idUser));
	
	out.write("{\"livros\":[");
	for(int i = 0; i<livros.size(); i++){
		
		if (i!=0) out.println(",");
		
		out.print("{\"idLivro\": \"" + livros.get(i).getIdLivro() + "\",");
		out.print("\"nome\": \"" + livros.get(i).getNome() + "\",");
		out.print("\"autor\": \"" + livros.get(i).getAutor() + "\",");
		out.print("\"nomeFicheiro\": \"" + livros.get(i).getNomeFicheiro() + "\",");
		out.print("\"derivado\": \"" + livros.get(i).getDerivado() + "\"");
		out.print("}");
	}
	out.write("]}");
%>