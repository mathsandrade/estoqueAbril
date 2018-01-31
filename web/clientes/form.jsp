<%-- 
    Document   : form
    Created on : 29/01/2018, 23:29:15
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST" action="${pageContext.request.contextPath}/clientes?a=${action}" >
    <input type="hidden" name="cliente.id" value="${cliente.id}">
    <div class="container-fluid"> 
        <div class="row">
            <div id="form_cliente">
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="cliente.nome">Nome: </label>
                        <input type="text" class="form-control" name="cliente.nome" value="${cliente.nome}" required>
                    </div>
                    <div class="col-sm-4">
                        <label for="cliente.email">Email: </label><span style="color: red"> ${mensagem}</span>
                        <input type="email" id="blockemail" class="form-control" name="cliente.email" value="${cliente.email}" required>
                    </div>
                    <div class="col-sm-4">
                        <label for="cliente.telefone">Telefone: </label>
                        <input type="text" class="form-control phone" name="cliente.telefone" value="${cliente.telefone}" required>
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
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/clientes?a=delete&cliente.id=${cliente.id}">Deletar </a>
                    <script>
                    $(document).ready(function() {
                        document.getElementById('blockemail').readOnly = true;
                    });
                    </script>
                </c:if>    
            </div>
        </div>
    </div>
</form>
