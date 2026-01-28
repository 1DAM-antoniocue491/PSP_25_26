package org.mm.UD3.ServidorDNS_Repaso;

import org.mm.UD3.Recuperación_HundirLaFlota.ClienteHandler;
import org.mm.UD3.Tarea2.HiloServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DnsServer {
    private static final int PORT = 5555;
    private static ServerSocket serverSocket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static BufferedReader inputClient;
    private static PrintWriter outputClient;
    private static Enrutamiento enrutamiento = new Enrutamiento();
    private static int numConexionesSospechosas = 0;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);

            boolean seguridad = true;
            while (seguridad){
                Socket clientSocket = serverSocket.accept();
                createFlujo(clientSocket);
                System.out.println("Cliente conectado y flujo abierto");

                Token token = (Token) in.readObject();
                if (token.getCodCliete() == 9999999999L) {
                    AdminClient adminClient = new AdminClient(token.getOperacion(), inputClient, outputClient, enrutamiento);
                    adminClient.start();
                } else if (token.getCodCliete() == 1111111111L) {
                    UserClient userClient = new UserClient(token.getOperacion(), inputClient, outputClient, out, enrutamiento);
                    userClient.start();
                } else {
                    outputClient.println("Atención!! Usted no es un cliente autorizado para el uso del servidor");
                    numConexionesSospechosas++;
                }

                if (numConexionesSospechosas>3) {
                    seguridad = false;
                    System.out.println("ATTACK DETECTED!!! Se ha detectado un posible ataque al servidor con lo que se procederá a su finalización");
                }
            }


        } catch (IOException e) {
            System.err.println("Error: ");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }
    }

    private static void createFlujo(Socket client) throws IOException {
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());

        outputClient = new PrintWriter(client.getOutputStream(), true);
        inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
}
