package org.mm.UD3.Tarea1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private static final int PORT = 5555;
    private static final int numAleatorio = (int) (Math.random() * 100) + 1;
    private static final int totalJugadas = 10;
    private static int numJugadas = 0;
    private static boolean result = false;
    private static HashMap<Integer, Integer> intentos = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);
            System.out.println("Numero: " + numAleatorio);

            printSeparador();
            while (totalJugadas > numJugadas || !result){
                Socket clientSocket = serverSocket.accept();
                BufferedReader inputClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputClient = new PrintWriter(clientSocket.getOutputStream(), true);

                int idCliente = Integer.parseInt(inputClient.readLine());

                String respuesta = "";
                if (checkIntentos(idCliente)) {
                    System.out.println("Conectado el cliente con id: " + idCliente);

                    int num = Integer.parseInt(inputClient.readLine());

                    if (num == numAleatorio) {
                        respuesta = "El cliente ha acertado el número. Este era el " + numAleatorio;
                    } else {
                        respuesta = getPista(num);
                    }

                    numJugadas++;
                } else {
                    System.out.println("El usuario no puede acceder ya que ha superado la cantidad máxima de posibilidades.");
                }

                outputClient.println(respuesta);
                outputClient.flush();

                clientSocket.close();
                System.out.println("Se ha terminado la conexión con este cliente.");
                printSeparador();
            }

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error: " );
        }
    }

    private static void printSeparador() {
        System.out.println("===================================");
    }

    private static boolean checkIntentos(int idCliente) {
        if (intentos.containsKey(idCliente)) {
            int numIntentos = intentos.get(idCliente);
            if (numIntentos > 2) {
                return false;
            } else {
                numIntentos++;
                intentos.put(idCliente, numIntentos);
            }
        } else {
            intentos.put(idCliente, 1);
        }

        return true;
    }

    private static String getPista(int value) {
        if (value > numAleatorio)
            return "El número es mayor que el valor aleatorio almacenado.";
        else
            return "El número es menor que el valor aleatorio almacenado.";
    }
}
