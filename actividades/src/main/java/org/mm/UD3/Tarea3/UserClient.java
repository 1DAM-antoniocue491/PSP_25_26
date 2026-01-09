package org.mm.UD3.Tarea3;

import java.io.*;
import java.net.Socket;

public class UserClient extends Thread {
    private Buffer buffer;
    private String opcion;
    private Socket clientSocket;
    private ObjectOutputStream outputObjectClient;

    public UserClient(Buffer buffer, String opcion, Socket clientSocket, ObjectOutputStream outputObjectClient) {
        this.buffer = buffer;
        this.opcion = opcion;
        this.clientSocket = clientSocket;
        this.outputObjectClient = outputObjectClient;
    }

    @Override
    public void run() {
        try {
            BufferedReader inputClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputClient = new PrintWriter(clientSocket.getOutputStream(), true);

            boolean resultado = false;
            String mensaje = "";
            TablaEnrutamiento tablaEnrutamiento = null;
            String dominio = "";

            if (opcion.equals("CON")) {
                outputClient.println("Indica el nomgre de dominio: ");
                dominio = inputClient.readLine();

                resultado = buffer.conectar(dominio);
                if (resultado)
                    mensaje = "Conexión correcta a " + dominio + " y en la dirección IP: " + buffer.getIP(dominio);
            } else if (opcion.equals("ALL")){
                tablaEnrutamiento = buffer.obtenerTodo();
            }

            if (opcion.equals("ALL")) {
                outputObjectClient.writeObject(tablaEnrutamiento);
            } else if (resultado)
                outputClient.println(mensaje);
            else
                outputClient.println("ERROR HTTP 404 (Page Not Found): " + dominio + " no encontrado en el servidor DNS.");

        } catch (RuntimeException e) {
            System.err.println("RuntimeException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
