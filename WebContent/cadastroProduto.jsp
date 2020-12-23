<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de produtos</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
	
	<script src="resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="resources/js/jquery.maskMoney.min.js" type="text/javascript"></script>
	
</head>
<body>

</head>
<body>

	<nav class="navbar navbar-dark bg-primary mb-5">
		<div class="container-fluid">
			<a class="navbar-brand">SCUP - Sistema de Cadatro de Usuários e Produtos</a>
			<div class="d-flex">
				<a class="nav-link" href="acessoliberado.jsp" style="color: #fff;">
					<i class="fas fa-home"></i> Home
				</a>
				<a class="nav-link" href="salvarUsuario" style="color: #fff;">
					<i class="fas fa-users"></i> Usuário
				</a>
				<a class="nav-link" href="index.jsp" style="color: #fff;">
					<i class="fas fa-sign-out-alt"></i> Sair
				</a>
			</div>
		</div>
	</nav>

	<center>
		<h1>Cadastro de Produto</h1>
		<h4 style="color: red;">${msg }</h4>
	</center>
	
	
	<div class="container mt-5">
		<form action="salvarProduto" method="post" id="formProduto">
			
			<div class="mb-3 row">
				<label for="id" class="col-sm-1 col-form-label">Código:</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="id" value="${produto.id }" readonly="readonly">
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="descricao" class="col-sm-1 col-form-label">Descrição:</label>
				<div class="col-sm-11">
					<input class="form-control" type="text" id="descricao" name="descricao" value="${produto.descricao }" >
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="quantidade" class="col-sm-1 col-form-label">Quantidade:</label>
				<div class="col-sm-3">
					<input class="form-control" type="text" id="quantidade" name="quantidade" value="${produto.quantidade }" maxlength="6" />
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="valor" class="col-sm-1 col-form-label">Valor:</label>
				<div class="col-sm-3">
					<input class="form-control" type="text" id="valor" name="valor" value="${produto.valorEmTexto }" data-thousands="." data-decimal="," />
				</div>
			</div>
			
			<div class="mb-3 row">
				<button type="submit" class="btn btn-primary" style="width: 10%; margin: 0px 5px 0px 895px;">Salvar</button>
				<button type="submit" class="btn btn-primary" style="width: 10%;" onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'">Cancelar</button>
			</div>
			
		</form>
	</div>
	
	<div class="container">
		<center>
			<h2 class="mt-5 mb-5">Produtos cadastrados</h2>
		</center>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Descricao</th>
					<th>Quantidade</th>
					<th>Valor</th>
					<th colspan="2" class="text-center">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produto" items="${produtos }">
					<tr>
						<td style="width: 5%;">${produto.id }</td>
						<td style="width: 30%;">${produto.descricao }</td>
						<td style="width: 30%;"><c:out value="${produto.quantidade }" /></td>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="2" value="${produto.valor }"></fmt:formatNumber>
						</td>
						<td style="width: 5%;">
							<a href="salvarProduto?acao=editar&produto=${produto.id }" data-bs-toggle="tooltip" title="Editar">
								<i class="fas fa-edit"></i>
							</a>
						</td>
						<td style="width: 5%;">
							<a href="salvarProduto?acao=delete&produto=${produto.id }" data-bs-toggle="tooltip" title="Remover"
								onclick="return confirm('Confirmar a exclusão?');">								
								<i class="fas fa-trash-alt"></i>
							</a>
						</td>
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		
		function validarCampos(){
			
			if(document.getElementById("descricao").value == ''){
				alert('Informe a descricao do produto');
				return false;
				
			} else if(document.getElementById("quantidade").value == ''){
				alert('Informe a quantidade');
				return false;
				
			} else if(document.getElementById("valor").value == ''){
				alert('Informe o valor');
				return false;
				
			}
			
			return true;
		}
		
	</script>
	
</body>

<script type="text/javascript">

	$(function(){
		$('#valor').maskMoney();
	});
	
	$(document).ready(function(){	
		$('#quantidade').keyup(function(){
			$('#quantidade').val(this.value.match(/[0-9]*/));
		});
	});
</script>
</html>