package org.mm.UD3.ServidorDNS_Repaso;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class DnsClient {
    private final static String IP = "localhost";
    private final static int PORT = 5555;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static BufferedReader inputClient;
    private static PrintWriter outputClient;
    private static Socket serverSocket;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            serverSocket = new Socket(IP, PORT);
            createFlujo(serverSocket);

            System.out.println("1. Administrador");
            System.out.println("2. Usuario");
            System.out.println("3. Spyware");
            System.out.print("Indique una opción: ");
            int opcion = teclado.nextInt();
            long codCliente = 0;
            String operacion = "";
            switch (opcion) {
                case 1 -> {
                    codCliente = 9999999999L;
                    System.out.print("Indique una acción (REG/DEL): ");
                    operacion = teclado.next().toUpperCase();
                }
                case 2 -> {
                    codCliente = 1111111111L;
                    System.out.print("Indique una acción (CON/ALL): ");
                    operacion = teclado.next().toUpperCase();
                }
                case 3 -> {
                    codCliente = 2509746524L;
                }
            }

            Token token = new Token(codCliente, operacion);
            out.writeObject(token);

            if (operacion.equals("ALL")) {
                Enrutamiento enrutamiento = (Enrutamiento) in.readObject();
                System.out.println(enrutamiento);
            } else {
                System.out.print(inputClient.readLine());
                outputClient.println(teclado.next());
                System.out.println();
                System.out.println(inputClient.readLine());
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:" + e.getMessage());
            }
        }
    }

    private static void createFlujo(Socket server) throws IOException {
        out = new ObjectOutputStream(server.getOutputStream());
        in = new ObjectInputStream(server.getInputStream());

        outputClient = new PrintWriter(server.getOutputStream(), true);
        inputClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }
}
