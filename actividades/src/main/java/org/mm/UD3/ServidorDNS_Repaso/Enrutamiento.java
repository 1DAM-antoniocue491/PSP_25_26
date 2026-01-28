package org.mm.UD3.ServidorDNS_Repaso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Enrutamiento implements Serializable {
    private HashMap<String, Integer> tablaEnrutamiento = new HashMap<>();
    private int ultIP;

    public boolean addDominio(String dominio) {
        if (!tablaEnrutamiento.containsKey(dominio) && getSiguienteIp()<15) {
            ultIP = getSiguienteIp();
            tablaEnrutamiento.put(dominio, ultIP);
            return true;
        }
        return false;
    }

    public String getUltimaAsignacion(String dominio) {
        return "El dominio " + dominio + " ha sido asignado a la ip 192.168.0." + ultIP;
    }

    private int getSiguienteIp() {
        List<Integer> listaIps = tablaEnrutamiento.values().stream().toList();
        listaIps.stream().sorted();

        int siguiente = 1;
        while (listaIps.contains(siguiente)) {
            siguiente++;
        }

        return siguiente;
    }

    public boolean delDominio(String dominio) {
        if (tablaEnrutamiento.containsKey(dominio)) {
            tablaEnrutamiento.remove(dominio);
            return true;
        }
        return false;
    }

    public String conDominio(String dominio) {
        if (tablaEnrutamiento.containsKey(dominio)) {
            int ip = tablaEnrutamiento.get(dominio);
            return "Conexión correcta a " + dominio + " y en la dirección IP: 192.168.0." + ip;
        }

        return "ERROR HTTP 404 (Page Not Found): " + dominio + " no encontrado en el servidor DNS";
    }

    public HashMap<String, Integer> getTablaEnrutamiento() {
        return tablaEnrutamiento;
    }

    public void setTablaEnrutamiento(HashMap<String, Integer> tablaEnrutamiento) {
        this.tablaEnrutamiento = tablaEnrutamiento;
    }

    public int getUltIP() {
        return ultIP;
    }

    public void setUltIP(int ultIP) {
        this.ultIP = ultIP;
    }

    @Override
    public String toString() {
        List<String> dominios = tablaEnrutamiento.keySet().stream().sorted().toList();
        String resultado = "TABLA DE ENRUTAMIENTO:\n";
        for (String dominio : dominios) {
            resultado += "Dominio: " + dominio + " -> IP: 192.168.0." + tablaEnrutamiento.get(dominio) + "\n";
        }
        return resultado;
    }
}
