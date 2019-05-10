<!DOCTYPE html>
<html lang="br" >

<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <meta charset="UTF-8">
  <title>Cliente</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  
  
      <link rel="stylesheet" href="./css/Venda.css">

  
</head>

<body>

<form class="steps" accept-charset="UTF-8" enctype="multipart/form-data" novalidate="" method="get">
  <ul id="progressbar">
    <li class="active">Cliente</li>
    <li>Produto / Carrinho </li>
    <li>Confirmação</li>
  </ul>

  <h1> VENDA </h1>
  <fieldset>
    <h2 class="fs-title">Cliente <i class="fa fa-user" style="font-size: 30px;"></i></h2>

        <div>
        
          <label>Cliente <a>*</a></label>

          <input name="nome" type="text">
          <span class="error1" style="display: none;">
              <i class="error-log fa fa-exclamation-triangle"></i>
          </span>
        </div>

        <div>
        
          <label>CPF <a>*</a></label>

          <input name="cpf" type="text">
          <span class="error1" style="display: none;">
              <i class="error-log fa fa-exclamation-triangle"></i>
          </span>
        </div>

<button type="submit" class="botao-pesquisar">Pesquisar</button>    
</div>

    <div class="tabela">
      <table>
        <tr>
          
          <th>Nome</th>
          <th>Sobrenome</th>
          <th>CPF</th>
        </tr>
        <c:forEach items="${listaCliente}" var="cliente">
            <tr onclick="window.location='/PetShop_Nets-1.0-SNAPSHOT/selecionaCliente?idclientevenda=${cliente.id}';">
            <td> ${cliente.nome} </td>
            <td> ${cliente.sobrenome} </td>
            <td> ${cliente.cpf} </td>
            <input type="hidden" value="${cliente.id}" name="clienteVenda">
        </tr>    
        </c:forEach>
      </table>
    </div>
  </div>

<button type="submit" class="botao-cancelar">Cancelar Venda</button>

  </fieldset>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
<script src='https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js'></script>

<script  src="./js/Avisos.js"></script>

</form>

</body>

</html>
