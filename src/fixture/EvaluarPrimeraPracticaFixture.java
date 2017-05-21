/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Practica1;
import entities.Usuario;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class EvaluarPrimeraPracticaFixture {

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

    public String enviarACorreccionPrimeraPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica1 p = sb.getPractica1ByIdentificarP("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.enviarACorreccionP1(p.getIdPractica1());
        if (response.equals("t")) {
            return "Primera práctica enviada a corrección con éxito";
        }

        return "Error al enviar a corrección la primera práctica";
    }

    public String evaluarPrimeraPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica1 p = sb.getPractica1ByIdentificarP("P-1", c.getIdComunidad(), user.getRutU());
        String response = sb.evaluarP1(p.getIdPractica1(), nota, observacion);
        if (response.equals("t")) {
            return "Primera práctica evaluada con éxito";
        }

        return "Error al evaluar la práctica";

    }
}
