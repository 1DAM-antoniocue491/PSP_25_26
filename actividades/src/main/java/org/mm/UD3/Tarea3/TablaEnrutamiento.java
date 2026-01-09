package org.mm.UD3.Tarea3;

import java.io.Serializable;
import java.util.HashMap;

public class TablaEnrutamiento implements Serializable {
    private HashMap<String, String> tablaEnrutamiento = new HashMap<>();

    public void addEnrutamiento(Enrutamiento enrutamiento) {
        tablaEnrutamiento.put(enrutamiento.getDominio(), enrutamiento.getIp());
    }

    public HashMap<String, String> getTablaEnrutamiento() {
        return tablaEnrutamiento;
    }

    public void setTablaEnrutamiento(HashMap<String, String> tablaEnrutamiento) {
        this.tablaEnrutamiento = tablaEnrutamiento;
    }

    @Override
    public String toString() {
        return "TablaEnrutamiento{" +
                "tablaEnrutamiento=" + tablaEnrutamiento +
                '}';
    }
}