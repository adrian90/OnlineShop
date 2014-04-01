
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS\\style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compra Realizada</title>
    </head>
    <table >
        <tr>
            <td>
                <form action="FrontController" method="post">
                    <input class="button5" type="submit" 
                           value="Seguir Comprando">
                </form> 
            </td>
    </table>
    <h1 id="buy"><%=request.getAttribute("mens")%></h1>
</body>
</html>
