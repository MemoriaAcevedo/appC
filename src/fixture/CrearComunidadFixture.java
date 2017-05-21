/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class CrearComunidadFixture {

    private String profesor;
    private String nombreComunidad;
    private String descripcionComunidad;
    private String nuevoNombreComunidad;
    private String nuevaDescripcionComunidad;
    private String alumno;

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getNombreComunidad() {
        return nombreComunidad;
    }

    public void setNombreComunidad(String nombreComunidad) {
        this.nombreComunidad = nombreComunidad;
    }

    public String getDescripcionComunidad() {
        return descripcionComunidad;
    }

    public void setDescripcionComunidad(String descripcionComunidad) {
        this.descripcionComunidad = descripcionComunidad;
    }

    public String getNuevoNombreComunidad() {
        return nuevoNombreComunidad;
    }

    public void setNuevoNombreComunidad(String nuevoNombreComunidad) {
        this.nuevoNombreComunidad = nuevoNombreComunidad;
    }

    public String getNuevaDescripcionComunidad() {
        return nuevaDescripcionComunidad;
    }

    public void setNuevaDescripcionComunidad(String nuevaDescripcionComunidad) {
        this.nuevaDescripcionComunidad = nuevaDescripcionComunidad;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String crearComunidad() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        String response = sb.crearComunidad(profesor, nombreComunidad, descripcionComunidad);
        if (response.equals("t")) {
            return "Comunidad creada con éxito";
        } else if (response.equals("e")) {
            return "Nombre ingresado existente";
        } else if (response.equals("e")) {
            return "El e-mail ingresado se encuentra registrado";
        }
        return "Error";
    }

    public String editarComunidad() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Comunidad c = sb.getComunidadByNombre(nombreComunidad);
        c.setNombreC(nuevoNombreComunidad);
        c.setDescripcionC(nuevaDescripcionComunidad);
        String response = sb.editarComunidad(c);
        if (response.equals("true")) {
            return "Comunidad actualizada con éxito";
        } else if (response.equals("e")) {
            return "Nombre ingresado existente";
        } else if (response.equals("false")) {
            return "Error al actualizar la comunidad";
        }
        return "Error";
    }

    public String eliminarComunidad() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Comunidad c = sb.getComunidadByNombre(nombreComunidad);
        String response = sb.eliminarComunidad(c);
        if (response.equals("true")) {
            return "Comunidad eliminada con éxito";
        } else if (response.equals("false")) {
            return "Error al eliminar la comunidad";
        }
        return "Error";
    }
    
    public String asociarUsuarioAComunidad() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario u = sb.getUserByEmail(alumno);
        String response = sb.asociarAComunidad(nombreComunidad, u.getRutU());
        if (response.equals("true")) {
            return "Usuario asociado con éxito";
        } else if (response.equals("e")) {
            return "Usuario ya se encuentra asociado";
        }

        return "Error al asociar el usuario a la comunidad. Intente más tarde";
    }
    
    public String desligarUsuarioDeComunidad() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario u = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(nombreComunidad);
        String response = sb.desligar(c.getIdComunidad(), u.getIdUsuario());
        if (response.equals("true")) {
            return "Usuario desligado con éxito";
        }
        return "Error al desligar el usuario de la comunidad. Intente más tarde";
    }

}
