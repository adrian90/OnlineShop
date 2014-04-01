

<%@page import="Interfaces.IAlbum"%>
<%@page import="Interfaces.IProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% ArrayList<IAlbum> cat = new ArrayList<IAlbum>();
    String actcommand = "Main";
%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS\\style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Online</title>
    </head>
    <body>
        <jsp:useBean id="catalogo" scope="request" class="Interfaces.ICatalog" /> 
        <jsp:useBean id="user" scope="session" class="Interfaces.UserInterface" />
        <HR width=90% align="center"> 
        <div class="cabeceramain">
            <table align="left">
                <tr>
                <form action="FrontController" method="post">
                    <td><input type="hidden" name="command" 
                               value="Cart">
                        <input type="hidden" name="idCat" 
                               value="<%=catalogo.getID()%>">
                        <input type="hidden" name="ruta" 
                           value="main">
                        <input class="button2" type="submit" 
                               value="Carrito">
                    </td>
                </form>
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
                    <input type="hidden" name="command" value="Login">
                    <input type="hidden" name="currpage" value="<%=actcommand%>">
                    <input type="hidden" name="idCat" 
                           value="<%=catalogo.getID()%>">
                </form>
                <%}%>
                </tr>
            </table>     
        </div>
        <HR width=90% align="center">
        <br>
        <div id="result_container_bottom">
            <span class="nohistory"><a href="FrontController?idCat=1">Anterior</a></span>
            <% for (int i = 0; i > (-catalogo.getNPages()); i--){
                   if (i == catalogo.getID()){%>
                       <span class="actual"><%=(-i)%></a></span>
                   <%}
                   else {%>
                        <span><a href="FrontController?idCat=<%=i%>"><%=(-i)%></a></span>
                   <%}%>
            <%}%>
            <span class="nohistory"> <a href="FrontController?idCat=2">Siguiente</a></span>						
            <div style="clear:both"></div>
        </div>
        <HR width=90% align="center"> 
        <br><br><br>
        <div>
            <table class="mainview" cellpadding="10">         
                <%
                    cat = catalogo.getCatalog();
                %>
                <tr>
                    <% for (int i = 0; i < cat.size(); i++) {%> 

                    <td>
                        <form action="FrontController" method="post">
                            <input type="image" 
                                   src="<%="fotos\\" + cat.get(i).getPhoto()%>"
                                   height="250" width="250">
                            <input type="hidden" name="id" value=<%=cat.get(i).getID()%>>
                            <input type="hidden" name="idCat" value=<%=catalogo.getID()%>>
                            <input type="hidden" name="command" value="Album">
                        </form>                           
                        <b><%= cat.get(i).getAuthor()%></b>
                    </td>

                    <%}%>
                </tr>
            </table>   
        </div>
    </body>
</html>
