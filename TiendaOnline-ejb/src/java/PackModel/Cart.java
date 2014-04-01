package PackModel;

import Interfaces.ICart;
import Interfaces.IProduct;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.ejb.Remove;


@Stateful
public class Cart implements ICart, Serializable {
    
    private float TotalPrice=0;
    private String User;
    private ArrayList<IProduct> contents = new ArrayList<>();
    
    @Override
    public void restTotalPrice(float totalprice){
        this.TotalPrice-=totalprice;
    }
    @Override
    public void sumTotalPrice(float totalprice){
        this.TotalPrice+= totalprice;
    }
    @Override
    public void setTotalPrice(float totalprice){
        this.TotalPrice= totalprice;
    }
    @Override
    public float getTotalPrice(){
        return this.TotalPrice;
    } 
    @Override
    public void setUser(String user){
        this.User=user;
    }
    @Override
    public void addProduct (IProduct p){
        this.contents.add(p);      
    }
    @Override
    public void removeProduct (int p){
        this.contents.remove(p);
    }
    
    @Override
    public ArrayList<IProduct> getContents(){
         return this.contents;
    }
    
    @Override
    public String getUser(){
        return this.User;
    }
    
    
    @Remove
    @Override
    public void remove(){}

}
