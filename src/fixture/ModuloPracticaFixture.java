/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.ModuloEJBLocal;

/**
 *
 * @author Sebastian
 */
public class ModuloPracticaFixture {

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

    public String autenticarseEnTerminalBancaria() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.login(usuario, pass);
        if (response.equals("/moduloP/home")) {
            return "Usuario autenticado con éxito en la terminal bancaria";
        }
        return "Error al autenticar el usuario en la terminal bancaria";
    }

    public String realizarDeposito() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.depositar(usuario, monto);
        if (response.equals("true")) {
            return "Depósito realizado con éxito en terminal bancaria";
        }
        return "Error al realizar el depósito en la terminal bancaria";
    }

    public String realizarRetiro() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.retirar(usuario, monto);
        if (response.equals("true")) {
            return "Retiro realizado con éxito en terminal bancaria";
        }
        return "Error al realizar el retiro en la terminal bancaria";
    }

    public String realizarTransferencia() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.transferir(usuario, usuarioATransferir, monto);
        if (response.equals("true")) {
            sb.retirar(usuarioATransferir, monto);
            return "Transferencia realizada con éxito en terminal bancaria";
        }
        return "Error al realizar la transferencia enb la terminal bancaria";
    }

    public String cerrarCuentaTerminalBancaria() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.updateEstadoC(usuario, "c");
        if (response.equals("true")) {
            return "Cuenta cerrada con éxito en la terminal bancaria";
        }
        return "Error al cerrar la cuenta en la terminal bancaria";
    }
    
    public String activarCuentaTerminalBancaria() throws NamingException {
        InitialContext ic = new InitialContext();
        ModuloEJBLocal sb = (ModuloEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/ModuloEJB");
        String response = sb.updateEstadoC(usuario, "a");
        if (response.equals("true")) {
            return "Cuenta activada con éxito en la terminal bancaria";
        }
        return "Error al activar la cuenta en la terminal bancaria";
    }
}
