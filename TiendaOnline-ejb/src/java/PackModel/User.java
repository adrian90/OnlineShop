package PackModel;

import Interfaces.UserInterface;
import java.io.Serializable;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class User implements UserInterface, Serializable {
    private String nick;
    private String password;
    private String correo;
    private String nombre;
    private String apellidos;
    private int edad;
    private String pais;
    private boolean isLogin = false;

    @Override
    public boolean isLogin(){
        return this.isLogin;
    }
    
    @Override
    public void setLogin(){
        this.isLogin = true;
    }
    @Override
    public void setLogout(){
        this.isLogin = false;
    }
    @Override
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public int getEdad() {
        return edad;
    }

    @Override
    public String getPais() {
        return pais;
    }
    
    @Remove
    @Override
    public void remove(){}
    
}
