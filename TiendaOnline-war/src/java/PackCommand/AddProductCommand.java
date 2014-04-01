package PackCommand;

import Interfaces.IAlbum;
import Interfaces.ICart;
import Interfaces.ICatalog;
import Interfaces.IProduct;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class AddProductCommand extends PackCommand.FrontCommand {

    @Override
    public void process() throws NamingException, SQLException {

        ICart cart = getCart();
        UserInterface user = getUser();

        float price = Float.parseFloat(request.getParameter("price"));
        String name = request.getParameter("name");

        String idAlbum = request.getParameter("id");
        String idSong = request.getParameter("idSong");
            
        ICatalog Map = getCatalogMap();
        IAlbum alb = Map.findAlb(Integer.parseInt(idAlbum), "album");
        
        if (idSong == null & alb.getName().equals(name))
            cart.addProduct(alb);
        else {
            IProduct song = alb.getSong(Integer.parseInt(idSong));
            if (song.getName().equals(name))
                cart.addProduct(song);
        }

        cart.sumTotalPrice(price);
        GotProduct(alb);
        request.setAttribute("album", alb);
            
        try {
            forward("/AlbumView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}