package org.mm.UD3.Tarea3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buffer {
    private HashMap<String, Integer> enrutamiento = new HashMap<>();

    public synchronized boolean registrar(String dominio) {
        if (enrutamiento.size() <= 14) {
            int siguiente = getSiguienteIp();

            enrutamiento.put(dominio, siguiente);
            return true;
        }
        return false;
    }

    private int getSiguienteIp() {
        List<Integer> listaIps = enrutamiento.values().stream().toList();
        listaIps.stream().sorted();

        int siguiente = 1;
        while (listaIps.contains(siguiente)) {
            siguiente++;
        }

        return siguiente;
    }

    public synchronized boolean eliminar(String dominio) {
        if (enrutamiento.containsKey(dominio)) {
            enrutamiento.remove(dominio);
            return true;
        }
        return false;
    }

    public synchronized boolean conectar(String dominio) {
        if (enrutamiento.containsKey(dominio))
            return true;
        else
            return false;
    }

    public synchronized String getIP(String dominio) {
        return "192.168.0." + enrutamiento.get(dominio);
    }

    public synchronized TablaEnrutamiento obtenerTodo() {
        TablaEnrutamiento tablaEnrutamiento = new TablaEnrutamiento();

        for (Map.Entry<String, Integer> dominioIp : enrutamiento.entrySet()) {
            String ip = "192.168.0." + dominioIp.getValue();
            Enrutamiento ipDominio = new Enrutamiento(dominioIp.getKey(), ip);
            tablaEnrutamiento.addEnrutamiento(ipDominio);
        }

        return tablaEnrutamiento;
    }
}
