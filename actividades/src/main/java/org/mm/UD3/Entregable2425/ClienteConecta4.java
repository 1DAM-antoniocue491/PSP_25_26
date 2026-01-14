package org.mm.UD3.Entregable2425;

import org.mm.UD3.Tarea3.Token;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteConecta4 {
    private final static String IP = "172.22.160.5";
    private final static int PORT = 12345;
    private static ClientID clientID;

    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket(IP, PORT);
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter outputServer = new PrintWriter(serverSocket.getOutputStream(), true);
            Scanner teclado = new Scanner(System.in);

            System.out.println("Se ha conectado a un servidor");

            ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ClientID client = new ClientID();
            out.writeObject(client);

            ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
            clientID = (ClientID) in.readObject();

            System.out.println("Esperando a empezar la partida....");
            while (true) {
                Partida partida = (Partida) in.readObject();
                System.out.println("Partida iniciada");
                if (partida.getGanador() != null) {
                    System.out.println("GANADOR: " + partida.getGanador().toString());
                    partida.imprimirTablero();
                    break;
                }

                if (clientID.getColorFicha() == partida.getTurnoActual())  {
                    partida.imprimirTablero();
                    System.out.println();
                    System.out.print("Indica una columna para posicionar la bola: ");
                    int col = teclado.nextInt();

                    outputServer.println(col);
                }

                System.out.println(inputServer.readLine());
            }

            System.out.println("Se acabó la partida.");

        } catch (Exception e) {
            System.err.println("Exception: " + e.getCause());
        }
    }
}
