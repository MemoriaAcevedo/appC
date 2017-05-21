/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class AutenticarFixture {
    
    private String email;
    private String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String autenticarUsuario() throws NamingException{
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        String response = sb.login(email, pass);
        if(response.equals("/home/profesor")){
            return "Usuario autenticado como profesor";
        }else if(response.equals("/home/alumno")){
            return "Usuario autenticado como alumno/ayudante";
        }else if(response.equals("/home/administrador")){
            return "Usuario autenticado como administrador";
        }else{
            return "Error al autenticar al usuario";
        }
    }
}
