<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Consulta de Produto</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/ConsultarProduto.css" />
    </head>

    <body>
        <form action="${pageContext.request.contextPath}/consultarProduto"
              method="post">
            <h1>Consulta de Produto</h1>

            <div class="contentform">

                <div>
                    <div class="form-group">
                        <p> Nome do Produto <span>*</span></p>
                        <input type="text" name="nomeProduto" id="nomeProduto" required="true" />
                    </div> 

                    <p>Categoria<span>*</span></p>
                    <select type="text" name="categoria" />
                    <option value="Selecione"> Selecione </option>
                    <option value="Medicamentos"> Medicamentos </option>
                    <option value="Racões"> Racões </option>
                    <option value="Animais"> Animais </option>
                    <option value="Brinquedos"> Brinquedos </option>
                    </select>
                </div>
            </div>
            <div>
                <button type="submit" class="botao-consultar">Consultar</button>
            </div>

            <div class="tabela">
                <table>
                    <tr>
                        <th>Nome do Produto</th>
                        <th>Categoria</th>
                        <th>Valor</th>
                    </tr>
                    <c:forEach items="${listaProduto}" var="produto">   
                        <tr>
                            <td ><a href="${pageContext.request.contextPath}/detalhesProduto?id_produto=<c:out value="${produto.id}"/>"><c:out value="${produto.nomeProduto}"/> <a/></td>
                            <td> <c:out value="${produto.categoria.nome}"/> </td>
                            <td> <c:out value="${produto.valor}"/></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>



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