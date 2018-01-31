<%-- 
    Document   : index
    Created on : 30/01/2018, 00:21:52
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <%@include file="assets.jsp" %>
        <title>Bem Vindo</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <div class="container-fluid">
            <h3 class="text-center">Bem vindo ao sistema criado como resolução do teste para o processo seletivo.</h3>
            <div class="row">
                <div class="col-md-2 col-md-offset-5"><img src="images/Logotipo_da_Editora_Abril.png" class="img-responsive" alt="logo editora abril"></div>
            </div>
        </div>
    </body>
</html>
