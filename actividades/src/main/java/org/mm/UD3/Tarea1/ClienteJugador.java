package org.mm.UD3.Tarea1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJugador {
    private final static String IP = "172.22.160.5";
    private final static int PORT = 5555;

    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket(IP, PORT);
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter outputServer = new PrintWriter(serverSocket.getOutputStream(), true);
            Scanner teclado = new Scanner(System.in);

            int idCliente = (int) (Math.random() * 5) + 1;
            System.out.println("ID: " + idCliente);
            outputServer.println(idCliente);
            outputServer.flush();

            System.out.print("Indica un número entre 1 y 100: ");
            int num = teclado.nextInt();
            outputServer.println(num);
            outputServer.flush();

            System.out.println(inputServer.readLine());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
