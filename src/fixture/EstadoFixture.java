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
public class EstadoFixture {

    private String email;
    private String motivo;
    private Usuario user;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String cerrarCuenta() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        user = sb.getUserByEmail(email);
        String response = sb.cerrarC(user.getRutU(), motivo);
        if (response.equals("true")) {
            return "Cierre de cuenta realizado con éxito";
        }
        return "Error al realizar el cierre";
    }

    public String activarCuenta() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        user = sb.getUserByEmail(email);
        Usuario response = sb.activarC(user.getRutU());
        if (response != null) {
            return "Activación de cuenta realizada con éxito";
        }
        return "Error al realizar la activación";
    }
}
