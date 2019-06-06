<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Detalhes do Usuario</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/DetalheUsuario.css" />
    </head>
    <body>

        <form action="${pageContext.request.contextPath}/atualizarUsuario"
              method="post" >
            <h1>Detalhes do Usuario</h1>

            <div class="contentform">

                <h2> Identificação do Usuario </h2>

                <div class="leftcontact">
                    <div class="form-group">
                        <p>Nome<span>*</span></p>
                        <input type="text" name="nome" id="nome" required="true" value="${usuario.nome}"/>
                    </div> 

                    <div class="form-group">
                        <p>Sobrenome <span>*</span></p>
                        <input type="text" name="sobrenome" id="sobrenome" required="true" value="${usuario.sobrenome}" />
                    </div>

                    <div class="form-group">
                        <p>Sexo <span>*</span></p>
                        <select type="text" name="sexo" required="true" />
                        <option value="Selecione"> Selecione </option>
                        <option value="Masculino" ${"Masculino" == usuario.sexo ? "selected" : "" }> Masculino </option>
                        <option value="Feminino"  ${"Feminino" == usuario.sexo ? "selected" : "" }> Feminino </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Telefone <span>*</span></p>
                        <input type="text" name="telefone" id="telefone" required="true" value="${usuario.telefone}" />
                    </div>


                </div>

                <h2 align="center" class="formatatit"> Setor </h2>

                <div class="rightcontact">	

                    <div class="form-group">
                        <p>Função <span>*</span></p>
                        <select name="funcao" required="true" />
                        <c:forEach items="${listaFuncao}" var ="funcao">
                            <option value="${funcao.id}"
                                    ${funcao.id == usuario.funcao.id ? "selected" : "" }
                                    >${funcao.nomeFuncao}</option>
                            \
                        </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Admissão: <span>*</span></p>	
                        <input type="date" name="dt_admissao" id="dt_admissao" required="true" value="${usuario.dtAdmissao}"/>
                    </div>
                </div>
            </div>

            <input type="hidden" name="id_usuario" value="${usuario.id}">
            <input type="hidden" name="id_funcao" value="${funcao.id}">
            <button type="submit" class="botao-alterar">Alterar Dados</button>
            <form class="botaoExcluir" action="${pageContext.request.contextPath}/excluirUsuario"
                  method="post">
                <input type="hidden" name="id_usuario" value="${usuario.id}">
                <button type="submit" class="botao-excluir">Excluir Cadastro</button>
            </form>
        </form>	

        <div class="navbar">
            <a style="text-decoration: none" href="PaginaPrincipal.html"><h1 class="title"></h1></a>

            <div class="menu">
                <ul>
                    <li><a class="button" id="home" href="PaginaPrincipal.html">Retornar</a></li>
                </ul>
            </div>
        </div>
    </body>
</html>
