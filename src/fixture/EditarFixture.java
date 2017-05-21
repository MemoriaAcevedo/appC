/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class EditarFixture {
    private String email;
    private String apodo;
    private String pass;
    private String fotoPerfil;
    private Usuario user;
    private Usuario uB;

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

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
  
    public String editarPerfil() throws NamingException{
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        user = sb.getUserByEmail(email);
        uB = user;
        user.setApodoU(apodo);
        user.setContrasenaU(pass);
        user.setFotoPerfilU(fotoPerfil);
        Usuario response = sb.editarUsuario(user);
        if(response != null){
            return "Edición realizada con éxito";
        }
        return "Error al realizar la edición";
    }
    
    
}
