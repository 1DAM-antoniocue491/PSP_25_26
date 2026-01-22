package org.mm.UD3.Entregable_24_25_Netflix;

import java.io.Serializable;

public class Login implements Serializable {
    private String usuario;
    private String passwd;

    public Login(String usuario, String passwd) {
        this.usuario = usuario;
        this.passwd = passwd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Login{" +
                "usuario='" + usuario + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
