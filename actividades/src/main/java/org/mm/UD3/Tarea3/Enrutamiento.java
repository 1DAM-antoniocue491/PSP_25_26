package org.mm.UD3.Tarea3;

public class Enrutamiento {
    private String dominio;
    private String ip;

    public Enrutamiento(String dominio, String ip) {
        this.dominio = dominio;
        this.ip = ip;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "IP{" +
                "dominio='" + dominio + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
