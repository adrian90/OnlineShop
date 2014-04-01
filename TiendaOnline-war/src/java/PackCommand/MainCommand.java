package PackCommand;

import Interfaces.ICatalog;
import Interfaces.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class MainCommand extends PackCommand.FrontCommand {
    
    
    @Override
    public void process() throws NamingException, SQLException {
        
        String idi = (request.getParameter("idCat"));
        if (idi == null) idi = "0";
        
        int id = Integer.parseInt(idi);
        
        ICatalog Map = getCatalogMap();
        ICatalog cat = Map.findCat(id, "main");
        UserInterface user = getUser();
        
        request.setAttribute("catalogo", cat);
        
        try {
            forward("/MainView.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(MainCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
