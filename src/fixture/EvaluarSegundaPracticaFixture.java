/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Practica2;
import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class EvaluarSegundaPracticaFixture {
    private String alumno;
    private String profesor;
    private String comunidad;
    private String observacion;
    private int nota;

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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String enviarACorreccionSegundaPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica2 p = sb.getPractica2ByIdentificarP("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.enviarACorreccionP2(p.getIdPractica2());
        if (response.equals("t")) {
            return "Segunda práctica enviada a corrección con éxito";
        }

        return "Error al enviar a corrección la segunda práctica";
    }

    public String evaluarSegundaPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica2 p = sb.getPractica2ByIdentificarP("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.evaluarP2(p.getIdPractica2(), nota, observacion);
        if (response.equals("t")) {
            return "Segunda práctica evaluada con éxito";
        }

        return "Error al evaluar la segunda práctica";

    }
}
