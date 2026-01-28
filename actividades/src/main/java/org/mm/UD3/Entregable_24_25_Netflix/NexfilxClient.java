package org.mm.UD3.Entregable_24_25_Netflix;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NexfilxClient {
    private final static String IP = "localhost";
    private final static int PORT = 6000;
    private static ObjectOutputStream out;
    private static BufferedReader inputClient;
    private static PrintWriter outputClient;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Socket serverSocket = new Socket(IP, PORT);
            createFlujo(serverSocket);

            Login login = iniciarSesion();
            out.writeObject(login);
            boolean inicioSesion = Boolean.parseBoolean(inputClient.readLine());
            if (inicioSesion) {
                if (login.getUsuario().equals("ADMIN")) {
                    clientAdmin();
                } else {
                    clientUser();
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos");
                Login login2 = iniciarSesion();
                out.writeObject(login2);
                boolean inicioSesion2 = Boolean.parseBoolean(inputClient.readLine());
                if (inicioSesion2) {
                    if (login.getUsuario().equals("ADMIN")) {
                        clientAdmin();
                    } else {
                        clientUser();
                    }
                } else {
                    System.out.println("No se ha podido iniciar sesión");
                    serverSocket.close();
                }
            }
            serverSocket.close();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static void createFlujo(Socket server) throws IOException{
        out = new ObjectOutputStream(server.getOutputStream());

        inputClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
        outputClient = new PrintWriter(server.getOutputStream(), true);
    }

    private static Login iniciarSesion() {
        System.out.print("Indica el usuario: ");
        String usuario = teclado.nextLine();
        System.out.print("Indica la contraseña: ");
        String passwd = teclado.nextLine();

        return new Login(usuario, passwd);
    }

    private static void clientAdmin() throws IOException {
        System.out.print("Indica la acción a realizar (ALTA/BAJA): ");
        String accion = teclado.nextLine();

        outputClient.println(accion);

        if (accion.equals("ALTA")) {
            boolean seguir = true;
            while (seguir) {
                System.out.print(inputClient.readLine());
                outputClient.println(teclado.nextLine());

                System.out.println(inputClient.readLine());

                System.out.print(inputClient.readLine());
                String opcion = teclado.nextLine();
                outputClient.println(opcion);

                if (!opcion.equals("si"))
                     seguir = false;
            }
        } else {
            System.out.print(inputClient.readLine());
            outputClient.println(teclado.nextLine());

            System.out.println(inputClient.readLine());
        }
    }

    private static void clientUser() throws IOException {
        System.out.print("Indica la acción a realizar (VER/BUSCAR): ");
        String accion = teclado.nextLine();

        outputClient.println(accion);

        if (accion.equals("BUSCAR")) {
            System.out.print(inputClient.readLine());
        }

        System.out.print(inputClient.readLine());
        outputClient.println(teclado.nextLine());

        System.out.println(inputClient.readLine());
    }
}
