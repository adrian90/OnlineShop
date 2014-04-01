package PackFrontController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import PackCommand.FrontCommand;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})

public class FrontController extends HttpServlet {
    private FrontCommand Command = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void getCommand(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException{ 
        Command = (FrontCommand)(getCommandClass(request).newInstance()); 
    }
    
    private Class getCommandClass(HttpServletRequest request) throws ClassNotFoundException{ 
        Class result;
        if (request.getParameter("command") == null){
            final String command = "PackCommand.MainCommand";
            result = Class.forName(command); 
        }
        else{
            final String command = "PackCommand."+(String)request.getParameter("command") 
                    + "Command"; 
            result = Class.forName(command);
        }
        
         return result; 
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        FrontController F= new FrontController();
        try {
            F.getCommand(request);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        F.Command.init(getServletContext(), request, response);
        try {
            F.Command.process();
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FrontController F= new FrontController();
        try {
            F.getCommand(request);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        F.Command.init(getServletContext(), request, response);
        try {
            F.Command.process();
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}