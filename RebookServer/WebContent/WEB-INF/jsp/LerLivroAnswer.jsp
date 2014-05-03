<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String idLivro = (String) session.getAttribute("idLivro"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="refresh" content="0;url=10.42.0.1:8080/livros/<% out.write(idLivro);%>.html" />

<title>Insert title here</title>
</head>
<body>

</body>
</html>