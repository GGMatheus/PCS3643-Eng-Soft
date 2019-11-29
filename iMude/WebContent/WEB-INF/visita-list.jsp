<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
        <h2>
        	<a href="new">Add New Visita</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Visitas</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Visitas</h2></caption>
            <tr>
                <th>ID</th>
                <th>Data</th>
                <th>Horario</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="visita" items="${listVisita}">
                <tr>
                    <td><c:out value="${visita.id}" /></td>
                    <td><c:out value="${visita.data}" /></td>
                    <td><c:out value="${visita.horario}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${visita.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${visita.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
