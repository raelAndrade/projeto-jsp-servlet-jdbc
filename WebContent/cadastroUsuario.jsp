<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de usuário</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
	
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" integrity="sha512-bnIvzh6FU75ZKxp0GXLH9bewza/OIw6dLVh9ICg0gogclmYGguQJWl8U30WpbsGTqbIiAwxTsbe76DErLq5EDQ==" crossorigin="anonymous"></script> -->
	<script src="resources/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	
	<nav class="navbar navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand">SCUP - Sistema de Cadatro de Usuários e Produtos</a>
			<div class="d-flex">
				<a class="nav-link" href="acessoliberado.jsp" style="color: #fff;">
					<i class="fas fa-home"></i> Home
				</a>
				<a class="nav-link" href="salvarProduto" style="color: #fff;">
					<i class="fas fa-box-open"></i> Produto
				</a>
				<a class="nav-link" href="index.jsp" style="color: #fff;">
					<i class="fas fa-sign-out-alt"></i> Sair
				</a>
			</div>
		</div>
	</nav>
	
	<div class="container">
		
		<div class="text-center mb-5 mt-5">
			<h1>Cadastro de Usuário</h1>
			<h4 style="color: red;">${msg }</h4>
		</div>
	
		<form action="salvarUsuario" method="post" id="formUser" 
			onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data">
			
			<div class="mb-3 row">
				<label for="id" class="col-sm-1 col-form-label">Código:</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" id="id" name="id" value="${user.id }" readonly="readonly" >
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="nome" class="col-sm-1 col-form-label">Nome:</label>
				<div class="col-sm-11">
					<input class="form-control" type="text" id="nome" name="nome" value="${user.nome }" placeholder="Informe o nome do usuário" >
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="login" class="col-sm-1 col-form-label">Login:</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="login" name="login" value="${user.login }" placeholder="Informa o login" >
				</div>
				<label for="senha" class="col-sm-1 col-form-label">Senha:</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="senha" name="senha" value="${user.senha }" >
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="senha" class="col-sm-1 col-form-label">Cep: </label>
				<div class="col-sm-3">
					<input class="form-control" name="cep" type="text" id="cep" size="10" maxlength="9" onblur="consultaCep();" value="${user.cep }" placeholder="Informe um CEP válido" />
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="senha" class="col-sm-1 col-form-label">Rua:</label> 
					<div class="col-sm-11">
						<input class="form-control" name="rua" type="text" id="rua" size="60" value="${user.rua }" />
					</div>
			</div>
			
			<div class="mb-3 row">
				<label for="senha" class="col-sm-1 col-form-label">Bairro:</label>
				<div class="col-sm-5"> 
					<input class="form-control" name="bairro" type="text" id="bairro" size="40" value="${user.bairro }" />
				</div>

				<label for="senha" class="col-sm-1 col-form-label">Cidade:</label>
				<div class="col-sm-5"> 
					<input class="form-control" name="cidade" type="text" id="cidade" size="40" value="${user.cidade }" />
				</div>
			</div>
			
			<div class="mb-3 row">

				<label for="senha" class="col-sm-1 col-form-label">Estado:</label> 
				<div class="col-sm-3">
					<input class="form-control" name="uf" type="text" id="uf" size="2" value="${user.uf }" />
				</div>

				<label for="senha" class="col-sm-1 col-form-label">IBGE:</label> 
				<div class="col-sm-3">
					<input class="form-control" name="ibge" type="text" id="ibge" size="8" value="${user.ibge }" />
				</div>
				
				<label for="senha" class="col-sm-1 form-check-label">Ativo:</label> 
				<div class="col-sm-3">
					<input class="form-check-input" type="checkbox" id="ativo" name="ativo" value="${user.ativo }" 
						<c:if test="${user.ativo == true}">checked="checked"</c:if> />
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="sexo" class="col-sm-1 col-form-label">Sexo:</label>
				
				<div class="col-sm-3">
					<input class="form-check-input sexo" type="radio" value="feminino" id="feminino" name="sexo" 
						<c:if test="${user.sexo eq 'feminino' }">checked="checked"</c:if> />
					<label class="form-check-label" for="feminino">Feminino</label>
				</div>
				
				<div class="col-sm-3">
					<input class="form-check-input sexo" type="radio" value="masculino" id="masculino" name="sexo" 
						<c:if test="${user.sexo eq 'masculino' }">checked="checked"</c:if> />
					<label class="form-check-label" for="masculino">Masculino</label>
				</div>
				
				<label for="perfil" class="col-sm-1 col-form-label">Perfil:</label>
				<div class="col-sm-3">
					<select class="form-select" id="perfil" name="perfil" style="width: 355px;">
						<option value="">-- SELECIONE --</option>
						<option value="administrador" <c:if test="${user.perfil eq 'administrador' }">selected</c:if>>Administrador</option>
						<option value="secretario" <c:if test="${user.perfil eq 'secretario' }">selected</c:if>>Secretário</option>
						<option value="gerente" <c:if test="${user.perfil eq 'gerente' }">selected</c:if>>Gerente</option>
						<option value="funcionario" <c:if test="${user.perfil eq 'funcionario' }">selected</c:if>>Funcionário</option>
					</select>
				</div>
				
			</div>
			
			<div class="mb-3 row">
				<label for="foto" class="col-sm-1 col-form-label">Foto:</label> 
				<div class="col-sm-5">
					<input class="form-control" name="foto" type="file" id="foto" />
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="curriculo" class="col-sm-1 col-form-label">Curriculo:</label> 
				<div class="col-sm-5">
					<input class="form-control" name="curriculo" type="file" id="curriculo" />
				</div>
			</div>

			<div class="mb-3 row">
				<button type="submit" class="btn btn-primary" style="width: 10%;margin: 0px 5px 0px 895px;">Salvar</button>
				<button type="submit" class="btn btn-primary" style="width: 10%;" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">Cancelar</button>
			</div>
			
		</form>
		
		<div class="container mt-5">
			<form action="pesquisaServlet" method="post">
				<div class="mb-3 row">
					<label for="descricaoConsulta" class="col-sm-1 col-form-label">Buscar</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" id="descricaoConsulta" name="descricaoConsulta" value="" >
					</div>
					<div class="col-sm-1">
						<button type="submit" class="btn btn-success">Consultar</button>
					</div>
				</div>
			</form>
		
		</div>
	
		<div class="container">
			<center>
				<h2 class="mt-5 mb-5">Usuários cadastrados</h2>
			</center>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Nome</th>
						<th>Foto</th>
						<th colspan="4" class="text-center">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${usuarios }">
						<tr>
							<td style="width: 5%;">${user.id }</td>
							<td style="width: 30%;"><c:out value="${user.nome }" /></td>
							<td style="width: 30%;">
								<c:choose>
									<c:when test="${user.fotoBase64Miniatura.isEmpty() == false }">
										<a href="salvarUsuario?acao=download&tipo=image&user=${user.id }" data-bs-toggle="tooltip" title="Foto">
											<img alt="Imagem do usuário" src="${user.fotoBase64Miniatura }" width="32px" height="32px">
										</a>
									</c:when>
									<c:otherwise>
										<i class="fas fa-user-times fa-2x" data-bs-toggle="tooltip" title="Sem foto"></i>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="salvarUsuario?acao=editar&user=${user.id }" data-bs-toggle="tooltip" title="Editar">
									<i class="fas fa-edit"></i>
								</a>
							</td>
							<td>
								<a href="salvarUsuario?acao=delete&user=${user.id }" data-bs-toggle="tooltip" title="Remover" 
									onclick="return confirm('Confirmar a exclusão?');">								
									<i class="fas fa-trash-alt"></i>
								</a>
							</td>
							<td>
								<a href="salvarTelefones?acao=addFone&user=${user.id }" data-bs-toggle="tooltip" title="Telefones">								
									<i class="fas fa-phone-square"></i>
								</a>
							</td>
							<td>
								<c:choose>
									<c:when test="${user.curriculoBase64.isEmpty() == false }">
										<a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id }" data-bs-toggle="tooltip" title="PDF">
											<i class="fas fa-file-pdf"></i>
										</a>
									</c:when>
									<c:otherwise> - </c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>		
				</tbody>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$('#ativo').on("change", function(e){
				e.preventDefault();
				
				if($('#ativo').is(":checked")){
					$(this).prop('checked', true);
					$(this).val(true);
				}else{
					$(this).prop('checked', false);
					$(this).val(false);
				}
				
			});
			
			$('.sexo').on('change', function(e){
				e.preventDefault();
				
				if($('#feminino').is(':checked')){
					$('#feminino').attr('checked', true);
					$('#masculino').attr('checked', false);
					$('#feminino').val('feminino');
					$('#masculino').val('');
				}else if($('#masculino').is(':checked')){
					$('#masculino').attr('checked', true);
					$('#feminino').attr('checked', false);
					$('#masculino').val('masculino');
					$('#feminino').val('');
				}
			});
			
		});
		
		function validarCampos(){
			
			if(document.getElementById("login").value == ''){
				alert('Informe o Login');
				return false;
				
			} else if(document.getElementById("senha").value == ''){
				alert('Informe a Senha');
				return false;
				
			} else if(document.getElementById("nome").value == ''){
				alert('Informe o nome');
				return false;
			}
			
			return true;
		}
		
		
		function consultaCep() {

			var cep = $("#cep").val();

			// Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

				if (!("erro" in dados)) {
					$("#rua").val(dados.logradouro);
					$("#bairro").val(dados.bairro);
					$("#cidade").val(dados.localidade);
					$("#uf").val(dados.uf);
					$("#ibge").val(dados.ibge);
				} else {
					limpa_formulário_cep();
					alert("CEP não encontrado.");
				}
				
			});
		}
		
		function limpa_formulário_cep() {
            // Limpa valores do formulário de cep.
            $("#rua").val("");
            $("#bairro").val("");
            $("#cidade").val("");
            $("#uf").val("");
            $("#ibge").val("");
        }
	</script>
</body>
</html>