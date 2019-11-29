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
        	<a href="new">Add New Imovel</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Imovel</a>
        	
        </h2>
	</center>
    <div align="center">
    
		<c:if test="${imovel != null}">
			<form action="update" method="post">
        </c:if>
        
        <c:if test="${imovel == null}">
			<form action="insert" method="post">
        </c:if>
        
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${imovel != null}">
            			Edit Imovel
            		</c:if>
            		<c:if test="${imovel == null}">
            			Add New Imovel
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${imovel != null}">
        			<input type="hidden" name="id" value="<c:out value='${imovel.id}' />" />
        		</c:if>
        	<tr>
                <th>Imovel Id: </th>
                <td>
                	<input type="int" name="id" size="45"
                			value="<c:out value='${imovel.id}' />"
                		/>
                </td>
            </tr>            
            <tr>
                <th>Imovel Preço: </th>
                <td>
                	<input type="float" name="preco" size="45"
                			value="<c:out value='${imovel.preco}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Imovel Endereco: </th>
                <td>
                	<input type="text" name="endereco" size="45"
                			value="<c:out value='${imovel.endereco}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Imovel Status : </th>
                <td>
                	<input type="text" name="endereco" size="45"
                			value="<c:out value='${imovel.status}' />"
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
