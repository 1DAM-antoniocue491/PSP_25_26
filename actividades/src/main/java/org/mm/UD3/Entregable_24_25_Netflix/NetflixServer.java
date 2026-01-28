package org.mm.UD3.Entregable_24_25_Netflix;

import org.mm.UD3.Tarea2.HiloServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetflixServer {
    private static final int PORT = 6000;
    private static ObjectInputStream in;
    private static BufferedReader inputClient;
    private static PrintWriter outputClient;
    private static List<String> peliculas = new ArrayList<>();
    private static HashMap<String, Usuario> usuarios = new HashMap<>();


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Se ha conectado un cliente");
                createFlujo(clientSocket);

                Login login = (Login) in.readObject();
                if (login.getUsuario().equals("ADMIN") && login.getPasswd().equals("ADMIN")) {
                    // Admin
                    outputClient.println(true);
                    clientAdmin();
                } else if (login.getPasswd().equals("USER")) {
                    // User
                    outputClient.println(true);
                    clientUser(login.getUsuario());
                } else {
                    outputClient.println(false);
                    Login login2 = (Login) in.readObject();
                    if (login2.getUsuario().equals("ADMIN") && login2.getPasswd().equals("ADMIN")) {
                        // Admin
                        outputClient.println(true);
                        clientAdmin();
                    } else if (login2.getPasswd().equals("USER")) {
                        // User
                        outputClient.println(true);
                        clientUser(login2.getUsuario());
                    } else {
                        outputClient.println(false);
                        serverSocket.close();
                    }
                }

                clientSocket.close();
            }

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        }
    }

    private static void createFlujo(Socket client) throws IOException{
        in = new ObjectInputStream(client.getInputStream());

        inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputClient = new PrintWriter(client.getOutputStream(), true);
    }

    private static void clientAdmin() throws IOException {
        System.out.println("Cliente administrador conectado");
        String accion = inputClient.readLine();
        switch (accion) {
            case "ALTA" -> {
                boolean reiniciar = true;
                while (reiniciar) {
                    outputClient.println("Indica el nombre de de la película a registrar: ");
                    String nombrePelicula = inputClient.readLine();
                    if (!peliculas.contains(nombrePelicula)) {
                        peliculas.add(nombrePelicula);
                        outputClient.println("Película ha sido añadida correctamente");
                    } else
                        outputClient.println("La película ya estaba registrada");

                    outputClient.println("Quieres volver a registrar una película? ");
                    String confirmacion = inputClient.readLine();

                    if (!confirmacion.equals("si"))
                        reiniciar = false;
                }
            }
            case "BAJA" -> {
                outputClient.println("Indica el nombre de la película a eliminar: ");
                String nombrePelicula = inputClient.readLine();
                if (peliculas.contains(nombrePelicula)) {
                    peliculas.remove(nombrePelicula);
                    outputClient.println("Película ha sido eliminada correctamente");
                } else
                    outputClient.println("La película no estaba registrada");
            }
        }
    }

    private static void clientUser(String nombreUsuario) throws IOException {
        System.out.println("Cliente usuario conectado");
        Usuario usuario;
        if (usuarios.containsKey(nombreUsuario)) {
            usuario = usuarios.get(nombreUsuario);
            usuario.updatePeliculas(peliculas);
        } else
            usuario = new Usuario(nombreUsuario, peliculas);

        String accion = inputClient.readLine();
        switch (accion) {
            case "VER" -> {
                outputClient.println("Indica el nombre de de la película que quieres ver: ");
                String nombrePelicula = inputClient.readLine();
                outputClient.println(usuario.verPelicula(nombrePelicula));
            }
            case "BUSCAR" -> {
                outputClient.println(usuario);
                outputClient.println("Indica el nombre de de la película que quieres ver: ");
                String nombrePelicula = inputClient.readLine();
                outputClient.println(usuario.verPelicula(nombrePelicula));
            }
        }

        usuarios.put(nombreUsuario, usuario);
    }
}
