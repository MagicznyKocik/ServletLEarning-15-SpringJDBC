<%@ page import="pl.adamLupinski.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: azlup
  Date: 22.02.2019
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Book book = (Book)request.getAttribute("book"); %>
<html>
<head>
    <title>Book info</title>
</head>
<body>

<h1> You choose <%=request.getAttribute("option")%> </h1>

<h1> Book title: <%= book.getTitle() %></h1>
<h1> Book ISBN: <%= book.getIsbn() %></h1>
<h1> Book description: </h1>
<h2><%= book.getDescription() %></h2>
</body>
</html>
