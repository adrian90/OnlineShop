
<%@page import="java.util.ArrayList"%>
<%@page import="Interfaces.IProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    ArrayList<IProduct> products = (ArrayList<IProduct>)request.getAttribute("products");
%>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS\\style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <jsp:useBean id="user" scope="session" class="Interfaces.UserInterface" />
        <HR width=90% align="center">
        <table align="left">
            <tr>
                <td>
                    <form action="FrontController" method="post">
                        <input class="button2" type="submit" 
                               value="Inicio">
                    </form> 
                </td>
        </table>
        <HR width=90% align="center"> 
         <table border="0" align="left" cellpadding="20" class="userview">
             <tr>
                 <td><b><i>
                 <% out.println("Información Personal");%>
                 </td>
             </tr>
              <tr>
             <tr>
                 <td><b>
                 <% out.println("Nombre: "+user.getNombre());%>
                 </td>
             </tr>
              <tr>
                  <td><b>
                 <% out.println("Apellidos: "+user.getApellidos());%>
                 </td>
             </tr>
              <tr>
                  <td><b>
                 <% out.println("Edad: "+user.getEdad());%>
                 </td>
             </tr>
              <tr>
                  <td><b>
                 <% out.println("Correo: "+user.getCorreo());%>
                 </td>
             </tr>
              <tr>
                  <td><b>
                 <% out.println("País: "+user.getPais());%>
                 </td>
             </tr>
         </table>
        <h1 align="left">Productos Adquiridos</h1>
        <table align="left" class="listaproductos" width=20%>                                                  
            <tr>
                <td><b>Producto</b></td>
                <td><b>Precio</b></td>
            </tr>
            <% for (int i = 0; i < products.size(); i++) {%>
            <tr>
                <td>
                    <% out.println(products.get(i).getName());%>
                </td>           
                <td>
                    <%out.println(products.get(i).getPrice() + "€");%>
                </td> 
                <%
                    }
                %>
            </tr>
        </table>
    </body>
</html>
