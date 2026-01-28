package org.mm.UD3.ServidorDNS_Repaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class UserClient extends Thread {
    private String operacion;
    private BufferedReader inputClient;
    private PrintWriter outputClient;
    private ObjectOutputStream out;
    private Enrutamiento enrutamiento;

    public UserClient (String operacion, BufferedReader inputClient, PrintWriter outputClient, ObjectOutputStream out, Enrutamiento enrutamiento) {
        this.operacion = operacion;
        this.inputClient = inputClient;
        this.outputClient = outputClient;
        this.enrutamiento = enrutamiento;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            switch (operacion) {
                case "CON": con(); break;
                case "ALL": all(); break;
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public void con() throws IOException {
        outputClient.println("Indica el nombre de dominio al que se quiere conectar: ");
        String dominio = inputClient.readLine();
        outputClient.println(enrutamiento.conDominio(dominio));
    }

    public void all() throws IOException {
        out.writeObject(enrutamiento);
    }
}
