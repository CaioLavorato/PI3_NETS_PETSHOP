<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
        <meta charset="utf-8" />
        <title>Cadastro de Usuario</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/CadastrarUsuario.css" />
    </head>

    <body>

        <form action="${pageContext.request.contextPath}/cadastrarUsuario" method="post">
            <h1>Cadastro de Usuario</h1>

            <div class="contentform">

                <h2> Identificação do Usuário </h2>

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
                        <p>Telefone <span>*</span></p>
                        <input type="text" name="telefone" id="telefone" required="true" />
                    </div>



                </div>

                <h2 align="center" class="formatatit"> Setor </h2>

                <div class="rightcontact">	

                    <div class="form-group">
                        <p>Função <span>*</span></p>
                        <select name="funcao" required="true" />
                        <c:forEach items="${listaFuncao}" var ="funcao">
                            <option value="${funcao.nomeFuncao}">${funcao.nomeFuncao}</option>
                        </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Admissão: <span>*</span></p>	
                        <input type="date" name="dt_admissao" id="dt_admissao" required="true" />
                    </div>
                </div>
            </div>

            <button type="submit" class="botao-cadastrar">Cadastrar</button>
            <button type="reset" class="botao-apagar">Limpar</button>


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
