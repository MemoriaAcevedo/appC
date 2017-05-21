/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Casoprueba;
import entities.Comunidad;
import entities.Estadoi;
import entities.Incidenciacp;
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
public class IncidenciacpFixture {
    private String alumno;
    private String comunidad;
    private String nombreIncidenciaCP;
    private String descripcionIncidenciaCP;
    private String pasosIncidenciaCP;
    private String resultadosEsperadosIncidenciaCP;
    private String resultadosObtenidosIncidenciaCP;
    private String estadoIncidenciaCP;

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

    public String getNombreIncidenciaCP() {
        return nombreIncidenciaCP;
    }

    public void setNombreIncidenciaCP(String nombreIncidenciaCP) {
        this.nombreIncidenciaCP = nombreIncidenciaCP;
    }

    public String getDescripcionIncidenciaCP() {
        return descripcionIncidenciaCP;
    }

    public void setDescripcionIncidenciaCP(String descripcionIncidenciaCP) {
        this.descripcionIncidenciaCP = descripcionIncidenciaCP;
    }

    public String getPasosIncidenciaCP() {
        return pasosIncidenciaCP;
    }

    public void setPasosIncidenciaCP(String pasosIncidenciaCP) {
        this.pasosIncidenciaCP = pasosIncidenciaCP;
    }

    public String getResultadosEsperadosIncidenciaCP() {
        return resultadosEsperadosIncidenciaCP;
    }

    public void setResultadosEsperadosIncidenciaCP(String resultadosEsperadosIncidenciaCP) {
        this.resultadosEsperadosIncidenciaCP = resultadosEsperadosIncidenciaCP;
    }

    public String getResultadosObtenidosIncidenciaCP() {
        return resultadosObtenidosIncidenciaCP;
    }

    public void setResultadosObtenidosIncidenciaCP(String resultadosObtenidosIncidenciaCP) {
        this.resultadosObtenidosIncidenciaCP = resultadosObtenidosIncidenciaCP;
    }

    public String getEstadoIncidenciaCP() {
        return estadoIncidenciaCP;
    }

    public void setEstadoIncidenciaCP(String estadoIncidenciaCP) {
        this.estadoIncidenciaCP = estadoIncidenciaCP;
    }
    
    public String crearIncidenciaAsociadaAcasoPrueba() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        Incidenciacp i = new Incidenciacp();
        i.setNombreICP(nombreIncidenciaCP);
        i.setDescripcionICP(descripcionIncidenciaCP);
        i.setPasosICP(pasosIncidenciaCP);
        i.setResultadoEICP(resultadosEsperadosIncidenciaCP);
        i.setResultadoOICP(resultadosObtenidosIncidenciaCP);
        List<Casoprueba> cp = sb.getCasosPruebaByPractica(p.getIdPractica());
        i.setCasoPruebaidCp(cp.get(0));
        String response = sb.crearIncidenciacp(i);
        if (response.equals("true")) {
            return "Incidencia asociada a caso de prueba creada con éxito";
        }
        return "Error al crear la incidencia asociada a caso de prueba";
    }
    
    public String editarIncidenciaAsociadaAcasoPrueba() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Incidenciacp> cps = sb.getIncidenciasCPByPractica(p.getIdPractica());
        Incidenciacp i = new Incidenciacp();
        for (Incidenciacp ix : cps) {
            if (ix.getIdentificadorICP().equals("I-1")) {
                i = ix;
                break;
            }
        }
        i.setNombreICP(nombreIncidenciaCP);
        i.setDescripcionICP(descripcionIncidenciaCP);
        i.setPasosICP(pasosIncidenciaCP);
        i.setResultadoEICP(resultadosEsperadosIncidenciaCP);
        i.setResultadoOICP(resultadosObtenidosIncidenciaCP);

        Estadoi ei = new Estadoi();
        ei.setIdEstadoI(2);
        ei.setNombre(estadoIncidenciaCP);
        i.setEstadoIidEstadoI(ei);
        String response = sb.actualizarIncidenciaCP(i);
        if (response.equals("true")) {
            return "Incidencia asociada a caso de prueba editada con éxito";
        }
        return "Error al editar la incidencia asociada a caso de prueba";
    }
    
     public String eliminarIncidenciaAsociadaAcasoPrueba () throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Incidenciacp> cps = sb.getIncidenciasCPByPractica(p.getIdPractica());
        int iId = 0;
        for (Incidenciacp i : cps) {
            if (i.getIdentificadorICP().equals("I-1")) {
                iId = i.getIdIncidenciacp();
                break;
            }
        }
        String response = sb.eliminarICP(iId);
        if (response.equals("t")) {
            return "Incidencia asociada a caso de prueba eliminada con éxito";
        }
        return "Error al eliminar la incidencia asociada a caso de prueba";
    }
    
}
