<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8" />
        <title>Relatorio</title>
        <link rel="stylesheet" type="text/css" media="screen" href="./css/Relatorio.css" />
    </head>

    <body>

        <form action="${pageContext.request.contextPath}/relatorio"
              method="post">
            <h1>Relatorio</h1>           
            <div class="contentform">

                <div class="form-group">
                    <p>Filial <span>*</span></p>
                    <span class="icon-case"></span>
                    <select type="text" name="filiais" required="true" />

                    <c:forEach items="${listaFilial}" var="filiais">                                
                        <option required="true" value="${filiais.id}"> ${filiais.nomeFantasia} </option>              
                    </c:forEach>
                    </select>
                </div>
            </div>

            <button type="submit" class="botao-consultar">Consultar</button>
        </form>

        <div class="tabela">
            <table>
                <tr>
                    <th>Filial</th>
                    <th>Data</th>
                    <th>Vendedor</th>
                    <th>Cliente</th>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Valor Unitario</th>
                </tr>

                <c:forEach items="${listaVenda}" var="venda">

                    <tr>
                        <td> ${venda.filial.nomeFantasia}</td>
                        <td> ${venda.dataVenda} </td>
                        <td> ${venda.usuario.nome} </td>
                        <td> ${venda.cliente.nome} </td>

                        <c:forEach items="${venda.listaItemVenda}" var="item">
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td> ${item.produtoFilial.produto.nomeProduto} </td>
                            <td> ${item.quantidade} </td>
                            <td> ${item.valorUnitario} </td>
                        </tr>
                    </c:forEach>
                    </tr>

                </c:forEach>

            </table>

            <form>
                <center>Valor Total</center> <br> <input class="valor-total" readonly="true" type="text" name="total" value="${total}">
            </form>
        </div>
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