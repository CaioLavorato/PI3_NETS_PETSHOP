<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Cadastro de Produto</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/CadastrarLivro.css" />
    </head>

    <body>

    <body>

        <form action="${pageContext.request.contextPath}/cadastrarProduto"
              method="post">
            <h1>Cadastro de Produto</h1>

            <div class="contentform">

                <h2 align="center"> Identificação do Produto </h2>

                <div class="leftcontact">
                    <div class="form-group">
                        <p>Categoria <span>*</span></p>
                        <select type="text" name="categoria" required="true" />
                        <c:forEach items="${categorias}" var="categoria">                                
                            <option value="${categoria.id}"> ${categoria.nome} </option>              
                        </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Nome do Produto<span>*</span></p>
                        <input type="text" name="nomeProduto" id="titulo" required="true" />
                    </div> 

                    <div class="form-group">
                        <p>Valor <span>*</span></p>
                        <input type="number" name="valor" id="valor" required="true" />
                    </div>

                    <p>Descrição <span>*</span></p>
                    <textarea name="descricao" required="true"> </textarea>	

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


