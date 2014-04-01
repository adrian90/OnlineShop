
package PackCommand;

import Interfaces.ICart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

public class EmptyCartCommand extends PackCommand.FrontCommand {
      @Override
    public void process(){
          
         ICart cart = getCart();
         
         cart.setTotalPrice(0);
         for (int i=cart.getContents().size()-1; i>-1; i--)
             cart.removeProduct(i);
        try {
            forward("/CartView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}