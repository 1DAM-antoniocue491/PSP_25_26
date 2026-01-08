package org.mm.UD3.Tarea3;

import java.io.Serializable;

public class Token implements Serializable {
    private Long clientCode;
    private String operacion;

    public Token(Long clientCode, String operacion) {
        this.clientCode = clientCode;
        this.operacion = operacion;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
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
                "clientCode=" + clientCode +
                ", operacion='" + operacion + '\'' +
                '}';
    }
}
