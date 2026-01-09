package org.mm.UD3.Tarea3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminClient extends Thread {
    private Buffer buffer;
    private String opcion;
    private Socket clientSocket;

    public AdminClient(Buffer buffer, String opcion, Socket clientSocket) {
        this.buffer = buffer;
        this.opcion = opcion;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inputClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputClient = new PrintWriter(clientSocket.getOutputStream(), true);

            outputClient.println("Indica el nomgre de dominio: ");
            String dominio = inputClient.readLine();

            boolean resultado = false;
            String mensaje = "";

            if (opcion.equals("REG")) {
                resultado = buffer.registrar(dominio);
                mensaje = "El servidor te ha asignado la IP: " + buffer.getIP(dominio);
            } else if (opcion.equals("DEL")){
                resultado = buffer.eliminar(dominio);
                mensaje = "El servidor a podido eliminar correctamente el dominio indicado.";
            }

            if (resultado)
                outputClient.println(mensaje);
            else
                outputClient.println("Ha ocurrido un error. No se ha podido realizar la operación indicada.");

        } catch (RuntimeException e) {
            System.err.println("RuntimeException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
