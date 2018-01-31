<%-- 
    Document   : nav
    Created on : 30/01/2018, 00:18:04
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">Home</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/clientes">Clientes</a></li>
      <li><a href="${pageContext.request.contextPath}/produtos">Produtos</a></li>
      <li><a href="${pageContext.request.contextPath}/pedidos">Pedidos</a>
    </ul>
  </div>
</nav>
