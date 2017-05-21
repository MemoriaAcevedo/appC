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
public class SegundaPracticaFixture {

    private String alumno;
    private String comunidad;
    private String repositorioGithub;
    private String ambienteDeTrabajo;

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getRepositorioGithub() {
        return repositorioGithub;
    }

    public void setRepositorioGithub(String repositorioGithub) {
        this.repositorioGithub = repositorioGithub;
    }

    public String getAmbienteDeTrabajo() {
        return ambienteDeTrabajo;
    }

    public void setAmbienteDeTrabajo(String ambienteDeTrabajo) {
        this.ambienteDeTrabajo = ambienteDeTrabajo;
    }

    public String iniciarSegundaPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        String response = sb.crearPracticaP2("P-1", c.getIdComunidad(), user.getRutU());
        if (response.equals("t")) {
            return "Segunda práctica iniciada con éxito";
        }
        return "Error al iniciar la segunda práctica";
    }

    public String guardarAmbienteYRepositorio() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica2 p = sb.getPractica2ByIdentificarP("P-1", c.getIdComunidad(), user.getRutU());
        p.setUrlCodenvy(ambienteDeTrabajo);
        p.setUrlGithub(repositorioGithub);
        String response = sb.guardarAR(p);
        if (response.equals("t")) {
            return "Ambiente de trabajo y repositorio guardado con éxito";
        }
        return "Error guardar el ambiente de trabajo y repositorio";
    }
}
