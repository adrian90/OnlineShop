package PackCommand;

import Interfaces.IProduct;
import Interfaces.IUser;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class UserCommand extends PackCommand.FrontCommand {
    
    
    @Override
    public void process() throws NamingException, SQLException {
        UserInterface user = getUser();
        InitialContext ctx = new InitialContext();
        IUser mapuser = (IUser) ctx.lookup("java:global/TiendaOnline/Database/UserMapper!Interfaces.IUser");
        ArrayList<IProduct> products = mapuser.findProducts(user.getNick(), "", "user");
        request.setAttribute("products", products);
        
        try {
            forward("/UserView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(MainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
