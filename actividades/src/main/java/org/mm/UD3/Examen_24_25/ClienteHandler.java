package org.mm.UD3.Examen_24_25;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClienteHandler extends Thread {
    private List<Mensaje> mensajesRecibidos;
    private GestorMensajes gestorMensajes;
    private ObjectOutputStream outputObjectClient;
    private ObjectInputStream inputObjetClient;
    private Usuario usuario;

    public ClienteHandler(GestorMensajes gestorMensajes, Socket clienteSocket) {
        this.gestorMensajes = gestorMensajes;
        try {
            outputObjectClient = new ObjectOutputStream(clienteSocket.getOutputStream());
            inputObjetClient = new ObjectInputStream(clienteSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexiones establecidas");
            usuario = (Usuario) inputObjetClient.readObject();
            System.out.println("Usuario recibido");

            System.out.println("Empieza la gestión del usuario");
            String nombreUsuario = usuario.getNombre();
            gestorMensajes.registrarUsuario(nombreUsuario, this);

            mensajesRecibidos = gestorMensajes.getMensajesPendientes(nombreUsuario);
            gestorMensajes.eliminarUsuarioMensajesPendientes(nombreUsuario);

            Respuesta respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "Tienes " + mensajesRecibidos.size() + " mensaje(s) pendiente(s).", mensajesRecibidos);
            outputObjectClient.writeObject(respuesta);

            while (true) {
                Comando comando = (Comando) inputObjetClient.readObject();
                switch (comando.getTipo()) {
                    case SEND -> {
                        handleSend(comando);
                        break;
                    }
                    case VIEW -> {
                        handleView();
                        break;
                    }
                    case CHANGE -> {
                        handleChange(comando);
                        break;
                    }
                    case EXIT -> {
                        handleExit();
                        break;
                    }
                }
            }
        } catch (RuntimeException e) {
            System.err.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    private void handleSend(Comando comando) throws IOException {
        String contenidoMensaje = comando.getContenido();

        Respuesta respuesta;

        List<String> usuariosConectados = gestorMensajes.getOnlineUsers();
        Mensaje mensaje = new Mensaje(usuario.getNombre(), usuario.getDestinatarioPreferido(), contenidoMensaje, LocalDateTime.now());

        if (usuariosConectados.contains(usuario.getDestinatarioPreferido())) {
            ClienteHandler clienteHandler = gestorMensajes.getClientHandlerUsuario(usuario.getDestinatarioPreferido());
            clienteHandler.deliverMessage(mensaje);
            respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "El mensaje ha sido enviado con exito");
        } else {
            gestorMensajes.addMensajesPendientes(usuario.getDestinatarioPreferido(), mensaje);
            respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "El mensaje se le ha añadido a la cola de espera");
        }

        outputObjectClient.writeObject(respuesta);
    }

    private void handleView() {
        try {
            List<String> usuariosConectados = gestorMensajes.getOnlineUsers();
            if (mensajesRecibidos.isEmpty()) {
                Respuesta respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "No tienes mensajes");
                outputObjectClient.writeObject(respuesta);
            } else {
                for (Mensaje mensaje : mensajesRecibidos) {
                    if (!mensaje.isLeido()) {
                        mensaje.setLeido(true);
                        if (usuariosConectados.contains(mensaje.getRemitente())) {
                            Mensaje mensajeRespuesta = new Mensaje(usuario.getNombre(), mensaje.getRemitente(), "Tu mensaje a " + usuario.getNombre() + " ha sido leido", LocalDateTime.now());
                            gestorMensajes.addMensajesPendientes(mensaje.getRemitente(), mensajeRespuesta);
                        }
                    }
                }
                Respuesta respuesta = new Respuesta(TipoRespuesta.LISTA_MENSAJES, "Bandeja de entrada", mensajesRecibidos);
                outputObjectClient.writeObject(respuesta);
            }
        } catch (RuntimeException e) {
            System.err.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    private void handleChange (Comando comando) {
        String contenido = comando.getContenido();
        usuario.setDestinatarioPreferido(contenido);
        try {
            Respuesta respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "Destinatario freferido actualizado a: " + contenido);
            outputObjectClient.writeObject(respuesta);
        } catch (RuntimeException e) {
            System.err.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    private void handleExit() {
        try {
            Respuesta respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "Desconectado....");
            outputObjectClient.writeObject(respuesta);
        } catch (RuntimeException e) {
            System.err.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void deliverMessage(Mensaje mensaje) {
        mensajesRecibidos.add(mensaje);
        try {
            Respuesta respuesta = new Respuesta(TipoRespuesta.NOTIFICACION, "Nuevo mensaje de " + mensaje.getRemitente());
            outputObjectClient.writeObject(respuesta);
        } catch (RuntimeException e) {
            System.err.println("Runtime Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
