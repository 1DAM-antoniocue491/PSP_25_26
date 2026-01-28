package org.mm.UD3.ServidorDNS_Repaso;

import java.io.Serializable;

public class Token implements Serializable {
    private long codCliete;
    private String operacion;

    public Token(long codCliete, String operacion) {
        this.codCliete = codCliete;
        this.operacion = operacion;
    }

    public long getCodCliete() {
        return codCliete;
    }

    public void setCodCliete(long codCliete) {
        this.codCliete = codCliete;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Override
    public String toString() {
        return "Token{" +
                "codCliete=" + codCliete +
                ", operacion='" + operacion + '\'' +
                '}';
    }
}
