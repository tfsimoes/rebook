<%@page import="utils.LivroUserSee"%>
<%@page import="utils.Livro"%>
<%@page import="java.util.ArrayList"%>
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
	ArrayList<LivroUserSee> livros = db.doBuscaLivros();
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

</body>
</html>