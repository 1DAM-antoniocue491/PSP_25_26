package org.mm.UD3.Tarea3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Buffer {
    private HashMap<Integer, String> enrutamiento = new HashMap<>();

    public void registrar(String dominio) {
        if (enrutamiento.size() <= 14) {
            int siguiente = getSiguienteIp();

            enrutamiento.put(siguiente, dominio);
        }
    }

    private int getSiguienteIp() {
        List<Integer> listaIps = enrutamiento.keySet().stream().toList();
        listaIps.stream().sorted();

        int siguiente = 1;
        while (listaIps.contains(siguiente)) {
            siguiente++;
        }

        return siguiente;
    }

    public void eliminar(String dominio) {
        if (enrutamiento.containsValue(dominio)) {
            int ip =
        }
    }
}
