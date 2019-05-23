<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Detalhes do Usuario</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/DetalheUsuario.css" />
    </head>
    <body>

        <form>
            <h1>Detalhes do Usuario</h1>

            <div class="contentform">

                <h2> Identificação do Usuario </h2>

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
                        <p>Funcao <span>*</span></p>
                        <select type="text" name="funcao" required="true" />
                        <option value="Teste"> Teste </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Admissão: <span>*</span></p>	
                        <input type="date" name="admissao" id="admissao" required="true" />
                    </div>
                </div>
            </div>

            <button type="submit" class="botao-alterar">Alterar Dados</button>
            <button type="submit" class="botao-excluir">Excluir Cadastro</button>

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
