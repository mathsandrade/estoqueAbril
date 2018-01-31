<%-- 
    Document   : form
    Created on : 29/01/2018, 23:30:15
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST" action="${pageContext.request.contextPath}/produtos?a=${action}" >
    <input type="hidden" name="produto.id" value="${produto.id}">
    <div class="container-fluid"> 
        <div class="row">
            <div id="form_cliente">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="produto.nome">Nome: </label><span style="color: red"> ${mensagem}</span>
                        <input type="text" id="blocknome" class="form-control" name="produto.nome" value="${produto.nome}" required>
                    </div>
                    <div class="col-sm-4">
                        <label for="produto.descricao">Descrição: </label>
                        <input type="text" class="form-control" name="produto.descricao" value="${produto.descricao}" required>
                    </div>
                    <div class="col-sm-4">
                        <label for="produto.valor">Preço: </label>
                        <input type="text" class="form-control money" name="produto.preco" value="${produto.preco}" required>
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
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/produtos?a=delete&produto.id=${produto.id}">Deletar </a>
                    <script>
                    $(document).ready(function() {
                        document.getElementById('blocknome').readOnly = true;
                    });
                    </script>
                </c:if>
            </div>
        </div>
    </div>
</form>
