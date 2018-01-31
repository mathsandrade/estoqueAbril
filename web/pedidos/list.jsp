<%-- 
    Document   : list
    Created on : 29/01/2018, 23:29:48
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
        <title>Pedidos - Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron" style="background-image: linear-gradient(to top, #dfe9f3 0%, white 100%); border-bottom: 2px solid; border-bottom-color: black">
                <h1 style="text-align: center; font-family: Pacifico, cursive;">Pedidos</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/pedidos?a=new">Novo</a>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nome do Cliente</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Nome do Produto</th>
                        <th>Preço do Produto(R$)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${ pageContext.request.getAttribute(\"pedidos\") }" >
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/pedidos?a=edit&pedido.id=${pedido.id}">${pedido.cliente.nome}</a></td>
                            <td>${pedido.cliente.telefone}</td>
                            <td>${pedido.cliente.email}</td>
                            <td>${pedido.produto.nome}</td>
                            <td>${pedido.produto.preco}</td>
                        </tr>                   
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
