<%-- 
    Document   : list
    Created on : 29/01/2018, 23:29:31
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
        <title>Clientes - Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron" style="background-image: linear-gradient(to top, #dfe9f3 0%, white 100%); border-bottom: 2px solid; border-bottom-color: black">
                <h1 style="text-align: center; font-family: Pacifico, cursive;">Clientes</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/clientes?a=new">Novo</a>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Telefone</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${ pageContext.request.getAttribute(\"clientes\") }" >
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/clientes?a=edit&cliente.id=${cliente.id}">${cliente.nome}</a></td>
                            <td>${cliente.email}</td>
                            <td>${cliente.telefone}</td>
                        </tr>                   
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
