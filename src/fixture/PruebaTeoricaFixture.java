/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fixture;

import entities.Comunidad;
import entities.Pregunta;
import entities.Pruebateorica;
import entities.Usuario;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbeans.UsuarioEJBLocal;

/**
 *
 * @author Sebastian
 */
public class PruebaTeoricaFixture {
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String pregunta5;
    private String pregunta6;
    private String pregunta7;
    private String pregunta8;
    private String pregunta9;
    private String pregunta10;
    private String comunidad;
    private String alumno;
    List<Pregunta> respuestas;
    private int nota;

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(String pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public String getPregunta6() {
        return pregunta6;
    }

    public void setPregunta6(String pregunta6) {
        this.pregunta6 = pregunta6;
    }

    public String getPregunta7() {
        return pregunta7;
    }

    public void setPregunta7(String pregunta7) {
        this.pregunta7 = pregunta7;
    }

    public String getPregunta8() {
        return pregunta8;
    }

    public void setPregunta8(String pregunta8) {
        this.pregunta8 = pregunta8;
    }

    public String getPregunta9() {
        return pregunta9;
    }

    public void setPregunta9(String pregunta9) {
        this.pregunta9 = pregunta9;
    }

    public String getPregunta10() {
        return pregunta10;
    }

    public void setPregunta10(String pregunta10) {
        this.pregunta10 = pregunta10;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
      
    public String evaluarPracticaTeorica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Usuario user = sb.getUserByEmail(alumno);
        Comunidad c = sb.getComunidadByNombre(comunidad);
        respuestas = sb.getPreguntas();
        respuestas.get(0).setRespuesta(pregunta1);
        respuestas.get(1).setRespuesta(pregunta2);
        respuestas.get(2).setRespuesta(pregunta3);
        respuestas.get(3).setRespuesta(pregunta4);
        respuestas.get(4).setRespuesta(pregunta5);
        respuestas.get(5).setRespuesta(pregunta6);
        respuestas.get(6).setRespuesta(pregunta7);
        respuestas.get(7).setRespuesta(pregunta8);
        respuestas.get(8).setRespuesta(pregunta9);
        respuestas.get(9).setRespuesta(pregunta10);
        String response = sb.corregirPT(respuestas, c.getIdComunidad(), user.getIdUsuario());
        if (response.equals("t")) {
            return "Evaluación efectuada con éxito";
        }
        return "Error al efectuar la evaluación";
    }
    
    public String configurarNotaPracticaTeorica() throws NamingException {
        InitialContext ic = new InitialContext();
        UsuarioEJBLocal sb = (UsuarioEJBLocal) ic.lookup("java:global/MockRapso/MockRapso-ejb/UsuarioEJB");
        Comunidad c = sb.getComunidadByNombre(comunidad);
        String response = sb.configurarNota(c.getIdComunidad(), nota);
        if (response.equals("true") || response.equals("eq")) {
            return "Configuración de la prueba teórica efectuada con éxito";
        }
        return "Error al efectuar la configuración de la prueba teórica";
    }
}
