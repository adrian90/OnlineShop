package PackCommand;

import Interfaces.IAlbum;
import Interfaces.ICart;
import Interfaces.ICatalog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;


public class AlbumCommand extends PackCommand.FrontCommand {

    @Override
    public void process() throws NamingException, SQLException { 
        
        String id = request.getParameter("id");

        ICatalog Map = getCatalogMap();
        IAlbum alb = Map.findAlb(Integer.parseInt(id), "album");


        ICart cart = getCart();
        GotProduct(alb);

        request.setAttribute("album", alb);
        try {
            forward("/AlbumView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AlbumCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
