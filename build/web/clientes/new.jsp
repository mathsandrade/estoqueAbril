<%-- 
    Document   : new
    Created on : 29/01/2018, 23:28:54
    Author     : Matheus
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../assets.jsp" %>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron" style="background-image: linear-gradient(to top, #dfe9f3 0%, white 100%); border-bottom: 2px solid; border-bottom-color: black">
                <h1 style="text-align: center; font-family: Pacifico, cursive;">Cadastro de Clientes</h1>
            </div>
            <%@include file="form.jsp" %>
        </div>
    </body>
</html>
