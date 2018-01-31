<%-- 
    Document   : list
    Created on : 29/01/2018, 23:30:10
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
        <title>Produtos - Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron" style="background-image: linear-gradient(to top, #dfe9f3 0%, white 100%); border-bottom: 2px solid; border-bottom-color: black">
                <h1 style="text-align: center; font-family: Pacifico, cursive;">Produtos</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/produtos?a=new">Novo</a>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Preço(R$)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${ pageContext.request.getAttribute(\"produtos\") }" >
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/produtos?a=edit&produto.id=${produto.id}">${produto.nome}</a></td>
                            <td>${produto.descricao}</td>
                            <td>${produto.preco}</td>
                        </tr>                   
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>