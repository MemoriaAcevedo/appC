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
public class PrimeraPracticaFixture {

    private String alumno;
    private String comunidad;

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

    public String iniciarPrimeraPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);

        String response = sb.crearPractica(user.getRutU(), c.getIdComunidad());
        if (response.equals("P-1")) {
            return "Primera práctica iniciada con éxito";
        }
        return "Error al iniciar la primera práctica";
    }

    public String eliminarPrimeraPractica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());

        String response = sb.eliminarPractica(p.getIdPractica());
        if (response.equals("t")) {
            return "Primera práctica eliminada con éxito";
        }
        return "Error al eliminar la primera práctica";
    }
}
