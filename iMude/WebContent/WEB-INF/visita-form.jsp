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
    
		<c:if test="${user != null}">
			<form action="update" method="post">
        </c:if>
        
        <c:if test="${user == null}">
			<form action="insert" method="post">
        </c:if>
        
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${user != null}">
            			Edit Visita
            		</c:if>
            		<c:if test="${user == null}">
            			Add New Visita
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${user != null}">
        			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        		</c:if>            
            <tr>
                <th>Visita Data: </th>
                <td>
                	<input type="text" name="data" size="45"
                			value="<c:out value='${visita.data}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Visita Horario: </th>
                <td>
                	<input type="text" name="horario" size="45"
                			value="<c:out value='${visita.horario}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        
        </form>
    </div>	
</body>
</html>
