package org.mm.UD3.Examen_24_25;

public class Usuario {
    private String nombre;
    private String destinatarioPreferido;
    private static Long idConexionGeneral = 1L;
    private Long idConexion;

    public Usuario(String nombre, String destinatarioPreferido) {
        this.nombre = nombre;
        this.destinatarioPreferido = destinatarioPreferido;
        this.idConexion = idConexionGeneral;
        idConexionGeneral++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestinatarioPreferido() {
        return destinatarioPreferido;
    }

    public void setDestinatarioPreferido(String destinatarioPreferido) {
        this.destinatarioPreferido = destinatarioPreferido;
    }

    public Long getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(Long idConexion) {
        this.idConexion = idConexion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", destinatarioPreferido='" + destinatarioPreferido + '\'' +
                ", idConexion=" + idConexion +
                '}';
    }
}
