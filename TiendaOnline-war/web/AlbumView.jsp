
<%@page import="Interfaces.ISong"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% ArrayList<ISong> canciones = new ArrayList<ISong>();
    int pointer = 0;
    String added;
    String disable="";
    String actcommand = "Album";
%>


<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS\\style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Album</title>
    </head>
    <body>
        <jsp:useBean id="album" scope="request" class="Interfaces.IAlbum" />
        <jsp:useBean id="user" scope="session" class="Interfaces.UserInterface" />
        <HR width=90% align="center"> 
        <table align="center">
            <tr>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" 
                               value="Main">
                        <input type="hidden" name="idCat" 
                               value="<%=Integer.parseInt(request.getParameter("idCat"))%>">
                        <input class="button2" type="submit" 
                               value="Atrás">
                    </form> 
                </td>
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
                    <input type="hidden" name="idCat" 
                               value="<%=request.getParameter("idCat")%>">
                    <input type="hidden" name="command" value="Login">
                    <input type="hidden" name="currpage" value="<%=actcommand%>">
                    <input type="hidden" name="id" 
                           value="<%=album.getID()%>">
                </form>
                <%}%> 
            <td>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" 
                           value="Cart">
                    <input type="hidden" name="id" 
                           value="<%=album.getID()%>">
                    <input type="hidden" name="idCat" 
                               value="<%=Integer.parseInt(request.getParameter("idCat"))%>">
                    <input type="hidden" name="ruta" 
                           value="album">
                    <input class="button3" type="submit" 
                           value="Carrito">
                </form> 
            </td>
        </tr>
    </table>
    <HR width=90% align="center"> 
    <br>

    <% String photo1 = "fotos\\" + album.getPhoto();%>

    <table border="0" align="center" cellpadding="20" class="albumview">
        <tr>
            <td>
                <%  canciones = album.getSongsAlbum();%>
                <%
                    if (album.getAdded() == false) {
                        added = String.valueOf(album.getPrice() + "€");
                    } else {
                        disable = "disabled";
                        added = "Añadido";
                        if (album.getBuyed()){
                            added="Comprado";
                        }
                    }
                %>
                <b><%  out.println("Autor: " + album.getAuthor());%></b>
                <br><br>
                <b><%  out.println("Album: " + album.getName());%></b>
                <br><br>
                <form action="FrontController" method="post">
                    <input type="hidden" name="name" 
                           value="<%=album.getName()%>">
                    <input type="hidden" name="command" 
                           value="AddProduct">
                    <input type="hidden" name="id" 
                           value="<%=album.getID()%>">
                    <input type="hidden" name="idCat" 
                               value="<%=Integer.parseInt(request.getParameter("idCat"))%>">
                    <input type="hidden" name="price" 
                           value="<%=album.getPrice()%>">
                    <input class="button" <%=disable%> type="submit" 
                           value="<%=added%>">
                </form> 
            </td>
            <td>
                <img src="<%=photo1%>" height="300" width="400">
            </td>
            <td align="center">
                <b>Canciones:</b>
                <br>
                <table class="listacanciones">
                    <%disable="";%>
                    <% for (int i = 0; i < canciones.size(); i++) {%>           
                    <tr><td><% out.println(i + 1 + "-.");%>
                            <%=canciones.get(i).getName()%>   
                        </td>                   

                        <td>
                            <%
                                if (canciones.get(i).getAdded() == false) {
                                    added = canciones.get(i).getPrice() + "€";
                                } else {
                                    disable = "disabled";
                                    added = "Añadido";
                                    if (canciones.get(i).getBuyed()){
                                        added="Comprado";
                                    }
                                }
                            %>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="name" 
                                       value="<%=canciones.get(i).getName()%>">
                                <input type="hidden" name="command" 
                                       value="AddProduct">
                                <input type="hidden" name="id" 
                                       value="<%=album.getID()%>">
                                <input type="hidden" name="idCat" 
                                value="<%=Integer.parseInt(request.getParameter("idCat"))%>">
                                <input type="hidden" name="idSong" 
                                       value="<%=canciones.get(i).getID()%>">
                                <input type="hidden" name="price" 
                                       value="<%=canciones.get(i).getPrice()%>">
                                <input class="button" <%=disable%> type="submit" 
                                       value="<%=added%>">
                            </form> 
                        </td>
                    </tr>
                    <% disable = ""; }%>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>
