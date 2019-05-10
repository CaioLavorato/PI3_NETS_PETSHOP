<!DOCTYPE html>
<html lang="br" >

<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <meta charset="UTF-8">
  <title>Produto</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  
      <link rel="stylesheet" href="./css/Venda.css">
  
</head>

<body>

<form class="steps" accept-charset="UTF-8" enctype="multipart/form-data" novalidate="">
  <ul id="progressbar">
    <li class="active">Cliente</li>
    <li class="active">Produto / Carrinho </li>
    <li>Confirmação</li>
  </ul>

  <h1> VENDA - Cliente: ${clienteVenda.nome} ${clienteVenda.sobrenome}</h1>
  <fieldset>
    <h2 class="fs-title">Produto <i class="fa fa-book" style="font-size: 30px;"></i></h2>

        <div>
        
          <label>Nome Produto<a>*</a></label>

          <input name="nomeProduto" required="required" type="text" data-rule-required="true" data-msg-required="Favor inserir o nome do Produto." >
          <span class="error1" style="display: none;">
              <i class="error-log fa fa-exclamation-triangle"></i>
          </span>
        </div>

<button type="submit" class="botao-consultar  ">Consultar</button>

</form>

<form action="${pageContext.request.contextPath}/adicionarAoCarrinho" method="POST">
    <br>${msg}<br>
    <div class="tabela">
      <table>
        <tr>
          <th>Selecionar</th>
          <th>Nome do produto</th>
          <th>Descricao</th>
          <th>Estoque</th>
          <th>Valor</th>
          <th>Quantidade</th>

        </tr>
                
        <c:forEach items="${listaProdutos}" var="produto">       
            <tr>
              <td><input style="width: 25px; height: 15px;" type="radio" name="selectedItemId" value="${livro.id}"></td>  
              <td> ${prouduto.nomeProduto} </td>
              <td> ${produto.descricao} </td>
              <td> ${produto.categoria.nome} </td>
              <td> <c:out value="${estoque[produto.id]}"/> </td>
              <td> ${produto.valor}</td>
              <td><input class="estoque" type="number" name="${produto.id}" min="0" max="${estoque[produto.id]}" value="0"></td>
            </tr>
        </c:forEach>
      </table>
    </div>
    
    <button type="submit" class="botao-consultar  ">Adicionar ao Carrinho</button>

  </form>
    
  
  <form action="${pageContext.request.contextPath}/removerDoCarrinho" class="steps" accept-charset="UTF-8" enctype="multipart/form-data" novalidate="">
      
    <h2 class="fs-titleCar"> Carrinho <i class="fa fa-shopping-cart" style="font-size: 30px;"></i></h2>
    <div class="tabela">
      <table>
        <tr>
          <th>NomeProduto</th>
          <th>Descricao</th>
          <th>Quantidade</th>
          <th>Valor</th>
          <th>Retirar</th>
        </tr>
        
        <c:forEach items="${shopCart}" var="item">       
            <tr>
              <td> ${item.key.titulo} </td>
              <td> ${item.key.autor}</td>
              <td> ${item.key.editora} </td>
              <td> ${item.key.edicao}</td>
              <td> ${item.value} </td>
              <td> ${item.key.valor}</td>
              <td><a href="${pageContext.request.contextPath}/removerDoCarrinho?selectedItemId=<c:out value="${item.key.id}"/>">Remover<a/></td>
            </tr>
        </c:forEach>   
        <tr>
          
        </tr>
      </table>
        <br>
        Total da Venda <input readonly="true" type="text" name="total" ${total}>
    </div>
    
  </form>

    
<form action="${pageContext.request.contextPath}/selecionaProduto" method="post">
    <button type="submit" class="botao-avancar">Realizar Venda</button>
</form>
    <button type="submit" class="botao-cancelar">Cancelar Venda</button>

</fieldset>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
<script src='https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js'></script>

<script  src="./js/Avisos.js"></script>

  

</body>

</html>
