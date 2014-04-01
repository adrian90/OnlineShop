
package PackCommand;

import Interfaces.ICart;
import Interfaces.ISaver;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class BuyCommand extends PackCommand.FrontCommand{
    @Override
    public void process() throws NamingException, SQLException{
        
        ICart cart = getCart();
        UserInterface user = getUser();
        if (cart.getContents().isEmpty()){
            String mensaje = "No hay ningún producto en el carrito";
            request.setAttribute("mens", mensaje);
        }
        else{
            if (user.getNick()==null){
                String mensaje = "¡Su compra ha sido realizada!";
                request.setAttribute("mens", mensaje);
                try {
                    forward("/BuyView.jsp");
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String mensaje = "¡Su compra ha sido realizada!";
            request.setAttribute("mens", mensaje);
            
            InitialContext ctx = new InitialContext();
            ISaver save = (ISaver)ctx.lookup("java:global/TiendaOnline/Database/"
                        + "UserProductMapper!Interfaces.ISaver");  
            
            try {
                save.saveProducts(cart, user.getNick());
            } catch (    NamingException | SQLException ex) {
                Logger.getLogger(BuyCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            cart.setTotalPrice(0);
            for (int i=cart.getContents().size()-1; i>-1; i--){
                cart.removeProduct(i);
            }
        }
        
        try {
            forward("/BuyView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

     

