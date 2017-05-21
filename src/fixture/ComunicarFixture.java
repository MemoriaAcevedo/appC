/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Practica;
import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class ComunicarFixture {

    private String alumno;
    private String profesor;
    private String comunidad;
    private String mensaje;

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String enviarMensajeACorrector() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.crearMensaje(p.getIdPractica(), mensaje, "r");
        if (response.equals("true")) {
            return "Mensaje enviado con éxito";
        }
        return "Error al enviar el mensaje";
    }

    public String enviarMensajeAlumno() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.crearMensaje(p.getIdPractica(), mensaje, "c");
        if (response.equals("true")) {
            return "Mensaje enviado con éxito";
        }
        return "Error al enviar el mensaje";
    }
}
