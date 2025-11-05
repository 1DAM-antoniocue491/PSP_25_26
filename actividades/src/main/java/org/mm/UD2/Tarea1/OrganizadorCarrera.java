package org.mm.UD2.Tarea1;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrganizadorCarrera {
    private AtomicBoolean corredorActivo = new AtomicBoolean(false);
    private LocalTime inicio = null;

    public void execute(int hilo) {
        while (corredorActivo.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        corredorActivo.set(true);

        System.out.println("Soy el hilo " + hilo + ", ejecutando...");

        inicio = LocalTime.now();
        int random = (int) (Math.random() * 11);

        boolean funcionando = false;
        while (!funcionando) {
            funcionando = segundosPasados(random);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Terminé. Paso el testigo al siguiente hilo");

        corredorActivo.set(false);
        notify();
    }

    private boolean segundosPasados(int second) {
        if ((int) ChronoUnit.SECONDS.between(inicio, LocalTime.now()) >= second) {
            return true;
        }
        return false;
    }

}
