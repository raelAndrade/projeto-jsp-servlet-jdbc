<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Acesso Liberado</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
</head>
<body>

	<nav class="navbar navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand">SCUP - Sistema de cadatro de usuários e produtos</a>
			<div class="d-flex">
				<a class="nav-link" href="acessoliberado.jsp" style="color: #fff;">
					<i class="fas fa-home"></i> Home
				</a>
				<a class="nav-link" href="index.jsp" style="color: #fff;">
					<i class="fas fa-sign-out-alt"></i> Sair
				</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<center class="pt-5">
			<h1 class="mt-5 mb-5">Seja bem vindo ao sistema!</h1>
			<div class="row">
				<div class="col-sm-6" style="margin-left: 115px;">
					<a href="salvarUsuario">
						<i class="fas fa-users fa-7x" style="padding: 0px 30px 0px 0px;"></i>
						<h5>Cadastro de Usuários</h5>
					</a>
				</div>
				
				<div class="col-sm-6" style="margin-left: -240px;">
					<a href="salvarProduto">
						<i class="fas fa-box-open fa-7x"></i>
						<h5>Cadastro de Produtos</h5>
					</a>
				</div>
			</div>
		</center>
	</div>

</body>
</html>