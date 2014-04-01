package PackCommand;

import Interfaces.IAlbum;
import Interfaces.ICart;
import Interfaces.ICatalog;
import Interfaces.IUser;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class LoginCommand extends FrontCommand {
    
    @Override
    public void process() throws NamingException, SQLException {
        String user = request.getParameter("User");
        String pass = request.getParameter("Pass");
        String currpage = request.getParameter("currpage");
        
        InitialContext ctx = new InitialContext();
        IUser mapuser = (IUser) ctx.lookup("java:global/TiendaOnline/Database/UserMapper!Interfaces.IUser");
        UserInterface usuario = getUser();
        UserInterface aux = mapuser.findUser(user, pass, "login");
        if (aux.getNick() != null) {
            usuario.setNick(aux.getNick());
            usuario.setPassword(aux.getPassword());
            usuario.setCorreo(aux.getCorreo());
            usuario.setNombre(aux.getNombre());
            usuario.setApellidos(aux.getApellidos());
            usuario.setEdad(aux.getEdad());
            usuario.setPais(aux.getPais());
            usuario.setLogin();
        }
        String id;
        ICatalog Map = getCatalogMap();
        ICart cart = getCart();
        switch (currpage){
            case "Main":
                int idCat = Integer.parseInt(request.getParameter("idCat"));
                ICatalog cat = Map.findCat(idCat, "main");
                request.setAttribute("catalogo", cat);
                break;
            case "Album":
                id = request.getParameter("id");
                IAlbum alb = Map.findAlb(Integer.parseInt(id), "album");

                GotProduct(alb);
                
                request.setAttribute("album", alb);
            case "Cart":
                CheckCart();
                break;
            default:
                break;
        }
        
        currpage = "/"+currpage+"View.jsp";
        try {
            forward(currpage);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(MainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
