<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de telefones</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
</head>
<body>
	
	<nav class="navbar navbar-dark bg-primary mb-5">
		<div class="container-fluid">
			<a class="navbar-brand">SCUP - Sistema de Cadatro de Usuários e Produtos</a>
			<div class="d-flex">
				<a class="nav-link" href="acessoliberado.jsp" style="color: #fff;">
					<i class="fas fa-home"></i> Home
				</a>
				<a class="nav-link" href="cadastroProduto.jsp" style="color: #fff;">
					<i class="fas fa-box-open"></i> Produto
				</a>
				<a class="nav-link" href="index.jsp" style="color: #fff;">
					<i class="fas fa-sign-out-alt"></i> Sair
				</a>
			</div>
		</div>
	</nav>
	
	<center>
		<h1>Cadastro de Telefones</h1>
		<h4 style="color: orange;">${msg }</h4>
	</center>
	
	<div class="container mt-5">
		<form action="salvarTelefones" method="post" id="formTelefone">
			
			<div class="mb-3 row">
				<label for="id" class="col-sm-2 col-form-label">User:</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="user" value="${userEscolhido.id }" readonly="readonly">
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="nome" class="col-sm-2 col-form-label">Nome:</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="nome" name="nome" value="${userEscolhido.nome }" readonly="readonly">
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="numero" class="col-sm-2 col-md-2 col-form-label">Número:</label>
				<div class="col-sm-5 col-md-5">
					<input class="form-control" type="text" id="numero" name="numero" value="" >
				</div>
			
				<div class="col-sm-5 col-md-5">
					<select id="tipo" name="tipo" style="width: 335px;">
						<option>Casa</option>
						<option>Celular</option>
						<option>Contato</option>
					</select>
				</div>
			</div>
			
			<div class="mb-3 row">
				<button type="submit" class="btn btn-primary" style="width: 10%; margin: 0px 5px 0px 765px;">Salvar</button>
				<input type="submit" class="btn btn-primary" value="Voltar" style="width: 10%; margin: -38px 5px 0px 895px;" 
					onclick="document.getElementById('formTelefone').action = 'salvarTelefones?acao=voltar'">
			</div>
			
		</form>
	</div>
	
	<div class="container">
		<center>
			<h2 class="mt-5 mb-5">Telefones cadastrados</h2>
		</center>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Número</th>
					<th>Tipo</th>
					<th class="text-center">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fone" items="${telefones }">
					<tr>
						<td style="width: 5%;">${fone.id }</td>
						<td style="width: 30%;">${fone.numero }</td>
						<td style="width: 30%;">${fone.tipo }</td>
						<td style="width: 5%%;" class="text-center">
							<a href="salvarTelefones?user=${fone.usuarioId }&acao=deleteFone&foneId=${fone.id }" 
								data-bs-toggle="tooltip" title="Remover" onclick="return confirm('Confirmar a exclusão?');">								
								<i class="fas fa-trash-alt"></i>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>