<%-- 
    Document   : form
    Created on : 29/01/2018, 23:29:57
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST" action="${pageContext.request.contextPath}/pedidos?a=${action}" >
    <input type="hidden" name="pedido.id" value="${pedido.id}">
    <div class="container-fluid"> 
        <div class="row">
            <div id="form_cliente">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="cliente.id">Cliente: </label>
                            <select class="form-control" name="cliente.id">
                                        <c:forEach var="cliente" items="${clientes}">
                                            <option value="${cliente.id}">${cliente.email}</option>
                                        </c:forEach>
                            </select>
                    </div>
                    <div class="col-sm-4">
                        <label for="produto.id">Produto: </label>
                            <select class="form-control" name="produto.id">
                                        <c:forEach var="produto" items="${produtos}">
                                            <option value="${produto.id}">${produto.nome}</option>
                                        </c:forEach>
                            </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-1" style="margin-top: 20px">
                <input type="submit" class="btn btn-success" name="commit" value="Salvar">
            </div>
            <div class="col-sm-1" style="margin-top: 20px">
                <c:if test="${action eq \"update\"}">
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/pedidos?a=delete&pedido.id=${pedido.id}">Deletar</a>
                </c:if>
            </div>
        </div>
    </div>
</form>