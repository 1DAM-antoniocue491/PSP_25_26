package org.mm.UD2.Tarea4;

import java.util.List;

public class Productor extends Thread {
    private int id;
    private Buffer controller;
    private boolean tipo;

    public Productor(int id, Buffer controller, boolean tipo) {
        this.id = id;
        this.controller = controller;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        while (true) {
            controller.generar(tipo, id);
        }
    }
}
