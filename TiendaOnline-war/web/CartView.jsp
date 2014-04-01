
<%@page import="java.text.DecimalFormat"%>
<%@page import="PackCommand.AddProductCommand"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Interfaces.IProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% ArrayList<IProduct> products = new ArrayList<IProduct>();
    DecimalFormat df = new DecimalFormat("0.00");
    String Command = "Album";

    if (request.getParameter("ruta").equals("main")) {
        Command = "Main";
    }
    String actcommand = "Cart";
%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS\\style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito de la Compra</title>
    </head>
    <body>

        <jsp:useBean id="cart" scope="session" class="Interfaces.ICart" />
        <jsp:useBean id="user" scope="session" class="Interfaces.UserInterface" />

        <HR width=90% align="center"> 
        <table align="center">
            <tr>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" 
                               value="<%=Command%>">
                        <input type="hidden" name="id" 
                               value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="idCat" 
                               value="<%=request.getParameter("idCat")%>">
                        <input class="button2" type="submit" 
                               value="Atrás">
                    </form> 
                </td> 
                <td>
                     <% if (user.isLogin()) {%>
                <form action="FrontController" method="post">
                    <td><input type="hidden" name="command" 
                               value="User">
                        <input type="submit" class="user1"
                               value="<%out.print("Bienvenido, " + user.getNombre());%>">
                    </td>
                </form>
                <form action="FrontController" method="post">
                    <td><input type="hidden" name="command" 
                               value="Logout">
                        <input class="button4" type="submit" 
                               value="Logout">
                    </td>
                </form>
                <%}%>
               <% if (!user.isLogin()) { %>
                <form action="FrontController" method="post">
                    <td width="80" align="right"><b>Usuario:&nbsp;</td>
                    <td><input name="User" maxlength="20" type="text"></td>
                    <td width="80" align="right"><b>Password:&nbsp; </td>
                    <td><input name="Pass" maxlength="20" type="password"> </td>
                    <td><input name="submit" class="button" tabindex="5" 
                               value="Login" type="submit"></td>
                    <td width="60"></td>
                    <input type="hidden" name="ruta" 
                           value="<%=request.getParameter("ruta")%>">
                    <input type="hidden" name="idCat" 
                               value="<%=request.getParameter("idCat")%>">
                    <input type="hidden" name="command" value="Login">
                    <input type="hidden" name="currpage" value="<%=actcommand%>">
                    <input type="hidden" name="id" 
                               value="<%=request.getParameter("id")%>">
                </form>
                <%}%> 
                <td>
                    <form action="FrontController" method="post">
                        <input class="button3" type="submit" 
                               value="Inicio">
                    </form>
                </td>
                               
            </tr>
        </table>
        <HR width=90% align="center"> 
        <h1 align="center">Carrito de la Compra</h1>

        <% products = cart.getContents();%>

        <table class="listacanciones" width=20%>                                                  
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
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="name" 
                               value="<%=products.get(i).getName()%>">
                        <input type="hidden" name="command" 
                               value="RemoveProduct">
                        <input type="hidden" name="ruta" 
                           value="<%=request.getParameter("ruta")%>">
                        <input type="hidden" name="id" 
                               value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="idCat" 
                               value="<%=request.getParameter("idCat")%>">
                        <input type="hidden" name="price" 
                               value="<%=products.get(i).getPrice()%>">                               
                        <input class="button" type="submit" value="Eliminar">
                    </form>
                </td>
                <%
                    }
                %>
            </tr>
        </table>
        <hr color="#FF0000" width=20% align="center"/>
        <table class="listacanciones" width=20%>
            <tr>
                <td><b>Precio Total</b></td>
                <td width="25%" height="10%"> 
                    <% out.println(df.format(cart.getTotalPrice()) + "€");%>
                </td>
            </tr>
        </table>
        <table align="center">
            <br>
            <tr>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="EmptyCart">
                        <input type="hidden" name="id" 
                               value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="idCat" 
                               value="<%=request.getParameter("idCat")%>">
                        <input type="hidden" name="ruta" 
                           value="<%=request.getParameter("ruta")%>">
                        <input class="button5" type="submit" value="Vaciar Carro">
                    </form>
                </td>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="Buy">                                    
                        <input class="button5" type="submit" value="Realizar Compra">
                    </form>
                </td>
            </tr>
        </table> 
    </body>
</html>

