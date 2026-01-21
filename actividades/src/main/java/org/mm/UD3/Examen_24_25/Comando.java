package org.mm.UD3.Examen_24_25;

public class Comando {
    private TipoComando tipo;
    private String contenido;

    public Comando(TipoComando tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public TipoComando getTipo() {
        return tipo;
    }

    public void setTipo(TipoComando tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Comando{" +
                "tipo=" + tipo +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
