/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Estadoi;
import entities.Incidencia;
import entities.Practica;
import entities.Usuario;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class IncidenciaFixture {

    private String alumno;
    private String comunidad;
    private String casoPrueba;
    private String nombreIncidencia;
    private String descripcionIncidencia;
    private String pasosIncidencia;
    private String resultadosEsperadosIncidencia;
    private String resultadosObtenidosIncidencia;
    private String estadoIncidencia;

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

    public String getCasoPrueba() {
        return casoPrueba;
    }

    public void setCasoPrueba(String casoPrueba) {
        this.casoPrueba = casoPrueba;
    }

    public String getNombreIncidencia() {
        return nombreIncidencia;
    }

    public void setNombreIncidencia(String nombreIncidencia) {
        this.nombreIncidencia = nombreIncidencia;
    }

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public String getPasosIncidencia() {
        return pasosIncidencia;
    }

    public void setPasosIncidencia(String pasosIncidencia) {
        this.pasosIncidencia = pasosIncidencia;
    }

    public String getResultadosEsperadosIncidencia() {
        return resultadosEsperadosIncidencia;
    }

    public void setResultadosEsperadosIncidencia(String resultadosEsperadosIncidencia) {
        this.resultadosEsperadosIncidencia = resultadosEsperadosIncidencia;
    }

    public String getResultadosObtenidosIncidencia() {
        return resultadosObtenidosIncidencia;
    }

    public void setResultadosObtenidosIncidencia(String resultadosObtenidosIncidencia) {
        this.resultadosObtenidosIncidencia = resultadosObtenidosIncidencia;
    }

    public String getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public String crearIncidencia() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        Incidencia i = new Incidencia();
        i.setNombreI(nombreIncidencia);
        i.setDescripcionI(descripcionIncidencia);
        i.setPasosI(pasosIncidencia);
        i.setResultadoEI(resultadosEsperadosIncidencia);
        i.setResultadoOI(resultadosObtenidosIncidencia);
        i.setPracticaidPractica(p);
        String response = sb.crearIncidencia(i);
        if (response.equals("true")) {
            return "Incidencia creada con éxito";
        }
        return "Error al crear la incidencia";
    }

    public String editarIncidencia() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Incidencia> cps = sb.incidenciasByPractica(p.getIdPractica());
        Incidencia i = new Incidencia();
        for (Incidencia ix : cps) {
            if (ix.getIdentificadorI().equals("I-1")) {
                i = ix;
                break;
            }
        }
        i.setNombreI(nombreIncidencia);
        i.setDescripcionI(descripcionIncidencia);
        i.setPasosI(pasosIncidencia);
        i.setResultadoEI(resultadosEsperadosIncidencia);
        i.setResultadoOI(resultadosObtenidosIncidencia);

        Estadoi ei = new Estadoi();
        ei.setIdEstadoI(2);
        ei.setNombre("Corregida");
        i.setEstadoIidEstadoI(ei);
        String response = sb.actualizarIncidencia(i);
        if (response.equals("true")) {
            return "Incidencia editada con éxito";
        }
        return "Error al editar la incidencia";
    }

    public String eliminarIncidencia() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Incidencia> cps = sb.incidenciasByPractica(p.getIdPractica());
        int iId = 0;
        for (Incidencia i : cps) {
            if (i.getIdentificadorI().equals("I-1")) {
                iId = i.getIdIncidencia();
                break;
            }
        }
        String response = sb.eliminarI(iId);
        if (response.equals("t")) {
            return "Incidencia eliminada con éxito";
        }
        return "Error al eliminar la incidencia";
    }

}
