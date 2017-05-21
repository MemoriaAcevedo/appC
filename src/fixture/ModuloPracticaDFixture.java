/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.ModuloDEJBLocal;

/**
 *
 * @author Sebastian
 */
public class ModuloPracticaDFixture {

    private String usuario;
    private String pass;
    private int monto;
    private String usuarioATransferir;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getUsuarioATransferir() {
        return usuarioATransferir;
    }

    public void setUsuarioATransferir(String usuarioATransferir) {
        this.usuarioATransferir = usuarioATransferir;
    }

    public String autenticarseEnTerminalBancariaDañada() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloDEJBLocal sb = (ModuloDEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloDEJB");
        String response = sb.loginD(usuario, pass);
        if (response.equals("/moduloD/home")) {
            return "Usuario autenticado con éxito en la terminal bancaria dañada";
        }
        return "Error al autenticar el usuario en la terminal bancaria dañada";
    }

    public String realizarDepositoTerminalBancariaDañada() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloDEJBLocal sb = (ModuloDEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloDEJB");
        String response = sb.depositarD(usuario, monto);
        if (response.equals("true")) {
            return "Depósito realizado con éxito en terminal bancaria dañada";
        }
        return "Error al realizar el depósito en la terminal bancaria dañada";
    }

    public String realizarRetiroTerminalBancariaDañada() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloDEJBLocal sb = (ModuloDEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloDEJB");
        String response = sb.retirarD(usuario, monto);
        if (response.equals("true")) {
            return "Retiro realizado con éxito en terminal bancaria dañada";
        }
        return "Error al realizar el retiro en la terminal bancaria dañada";
    }

    public String realizarTransferenciaTerminalBancariaDañada() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloDEJBLocal sb = (ModuloDEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloDEJB");
        String response = sb.transferirD(usuario, usuarioATransferir, monto);
        if (response.equals("true")) {
            sb.retirarD(usuarioATransferir, monto);
            return "Transferencia realizada con éxito en terminal bancaria dañada";
        }
        return "Error al realizar la transferencia en la terminal bancaria dañada";
    }

    public String cerrarCuentaTerminalBancariaDañada() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloDEJBLocal sb = (ModuloDEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloDEJB");
        String response = sb.cerrarC(usuario);
        if (response.equals("true")) {
            return "Cuenta cerrada con éxito en la terminal bancaria dañada";
        }
        return "Error al cerrar la cuenta en la terminal bancaria dañada";
    }
}
