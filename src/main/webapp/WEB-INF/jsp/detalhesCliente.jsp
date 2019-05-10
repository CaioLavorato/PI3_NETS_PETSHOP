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

        <form>
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
                        <p>RG <span></span></p>
                        <input type="text" name="rg" id="rg"/>
                    </div>

                    <div class="form-group">
                        <p>Data de Nascimento<span>*</span></p>
                        <input type="text" name="dtNascimento" id="datanascimento" required="true" value="${cliente.dtNascimento}" />
                    </div>


                </div>

                <h2 align="center" class="formatatit"> Endereço </h2>

                <div class="rightcontact">	

                    <div class="form-group">
                        <p>Rua <span>*</span></p>
                        <input type="text" name="rua" id="rua" required="true" value="${endereco.rua}"/>
                    </div>	

                    <div class="form-group">
                        <p>Nï¿½mero: <span>*</span></p>	
                        <input type="text" name="numero" id="numero" required="true" value="${endereco.numero}"/>
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

            <button type="submit" class="botao-alterar">Alterar Dados</button>


        </form>	

        <form class="botaoExcluir" action="${pageContext.request.contextPath}/excluir-cliente"
              accept-charset="" <input type="hidden" name="id_livro" value="${cliente.id}">
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