package org.mm.UD3.Tarea2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class HiloServer extends Thread {
    private static HashMap<Integer, Integer> intentos = new HashMap<>();
    private Socket clientSocket;
    private static int numAleatorio;
    private static int numJugadas = 0;

    public HiloServer (Socket clientSocket, int numAleatorio) {
        this.clientSocket = clientSocket;
        this.numAleatorio = numAleatorio;
    }

    @Override
    public void run() {
        try {
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
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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
