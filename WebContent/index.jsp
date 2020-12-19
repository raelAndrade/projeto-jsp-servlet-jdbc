<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

<%-- 	<c:out value="${ 'Bem vindo ao jstl' }" /> --%>
<%-- 	<c:import var="data" url="https://www.google.com.br" /> --%>
<%-- 	<c:out value="${data }" /> --%>
		
<%-- 	<c:set var="data" scope="page" value="${500 * 6}" /> --%>
<%-- 	<c:remove var="data" /> --%>
<%-- 	<c:out value="${data }" /> --%>

<%-- 	<c:catch var="erro"> --%>
<%-- 		<%= 100 / 0 %> --%>
<%-- 	</c:catch> --%>
	
<%-- 	<c:if test="${erro != null }"> --%>
<%-- 		${erro.message } --%>
<%-- 	</c:if> --%>

<%-- 	<c:set var="numero" value="${100 / 3 }" /> --%>
	
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${numero > 50 }"> --%>
<%-- 			<c:out value="${'Maior que 50' }" /> --%>
<%-- 		</c:when> --%>
		
<%-- 		<c:when test="${numero < 50 }"> --%>
<%-- 			<c:out value="${'Menor que 50' }" /> --%>
<%-- 		</c:when> --%>
		
<%-- 		<c:otherwise> --%>
<%-- 			<c:out value="${'Não encontrou o valor correto' }" /> --%>
<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
	
<%-- 	<c:forEach var="n" begin="1" end="${numero }"> --%>
<%-- 		Item : ${n } --%>
<%-- 	</c:forEach> --%>

<%-- 	<c:forTokens items="Israel-Goncalves-de-Andrade" delims="-" var="nome"> --%>
<%-- 		Nome: <c:out value="${nome }" /> --%>
<!-- 		<br /> -->
<%-- 	</c:forTokens> --%>

<%-- 	<c:url value="/acessoliberado.jsp" var="acesso"> --%>
<%-- 		<c:param name="param1" value="111" /> --%>
<%-- 		<c:param name="param2" value="222" /> --%>
<%-- 	</c:url> --%>
<%-- 	${acesso } --%>
	
<%-- 	<c:if test="${numero >= 50 }"> --%>
<%-- 		<c:redirect url="http://www.globo.com.br" /> --%>
<%-- 	</c:if> --%>
	
<%-- 	<c:if test="${numero < 50 }"> --%>
<%-- 		<c:redirect url="http://www.cnnbrasil.com.br" /> --%>
<%-- 	</c:if> --%>
	
	<div class="login-page">
		<center>
			<h4>Projeto Didático</h4>
			<br />
			<h5>JSP + Servlet + JDBC</h5>
			<br />
			<span>Usuário: <b>admin</b> e a Senha: <b>admin</b></span>
			<br />
		</center>
		<br />
  		<div class="form">
			<form class="login-form" action="LoginServlet" method="post">
				<label for="login">Login</label>
				<input type="text" id="login" name="login">
				<br />
				<label for="senha">Senha</label>
				<input type="password" id="senha" name="senha">
				<br />
				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>
	
</body>
</html>