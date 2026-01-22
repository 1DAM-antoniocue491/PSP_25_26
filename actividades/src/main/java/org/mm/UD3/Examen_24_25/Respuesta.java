package org.mm.UD3.Examen_24_25;

import java.io.Serializable;
import java.util.List;

public class Respuesta implements Serializable {
    private TipoRespuesta tipo;
    private String notificacion;
    private List<Mensaje> mensajes;

    public Respuesta(TipoRespuesta tipo, String notificacion) {
        this.tipo = tipo;
        this.notificacion = notificacion;
    }

    public Respuesta(TipoRespuesta tipo, String notificacion, List<Mensaje> mensajes) {
        this.tipo = tipo;
        this.notificacion = notificacion;
        this.mensajes = mensajes;
    }

    public TipoRespuesta getTipo() {
        return tipo;
    }

    public void setTipo(TipoRespuesta tipo) {
        this.tipo = tipo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "tipo=" + tipo +
                ", notificacion='" + notificacion + '\'' +
                ", mensajes=" + mensajes +
                '}';
    }
}
