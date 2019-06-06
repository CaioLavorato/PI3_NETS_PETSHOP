<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Detalhe do Cliente</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/DetalheCliente.css" />
    </head>

    <body>

    <body>

        <form action="${pageContext.request.contextPath}/atualizarCliente"
              method="post" >
            <h1>Detalhes do Cliente</h1>

            <div class="contentform">

                <h2> Identificação do Cliente </h2>

                <div class="leftcontact">
                    <div class="form-group">
                        <p>Nome<span>*</span></p>
                        <input type="text" name="nome" id="nome" required="true" value="${cliente.nome}"/>
                    </div> 

                    <div class="form-group">
                        <p>Sobrenome <span>*</span></p>
                        <input type="text" name="sobrenome" id="sobrenome" required="true" value="${cliente.sobrenome}"/>
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
                        <input type="text" name="cpf" id="cpf" required="true" value="${cliente.cpf}" />
                    </div>

                    <div class="form-group">
                        <p>Data Nasc. <span>*</span></p>	
                        <input type="date" name="data" id="dtNascimento" required="true" value="${cliente.dtNascimento}"/>
                    </div>


                </div>

                <h2 align="center" class="formatatit"> Endereço </h2>

                <div class="rightcontact">	

                    <div class="form-group">
                        <p>Rua <span>*</span></p>
                        <input type="text" name="rua" id="rua" required="true" value="${endereco.rua}"/>
                    </div>	

                    <div class="form-group">
                        <p>Numero: <span>*</span></p>	
                        <input type="text" name="numero" id="numero" required="true" value="${endereco.numero}"/>
                    </div> 

                    <div class="form-group">
                        <p>Estado <span>*</span></p>
                        <select type="text" name="estado" required="true" />
                        <option value="Selecione"> Selecione</option>
                        <option value="AC" ${endereco.estado == "AC" ? "selected" : ""}>Acre</option>
                        <option value="AL" ${endereco.estado == "AL" ? "selected" : ""}>Alagoas</option>
                        <option value="AP" ${endereco.estado == "AP" ? "selected" : ""}>Amapá</option>
                        <option value="AM" ${endereco.estado == "AM" ? "selected" : ""}>Amazonas</option>
                        <option value="BA" ${endereco.estado == "BA" ? "selected" : ""}>Bahia</option>
                        <option value="CE" ${endereco.estado == "CE" ? "selected" : ""}>Ceará</option>
                        <option value="DF" ${endereco.estado == "DF" ? "selected" : ""}>Distrito Federal</option>
                        <option value="ES" ${endereco.estado == "ES" ? "selected" : ""}>Espírito Santo</option>
                        <option value="GO" ${endereco.estado == "GO" ? "selected" : ""}>Goiás</option>
                        <option value="MA" ${endereco.estado == "MA" ? "selected" : ""}>Maranhão</option>
                        <option value="MT" ${endereco.estado == "MT" ? "selected" : ""}>Mato Grosso</option>
                        <option value="MS" ${endereco.estado == "MS" ? "selected" : ""}>Mato Grosso do Sul</option>
                        <option value="MG" ${endereco.estado == "MG" ? "selected" : ""}>Minas Gerais</option>
                        <option value="PA" ${endereco.estado == "PA" ? "selected" : ""}>Pará</option>
                        <option value="PB" ${endereco.estado == "PB" ? "selected" : ""}>Paraíba</option>
                        <option value="PR" ${endereco.estado == "PR" ? "selected" : ""}>Paraná</option>
                        <option value="PE" ${endereco.estado == "PE" ? "selected" : ""}>Pernambuco</option>
                        <option value="PI" ${endereco.estado == "PI" ? "selected" : ""}>Piauí</option>
                        <option value="RJ" ${endereco.estado == "RJ" ? "selected" : ""}>Rio de Janeiro</option>
                        <option value="RN" ${endereco.estado == "RN" ? "selected" : ""}>Rio Grande do Norte</option>
                        <option value="RS" ${endereco.estado == "RS" ? "selected" : ""}>Rio Grande do Sul</option>
                        <option value="RO" ${endereco.estado == "RO" ? "selected" : ""}>Rondônia</option>
                        <option value="RR" ${endereco.estado == "RR" ? "selected" : ""}>Roraima</option>
                        <option value="SC" ${endereco.estado == "SC" ? "selected" : ""}>Santa Catarina</option>
                        <option value="SP" ${endereco.estado == "SP" ? "selected" : ""}>São Paulo</option>
                        <option value="SE" ${endereco.estado == "SE" ? "selected" : ""}>Sergipe</option>
                        <option value="TO" ${endereco.estado == "TO" ? "selected" : ""}>Tocantins</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Bairro: <span>*</span></p>	
                        <input type="text" name="bairro" id="bairro" required="true" value="${endereco.bairro}"/>
                    </div>

                    <div class="form-group">
                        <p>Cidade: <span>*</span></p>	
                        <input type="text" name="cidade" id="cidade" required="true" value="${endereco.cidade}"/>
                    </div>

                    <div class="form-group">
                        <p>CEP: <span>*</span></p>	
                        <input type="text" name="cep" id="cep" required="true" value="${endereco.cep}" />
                    </div>
                </div>
            </div>
            <input type="hidden" name="id" value="${cliente.id}">
            <input type="hidden" name="idEndereco" value="${endereco.id}">
            <button type="submit" class="botao-alterar">Alterar Dados</button>


        </form>	

        <form class="botaoExcluir" action="${pageContext.request.contextPath}/excluirCliente"
              method="post">
            <input type="hidden" name="id" value="${cliente.id}">
            <button type="submit" class="botao-excluir">Excluir Cadastro</button>
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