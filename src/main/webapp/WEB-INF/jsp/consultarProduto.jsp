<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Consulta de Produto</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/ConsultarProduto.css" />
    </head>

    <body>

    <body>

        <form>
            <h1>Consulta de Produto</h1>

            <div class="contentform">

                <div>
                    <div class="form-group">
                        <p> Nome do Produto <span>*</span></p>
                        <input type="text" name="nomeProduto" id="titulo" />
                    </div> 

                    <p>Categoria<span>*</span></p>
                    <select type="text" name="categoria" />
                    <option value="Selecione"> Selecione </option>
                    <option value="Brinquedos"> Brinquedos </option>
                    <option value="Medicamentos"> Medicamentos </option>
                    <option value="Racões"> Racões </option>
                    <option value="Animais"> Animais </option>
                    </select>
                </div>
            </div>
        </div>

        <div class="tabela">
            <table>
                <tr>
                    <th>Nome do Produto</th>
                    <th>Categoria</th>
                </tr>
                <c:forEach items="${listaLivros}" var="livro">   
                    <tr>
                        <td ><a href="${pageContext.request.contextPath}/detalhesProduto?id=<c:out value="${produto.id}"/>"><c:out value="${produto.titulo}"/> <a/></td>
                        <td> <c:out value="${produto.categoria.nome}"/> </td>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <div>
            <button type="submit" class="botao-consultar">Consultar</button>
        </div>

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