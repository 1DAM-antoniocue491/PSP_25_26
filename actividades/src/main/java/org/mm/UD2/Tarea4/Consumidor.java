package org.mm.UD2.Tarea4;

import java.util.List;

public class Consumidor extends Thread{
    private int id;
    private Buffer controller;

    public Consumidor(int id, Buffer controller) {
        this.id = id;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            controller.obtener(id);
        }
    }
}
