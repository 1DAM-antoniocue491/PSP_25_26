package org.mm.UD3.ServidorDNS_Repaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminClient extends Thread {
    private String operacion;
    private BufferedReader inputClient;
    private PrintWriter outputClient;
    private Enrutamiento enrutamiento;


    public AdminClient(String operacion, BufferedReader inputClient, PrintWriter outputClient, Enrutamiento enrutamiento) {
        this.operacion = operacion;
        this.inputClient = inputClient;
        this.outputClient = outputClient;
        this.enrutamiento = enrutamiento;
    }

    @Override
    public void run() {
        try {
            switch (operacion) {
                case "REG": reg(); break;
                case "DEL": del(); break;
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private void reg() throws IOException {
        outputClient.println("Indica el nombre de dominio a registrar: ");
        String dominio = inputClient.readLine();
        if (enrutamiento.addDominio(dominio))
            outputClient.println(enrutamiento.getUltimaAsignacion(dominio));
        else
            outputClient.println("No hay ips suficientes para almacenar este dominio");
    }

    private void del() throws IOException {
        outputClient.println("Indica el nombre de dominio a eliminar: ");
        String dominio = inputClient.readLine();
        if (enrutamiento.delDominio(dominio))
            outputClient.println("Se ha podido eliminar correctamente el dominio");
        else
            outputClient.println("No se ha podido eliminar correctamente el dominio");
    }
}
