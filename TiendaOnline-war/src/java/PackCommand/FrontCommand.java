package PackCommand;



import Interfaces.IAlbum;
import Interfaces.ICart;
import Interfaces.ICatalog;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

abstract public class FrontCommand {
    protected ServletContext  context; 
    protected HttpServletRequest  request;
    protected HttpServletResponse response; 
    
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response){
        this.context = context;
        this.request  = request;
        this.response = response;    
    }
    
    public ICart getCart(){
        HttpSession session = request.getSession(true);
        ICart cart = (ICart)session.getAttribute("cart");
        if (cart != null){
            return cart;
        }
        else {
            try {
                InitialContext ctx = new InitialContext();
                cart = (ICart)ctx.lookup("java:global/TiendaOnline/TiendaOnline-ejb/"
                        + "Cart!Interfaces.ICart");
                session.setAttribute("cart", cart);
            } catch (NamingException ex) {
                Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cart;
        }
    }
    public ICatalog getCatalogMap(){
        HttpSession session = request.getSession(true);
        ICatalog cat  = (ICatalog)session.getAttribute("cat");
        if (cat != null){
            return cat;
        }
        else {
            try {
                InitialContext ctx = new InitialContext();
                cat = (ICatalog)ctx.lookup("java:global/TiendaOnline/Database/"
                        + "CatalogMapper!Interfaces.ICatalog");
                session.setAttribute("cat", cat);
            } catch (NamingException ex) {
                Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cat;
        }
    }
    public UserInterface getUser(){
        HttpSession session = request.getSession(true);
        UserInterface usuario  = (UserInterface)session.getAttribute("user");
        if (usuario!= null){
            return usuario;
        }
        else {
            try {
                InitialContext ctx = new InitialContext();
                usuario = (UserInterface)ctx.lookup("java:global/TiendaOnline/TiendaOnline-ejb/"
                        + "User!Interfaces.UserInterface");
                session.setAttribute("user", usuario);
            } catch (NamingException ex) {
                Logger.getLogger(FrontCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            return usuario;
        }
    }
    public void CheckCart()throws NamingException, SQLException{
        UserInterface user = getUser();
        ICart cart = getCart();
        if (user.getNick()!=null && (!cart.getContents().isEmpty())){
            InitialContext ctx = new InitialContext();
            IUser mapuser = (IUser) ctx.lookup("java:global/TiendaOnline/Database/UserMapper!Interfaces.IUser");
            ArrayList<IProduct> products = mapuser.findProducts(user.getNick(), "", "user");
            for (int i = 0; i < cart.getContents().size(); i++) {
                for (int j = 0; j < products.size(); j++){
                    if (cart.getContents().get(i).getID()==products.get(j).getID()){
                        cart.removeProduct(i);
                        cart.restTotalPrice(products.get(j).getPrice());
                        break;
                    }
                }
            }
        }
    }
    
    public void GotProduct(IAlbum alb)throws NamingException, SQLException{
        UserInterface user = getUser();
        ICart cart = getCart();
        if (user.getNick()!=null){
            InitialContext ctx = new InitialContext();
            IUser mapuser = (IUser) ctx.lookup("java:global/TiendaOnline/Database/UserMapper!Interfaces.IUser");
            ArrayList<IProduct> products = mapuser.findProducts(user.getNick(), "", "user");
            for(int j = 0; j < products.size(); j++){
                if ((alb.getID())==products.get(j).getID()){
                    alb.setAdded(true);
                    alb.setBuyed(true);
                    break;
                }
                else {
                    alb.setBuyed(false);
                    alb.setAdded(false);
                }
            }       
            for (int i = 0; i < alb.getSongsAlbum().size(); i++) {
                for (int j = 0; j < products.size(); j++) {
                    if (alb.getSongsAlbum().get(i).getID()==products.get(j).getID()) {
                        alb.getSongsAlbum().get(i).setAdded(true);
                        alb.getSongsAlbum().get(i).setBuyed(true);
                        break;
                    } else {
                        alb.getSongsAlbum().get(i).setBuyed(false);
                        alb.getSongsAlbum().get(i).setAdded(false);
                    }
                }
            }
        }
        
        if (cart.getContents().isEmpty() && user.getNick() == null) {
            if (!alb.getBuyed()) alb.setAdded(false);
            for (int i = 0; i < alb.getSongsAlbum().size(); i++) {
                if (!alb.getSongsAlbum().get(i).getBuyed()) 
                    alb.getSongsAlbum().get(i).setAdded(false);
            }
            return;
        }
        for (int j = 0; j < cart.getContents().size(); j++) {
            if ((alb.getName()).equals(cart.getContents().get(j).getName())
                    && (alb.getPrice()) == cart.getContents().get(j).getPrice()) {
                alb.setAdded(true);
                break;
            } else{
                 if (!alb.getBuyed()) alb.setAdded(false);
            }
        }
        for (int i = 0; i < alb.getSongsAlbum().size(); i++) {
            for (int j = 0; j < cart.getContents().size(); j++) {
                if (alb.getSongsAlbum().get(i).getName().equals(cart.getContents().get(j).getName())
                        && (alb.getSongsAlbum().get(i).getPrice())
                        == cart.getContents().get(j).getPrice()) {
                    alb.getSongsAlbum().get(i).setAdded(true);
                    break;
                } else{
                    if (!alb.getSongsAlbum().get(i).getBuyed()) 
                    alb.getSongsAlbum().get(i).setAdded(false);
                }
            }
        }
    }

    
    
    abstract public void process() throws NamingException, SQLException;
    
    public void forward(String destiny)
    throws ServletException, IOException {
    RequestDispatcher dp = 
        context.getRequestDispatcher(destiny);
    dp.forward(request, response);
    }
}
