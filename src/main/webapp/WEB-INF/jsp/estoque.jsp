<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Gerenciamento de Estoque</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/Estoque.css" />
    </head>
    <body>

        <form action="${pageContext.request.contextPath}/estoque?id=${produto.id}"
              method="post">
            <h1>Gerenciamento de Estoque</h1>

            <div class="contentform">

                <div>
                    <p> ${produto.nomeProduto} <span></span></p>

                </div>

                <div class="tabela">
                    <table>
                        <tr>
                            <th>FILIAL</th>
                            <th>QUANTIDADE DISPONÍVEL</th>
                        </tr>
                        <c:forEach items="${listaProdutoFilial}" var="produtoFilial">  
                            <tr>
                                <td> ${produtoFilial.filial.nomeFantasia} </td>
                                <td><input class="estoque" type="number" name="${produtoFilial.filial.id}"
                                           value="${produtoFilial.estoque}" required="true" min="0"></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div>
                    <button type="submit" class="botao-estoque"> Atualizar Estoque</button>
                </div>
            </div>
        </form> 



        <div class="navbar">
            <a style="text-decoration: none" href="PaginaPrincipal.html"><h1 class="title"></h1></a>

            <div class="menu">
                <ul>
                    <li><a class="button" id="home" href="./consultarProduto">Retornar</a></li>
                </ul>
            </div>
        </div>
    </body>
</html>