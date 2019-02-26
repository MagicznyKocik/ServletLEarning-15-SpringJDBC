<%--
  Created by IntelliJ IDEA.
  User: azlup
  Date: 22.02.2019
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Library</title>
  </head>
  <body>
    <h1>Library Viewer</h1>
      <form action="BookServlet" method="post">
        <input type="text" placeholder="ISBN" name="isbn">
        <br/>
        <input type="text" placeholder="Title" name="title">
        <br/>
        <input type="text" placeholder="Description" name="description">
        <br/><hr/>
        <b>Add</b> : <input type="radio" name="option" value="create">
        <br/>
        <b>Search</b> : <input type="radio" name="option" value="read">
        <br/>
        <b>Update</b> : <input type="radio" name="option" value="update">
        <br/>
        <b>Delete</b> : <input type="radio" name="option" value="delete">
        <hr/><br/>
        <input type="submit" value="Submit">
      </form>
  </body>
</html>
