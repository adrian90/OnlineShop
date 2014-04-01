
package PackCommand;


import Interfaces.ICart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

public class RemoveProductCommand  extends PackCommand.FrontCommand {
      @Override
    public void process(){
         ICart cart = getCart();
         String nameP = request.getParameter("name");
         
         float price = Float.parseFloat(request.getParameter("price"));
         cart.restTotalPrice(price);
         if (cart.getTotalPrice()<=0) cart.setTotalPrice(0);
         
         
         for (int i = 0; i < cart.getContents().size(); i++){ 
             if ((cart.getContents().get(i).getName()).equals(nameP)){
                 cart.removeProduct(i);
                 break;
             }    
        }
         
        try {
            forward("/CartView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}