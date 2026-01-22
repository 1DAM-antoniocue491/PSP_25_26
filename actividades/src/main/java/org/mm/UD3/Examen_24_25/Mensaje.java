package org.mm.UD3.Examen_24_25;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mensaje implements Serializable {
    private String remitente;
    private String destinatario;
    private String contenido;
    private LocalDateTime fechaHora;
    private boolean leido;

    public Mensaje(String remitente, String destinatario, String contenido, LocalDateTime fechaHora) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fechaHora=" + fechaHora +
                ", leido=" + leido +
                '}';
    }
}
