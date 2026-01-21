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
    private Usuario usuario;
    private List<Mensaje> mensajesRecibidos;
    private GestorMensajes gestorMensajes;
    private Socket clienteSocket;
    private ObjectInputStream inputObjetClient;
    private ObjectOutputStream outputObjectClient;

    public ClienteHandler(Usuario usuario, GestorMensajes gestorMensajes, Socket clienteSocket) {
        this.usuario = usuario;
        this.gestorMensajes = gestorMensajes;
        this.clienteSocket = clienteSocket;
        try {
            inputObjetClient = new ObjectInputStream(clienteSocket.getInputStream());
            outputObjectClient = new ObjectOutputStream(clienteSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
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

    private void handleSend(Comando comando) {
        String contenidoMensaje = comando.getContenido();

        List<String> usuariosConectados = gestorMensajes.getOnlineUsers();
        if (usuariosConectados.contains(usuario.getNombre())) {
            Mensaje mensaje = new Mensaje(usuario.getNombre(), usuario.getDestinatarioPreferido(), contenidoMensaje, LocalDateTime.now());
            ClienteHandler clienteHandler = gestorMensajes.getClientHandlerUsuario(usuario.getNombre());
            clienteHandler.deliverMessage(mensaje);
        }

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
                            gestorMensajes.addMensajesPendientes(mensaje.getRemitente(), new ArrayList<>((Collection) mensajeRespuesta));
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
