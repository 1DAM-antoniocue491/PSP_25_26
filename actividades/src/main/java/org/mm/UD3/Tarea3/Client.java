package org.mm.UD3.Tarea3;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private final static String IP = "172.22.160.5";
    private final static int PORT = 5555;

    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket(IP, PORT);
            Scanner teclado = new Scanner(System.in);

            System.out.println("Cliente conectado al servidor");
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter outputServer = new PrintWriter(serverSocket.getOutputStream(), true);

            ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

            Token token = generarToken();
            out.writeObject(token);

            if (token.getClientCode() != 1111111111L && token.getClientCode() != 9999999999L) {
                System.out.println(inputServer.readLine());
            } else if (token.getOperacion().equals("ALL")) {
                TablaEnrutamiento tablaEnrutamiento = (TablaEnrutamiento) in.readObject();
                HashMap<String, String> tabla = tablaEnrutamiento.getTablaEnrutamiento();

                for (Map.Entry<String, String> enrutamiento : tabla.entrySet()) {
                    System.out.println(enrutamiento.getKey() + " -> " + enrutamiento.getValue());
                }
            } else {
                System.out.print(inputServer.readLine());
                String dominio = teclado.next();
                outputServer.println(dominio);

                System.out.println(inputServer.readLine());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Token generarToken () {
        Scanner teclado = new Scanner(System.in);

        int idOption = (int) (Math.random() * 3);
        Long id = 0L;
        String tokenOption = "";

        switch (idOption) {
            case 0: {
                id = 1111111111L;
                System.out.print("Eres un usuario administrador.\nIndica una opcion (REG/DEL): ");
                tokenOption = teclado.next();
                break;
            }
            case 1: {
                id = 9999999999L;
                System.out.print("Eres un usuario cliente.\nIndica una opcion (CON/ALL): ");
                tokenOption = teclado.next();
                break;
            }
            case 2: {
                id = 7018413456L;
                System.out.print("Eres un usuario cliente.\nIndica una opcion (CON/ALL): ");
                tokenOption = teclado.next();
                break;
            }
        }

        return new Token(id, tokenOption);
    }
}
