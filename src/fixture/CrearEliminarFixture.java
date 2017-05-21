/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Tipousuario;
import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class CrearEliminarFixture {

    private String email;
    private String rut;
    private String nombre;
    private String apellido;
    private String apodo;
    private String preguntaSecreta;
    private String respuestaSecreta;
    private String tipoDeUsuario;
    private Usuario user;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String crearUsuario() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = new Usuario();
        user.setEmailU(email);
        user.setApodoU(apodo);
        user.setPreguntaU(preguntaSecreta);
        user.setRespuestaU(respuestaSecreta);
        user.setRutU(rut);
        user.setNombreU(nombre);
        user.setApellidoU(apellido);
        Tipousuario u = new Tipousuario();
        u.setIdTipoUsuario(1);
        u.setNombreTU(tipoDeUsuario);

        user.setTipoUsuarioidTipoUsuario(u);
        String response = sb.crearUsuario(user);
        if (response.equals("t")) {
            return "Usuario creado con éxito";
        } else if (response.equals("r")) {
            return "El rut ingresado se encuentra registrado";
        } else if (response.equals("e")) {
            return "El e-mail ingresado se encuentra registrado";
        } else if (response.equals("false")) {
            return "Rut y e-mail ingresados se encuentran registrados";
        }
        return "Error al crear el usuario";
    }

    public String eliminarUsuario() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        user = sb.getUserByEmail(email);
        String response = sb.eliminarC(user.getRutU());
        if (response.equals("t")) {
            return "Usuario eliminado con éxito";
        }
        return "Error al eliminar el usuario";
    }

}
