<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta charset="utf-8" />
    <title>Cadastro de Cliente</title>
    <link rel="stylesheet" type="text/css" media="screen" href="./css/CadastrarCliente.css" />
</head>

<body>

<body>
  
  <form action="${pageContext.request.contextPath}/cadastrarCliente"
                  method="post">
	    <h1>Cadastro de Cliente</h1>
	    
    <div class="contentform">

	    <h2> Identificação do Cliente </h2>

    	<div class="leftcontact">
			<div class="form-group">
			<p>Nome<span>*</span></p>
			<input type="text" name="nome" id="nome" required="true" />
       </div> 

            <div class="form-group">
            <p>Sobrenome <span>*</span></p>
				<input type="text" name="sobrenome" id="sobrenome" required="true" />
			</div>

			<div class="form-group">
			<p>Sexo <span>*</span></p>
				<select type="text" name="sexo" required="true" />
				<option value="Selecione"> Selecione </option>
				<option value="Masculino"> Masculino </option>
				<option value="Feminino"> Feminino </option>
            	</select>
			</div>

			<div class="form-group">
			<p>CPF <span>*</span></p>
				<input type="text" name="cpf" id="cpf" required="true" />
			</div>

			

			<div class="form-group">
			<p>Data de Nascimento<span>*</span></p>
				<input type="text" name="dtNascimento" id="datanascimento" required="true" />
			</div>


	</div>

		    <h2 align="center" class="formatatit"> Endereço </h2>

	<div class="rightcontact">	

			<div class="form-group">
			<p>Rua <span>*</span></p>
				<input type="text" name="rua" id="rua" required="true" />
			</div>	

			<div class="form-group">
			<p>Numero: <span>*</span></p>	
				<input type="text" name="numero" id="numero" required="true" />
			</div> 

			<div class="form-group">
			<p>Estado <span>*</span></p>
				<select type="text" name="estado" required="true" />
					<option value="Selecione"> Selecione</option>
					<option value="AC">Acre</option>
					<option value="AL">Alagoas</option>
					<option value="AP">Amapá</option>
					<option value="AM">Amazonas</option>
					<option value="BA">Bahia</option>
					<option value="CE">Ceará</option>
					<option value="DF">Distrito Federal</option>
					<option value="ES">Espírito Santo</option>
					<option value="GO">Goiás</option>
					<option value="MA">Maranhão</option>
					<option value="MT">Mato Grosso</option>
					<option value="MS">Mato Grosso do Sul</option>
					<option value="MG">Minas Gerais</option>
					<option value="PA">Pará</option>
					<option value="PB">Paraíba</option>
					<option value="PR">Paraná</option>
					<option value="PE">Pernambuco</option>
					<option value="PI">Piauí</option>
					<option value="RJ">Rio de Janeiro</option>
					<option value="RN">Rio Grande do Norte</option>
					<option value="RS">Rio Grande do Sul</option>
					<option value="RO">Rondônia</option>
					<option value="RR">Roraima</option>
					<option value="SC">Santa Catarina</option>
					<option value="SP">São Paulo</option>
					<option value="SE">Sergipe</option>
					<option value="TO">Tocantins</option>
            	</select>
			</div>

			<div class="form-group">
			<p>Bairro: <span>*</span></p>	
                <input type="text" name="bairro" id="bairro" required="true" />
			</div>

			<div class="form-group">
			<p>Cidade: <span>*</span></p>	
                <input type="text" name="cidade" id="cidade" required="true" />
			</div>

			<div class="form-group">
			<p>CEP: <span>*</span></p>	
                <input type="text" name="cep" id="cep" required="true" />
			</div>
	</div>
	</div>

<button type="submit" class="botao-cadastrar">Cadastrar</button>
<button type="reset" class="botao-apagar">Limpar</button>

</form>	

  
</body>
</html>


    <div class="navbar">
        <a style="text-decoration: none" href="PaginaPrincipal.html"><h1 class="title"></h1></a>
        
        <div class="menu">
            <ul>
                <li><a class="button" id="home" href="PaginaPrincipal.html">Retornar</a></li>
            </ul>
    </div>

</body>
</html>