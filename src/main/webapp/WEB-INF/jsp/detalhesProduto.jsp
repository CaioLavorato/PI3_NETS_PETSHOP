<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Detalhe do Produto</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/DetalheLivro.css" />
    </head>

    <body>

    <body>

        <form class="formAletarar" action="${pageContext.request.contextPath}/atualizarProduto"
              method="post" >
            <h1>Detalhe do Produto</h1>

            <div class="contentform">

                <h2 align="center"> Identificação do Produto </h2>

                <div class="leftcontact">
                    <div class="form-group">
                        <p>Categoria <span>*</span></p>
                        <select type="text" name="categoria" required="true" />

                        <c:forEach items="${categorias}" var="categoria">                                
                            <option  value="${categoria.id}" <c:if test="${categoria.id==produto.categoria.id}">selected</c:if> > ${categoria.nome} </option>              
                        </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <p>Nome do Produto<span>*</span></p>
                        <input type="text" name="titulo" id="titulo" required="true" value="${produto.nomeProduto}" />
                    </div> 

                    <div class="form-group">
                        <p>Valor <span>*</span></p>
                        <input type="number" name="valor" id="valor" required="true" value="${produto.valor}" />
                    </div>

                    <p>Descrição <span>*</span></p>
                    <textarea name="descricao" required="true"> ${produto.descricao}</textarea>	

                    </select>

                </div>

            </div>


            <input type="hidden" name="id_produto" value="${produto.id}">
            <button type="submit" class="botao-alterar">Alterar Dados</button>


        </form>	
        <form class="botaoEstoque" action="${pageContext.request.contextPath}/estoque?"
              method="get">
            <input type="hidden" name="id" value="${produto.id}">
            <button type="submit" class="botao-estoque">Gerenciar Estoque</button>
        </form>
        <form class="botaoExcluir" action="${pageContext.request.contextPath}/excluirProduto"
              method="post">
            <input type="hidden" name="id_produto" value="${produto.id}">
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