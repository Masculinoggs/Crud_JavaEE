<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Cadastro Usuário</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="estilo.css">

</head>
<body>
	<div class="box">
		<form name="frmContato" action="update">
			<fieldset>
				<legend>
					<b>Editar Usuário</b>
				</legend>
				<br>

				<div class="inputBox">
					<label for="">Id</label> <input type="text" name="iduse"
						class="inputUser" readonly
						value="<%out.print(request.getAttribute("iduse"));%>">
				</div>
				<br> <br>

				<div class="inputBox">
					<label for="nome">Nome</label><br> <br> <input
						id="nome_cad" type="text" name="nome" class="inputUser"
						value="<%out.print(request.getAttribute("nome"));%>">
				</div>
				<br> <br>

				<div class="inputBox">
					<label for="fone">Telefone</label><br> <br> <input
						id="telefone" type="text" name="fone" class="inputUser"
						value="<%out.print(request.getAttribute("fone"));%>">
				</div>
				<br> <br>


				<div class="inputBox">
					<label for="email">Email</label><br> <br> <input
						id="email" type="text" name="email" class="inputUser"
						value="<%out.print(request.getAttribute("email"));%>">
				</div>
				<br> <br>
				
				<div class="inputBox">
					<label for="senha">Senha</label><br> <br> <input
						id="senha" type="text" name="senha" class="inputUser"
						value="<%out.print(request.getAttribute("senha"));%>">
				</div>
				<br> <br>

				<div class="btn">
					<input type="button" value="Salvar" class="Botao1"
						onclick="validar()">
				</div>
			</fieldset>
		</form>
	</div>
	<script src="scripts/validar.js"></script>
</body>
</html>