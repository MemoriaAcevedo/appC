/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Casoprueba;
import entities.Comunidad;
import entities.Estadoc;
import entities.Hu;
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
public class CasoPruebaFixture {
    private String alumno;
    private String comunidad;
    private String historiaUsuario;
    private String nombreCaso;
    private String descripcionCaso;
    private String precondicionCaso;
    private String pasosCaso;
    private String resultadosEsperados;
    private String resultadosObtenidos;
    private String estadoCaso;

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

    public String getHistoriaUsuario() {
        return historiaUsuario;
    }

    public void setHistoriaUsuario(String historiaUsuario) {
        this.historiaUsuario = historiaUsuario;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    public String getPrecondicionCaso() {
        return precondicionCaso;
    }

    public void setPrecondicionCaso(String precondicionCaso) {
        this.precondicionCaso = precondicionCaso;
    }

    public String getPasosCaso() {
        return pasosCaso;
    }

    public void setPasosCaso(String pasosCaso) {
        this.pasosCaso = pasosCaso;
    }

    public String getResultadosEsperados() {
        return resultadosEsperados;
    }

    public void setResultadosEsperados(String resultadosEsperados) {
        this.resultadosEsperados = resultadosEsperados;
    }

    public String getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public String getResultadosObtenidos() {
        return resultadosObtenidos;
    }

    public void setResultadosObtenidos(String resultadosObtenidos) {
        this.resultadosObtenidos = resultadosObtenidos;
    }

    public String crearCasoDePrueba() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        Casoprueba cp = new Casoprueba();
        Hu u = new Hu();
        u.setIdHU(1);
        u.setIdentificadorHU("HU-1");
        u.setNombreHU("Identificarse en la terminal bancaria");
        u.setDescripcionHU("Como usuario quiero acceder a la terminal bancaria ingresando mi rut y contraseña para poder utilizar las funcionalidades disponibles");
        cp.setNombre(nombreCaso);
        cp.setDescripcion(descripcionCaso);
        cp.setPrecondicion(precondicionCaso);
        cp.setPasos(pasosCaso);
        cp.setResultadosE(resultadosEsperados);
        cp.setPracticaidPractica(p);
        cp.setHUidHU(u);
        String response = sb.crearCP(cp);
        if (response.equals("true")) {
            return "Caso de prueba creado con éxito";
        }
        return "Error al crear caso de prueba";
    }

    public String eliminarCasoDePrueba() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Casoprueba> cps = sb.getCasosPruebaByPractica(p.getIdPractica());
        int cpId = 0;
        for (Casoprueba cp : cps) {
            if (cp.getIdentificadorCaso().equals("CP-1")) {
                cpId = cp.getIdCp();
                break;
            }
        }
        String response = sb.eliminarCP(cpId);
        if (response.equals("t")) {
            return "Caso de prueba eliminado con éxito";
        }
        return "Error al eliminar caso de prueba";
    }

    public String editarCasoDePrueba() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        Practica p = sb.getPracticaByIdentificadorComunidad("P-1", c.getIdComunidad(), user.getRutU());
        List<Casoprueba> cps = sb.getCasosPruebaByPractica(p.getIdPractica());
        Casoprueba caso = new Casoprueba();
        for (Casoprueba cp : cps) {
            if (cp.getIdentificadorCaso().equals("CP-1")) {
                caso = cp;
                break;
            }
        }
        caso.setNombre(nombreCaso);
        caso.setDescripcion(descripcionCaso);
        caso.setPrecondicion(precondicionCaso);
        caso.setPasos(pasosCaso);
        caso.setResultadosE(resultadosEsperados);
        caso.setResultadosO(resultadosObtenidos);

        Estadoc ec = new Estadoc();
        ec.setIdEstadoC(2);
        ec.setNombre("Pasa");
        caso.setEstadoCidEstadoC(ec);

        String response = sb.actualizarCP(caso);
        if (response.equals("true")) {
            return "Caso de prueba editado con éxito";
        }
        return "Error al editar caso de prueba";
    }
}
