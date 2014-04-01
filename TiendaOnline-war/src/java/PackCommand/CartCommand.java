
package PackCommand;

import Interfaces.ICart;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class CartCommand extends PackCommand.FrontCommand {
        @Override
    public void process() throws NamingException, SQLException{
        
        ICart cart = getCart();
        UserInterface user = getUser();
        
        CheckCart();
         
        try {
            forward("/CartView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}