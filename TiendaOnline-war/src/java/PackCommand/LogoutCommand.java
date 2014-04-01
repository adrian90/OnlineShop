package PackCommand;

import Interfaces.ICart;
import Interfaces.ICatalog;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class LogoutCommand extends FrontCommand {

    @Override
    public void process() throws NamingException, SQLException{
         ICart cart = getCart();    
         cart.setTotalPrice(0);
         for (int i=cart.getContents().size()-1; i>-1; i--)
             cart.removeProduct(i);
        UserInterface usuario = getUser();
        usuario.setNick(null);
        usuario.setPassword(null);
        usuario.setCorreo(null);
        usuario.setNombre(null);
        usuario.setApellidos(null);
        usuario.setEdad(0);
        usuario.setPais(null);
        usuario.setLogout();
        ICatalog Map = getCatalogMap();
        ICatalog cat = Map.findCat(0, "main");
        request.setAttribute("catalogo", cat);
        try {
            forward("/MainView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(MainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
