<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Detalhes da Filial</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/DetalheFilial.css" />
    </head>

    <body>

    <body>

        <form action="${pageContext.request.contextPath}/atualizarFilial"
              method="post" >
            <h1>Detalhes da Filial</h1>

            <div class="contentform">

                <h2> Identificação da Filial </h2>

                <div class="leftcontact">
                    <div class="form-group">
                        <p>Nome Fantasia<span>*</span></p>
                        <input type="text" name="nomeFantasia" id="nomefantasia" required="true" value="${filial.nomeFantasia}" />
                    </div> 

                    <div class="form-group">
                        <p>CNPJ <span>*</span></p>
                        <input type="text" name="cnpj" id="cnpj" required="true" value="${filial.cnpj}" />
                    </div>

                    <div class="form-group">
                        <p>Email <span>*</span></p>
                        <input type="email" name="email" id="email" required="true" value="${filial.email}" />
                    </div>	

                    <div class="form-group">
                        <p>Telefone <span>*</span></p>
                        <input type="text" name="telefone" id="telefone" required="true" value="${filial.telefone}" />
                    </div>


                </div>

                <h2 align="center" class="formatatit"> Endereï¿½o </h2>

                <div class="rightcontact">

                    <div class="form-group">
                        <p>Rua <span>*</span></p>
                        <input type="text" name="rua" id="rua" required="true" value="${endereco.rua}"/>
                    </div>	

                    <div class="form-group">
                        <p>Cidade<span>*</span></p>	
                        <input type="text" name="cidade" id="cidade" required="true" value="${endereco.cidade}" />
                    </div> 

                    <div class="form-group">
                        <p>Número <span>*</span></p>	
                        <input type="text" name="numero" id="numero" required="true" value="${endereco.numero}" />
                    </div>

                    <div class="form-group">
                        <p>Bairro<span>*</span></p>	
                        <input type="text" name="bairro" id="bairro" required="true" value="${endereco.bairro}" />
                    </div>


                    <div class="form-group">
                        <p>CEP<span>*</span></p>	
                        <input type="text" name="cep" id="cep" required="true" value="${endereco.cep}" />
                    </div>

                    <div class="form-group">
                        <p>Estado <span>*</span></p>
                        <select type="text" name="estado" required="true" />
                        <option value="Selecione"> Selecione</option>
                        <option value="AC">Acre</option>
                        <option value="AL">Alagoas</option>
                        <option value="AP">Amapï¿½</option>
                        <option value="AM">Amazonas</option>
                        <option value="BA">Bahia</option>
                        <option value="CE">Cearï¿½</option>
                        <option value="DF">Distrito Federal</option>
                        <option value="ES">Espï¿½rito Santo</option>
                        <option value="GO">Goiï¿½s</option>
                        <option value="MA">Maranhï¿½o</option>
                        <option value="MT">Mato Grosso</option>
                        <option value="MS">Mato Grosso do Sul</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="PA">Parï¿½</option>
                        <option value="PB">Paraï¿½ba</option>
                        <option value="PR">Paranï¿½</option>
                        <option value="PE">Pernambuco</option>
                        <option value="PI">Piauï¿½</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="RN">Rio Grande do Norte</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="RO">Rondï¿½nia</option>
                        <option value="RR">Roraima</option>
                        <option value="SC">Santa Catarina</option>
                        <option value="SP">Sï¿½o Paulo</option>
                        <option value="SE">Sergipe</option>
                        <option value="TO">Tocantins</option>
                        </select>
                    </div>

                </div>
            </div>

            <input type="hidden" name="id_filial" value="${filial.id}">
            <input type="hidden" name="id_endereco" value="${endereco.id}">
            <button type="submit" class="botao-alterar">Alterar Dados</button>


        </form>	

        <form class="botaoExcluir" action="${pageContext.request.contextPath}/excluir-filial"
              method="post">
            <input type="hidden" name="id_filial" value="${filial.id}">
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