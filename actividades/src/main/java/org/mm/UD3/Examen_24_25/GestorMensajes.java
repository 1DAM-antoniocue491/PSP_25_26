package org.mm.UD3.Examen_24_25;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorMensajes implements Serializable {
    private HashMap<String, ClienteHandler> usuariosConectados;
    private HashMap<String, List<Mensaje>> mensajesPendientes;

    public boolean registrarUsuario(String nombre, ClienteHandler clienteHandler) {
        if (usuariosConectados.containsKey(nombre))
            return false;

        usuariosConectados.put(nombre, clienteHandler);

        return true;
    }

    public ClienteHandler getClientHandlerUsuario(String destinatario) {
        return usuariosConectados.get(destinatario);
    }

    public List<Mensaje> getMensajesPendientes(String destinatario) {
        List<Mensaje> mensajes = new ArrayList<>();

        if (mensajesPendientes.containsKey(destinatario))
            mensajes = mensajesPendientes.get(destinatario);

        return mensajes;
    }

    public void addMensajesPendientes (String destinatario, List<Mensaje> mensajes) {
        if (mensajesPendientes.containsKey(destinatario)) {
            List<Mensaje> mensajeList = mensajesPendientes.get(destinatario);
            mensajeList.addAll(mensajes);
            mensajesPendientes.put(destinatario, mensajeList);
        } else
            mensajesPendientes.put(destinatario, mensajes);
    }

    public void eliminarUsuarioMensajesPendientes(String destinatario) {
        mensajesPendientes.remove(destinatario);
    }

    public void removeOnlineUser(String nombre) {
        usuariosConectados.remove(nombre);
    }

    public List<String> getOnlineUsers() {
        return usuariosConectados.keySet().stream().toList();
    }
}
