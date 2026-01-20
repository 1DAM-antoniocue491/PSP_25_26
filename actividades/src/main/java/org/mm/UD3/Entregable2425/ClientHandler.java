package org.mm.UD3.Entregable2425;

import javax.sql.rowset.serial.SerialStruct;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private BufferedReader inputClient;
    private PrintWriter outputClient;
    private Partida partida;
    private ColorFicha colorFicha;
    private ClientHandler oponente;
    private ClientID clientID;
    private ObjectOutputStream out;

    public ClientHandler(Socket clienteSocket, ClientID clientID, ColorFicha colorFicha) {
        this.clientID = clientID;
        this.colorFicha = colorFicha;
        try {
            this.inputClient = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            this.outputClient = new PrintWriter(clienteSocket.getOutputStream(), true);
            this.out = new ObjectOutputStream(clienteSocket.getOutputStream());
            out.writeObject(this.clientID);
            System.out.println("ClientID enviado al cliente");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void setOponente(ClientHandler oponente) {
        this.oponente = oponente;
    }

    public void sendMessageToClient(String message) {
        try {
            outputClient.println(message);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        clientID.setColorFicha(colorFicha);
        clientID.setIdPartida(partida.getIdPartida());
        System.out.println(partida.getGanador());

        while (partida.getGanador() == null) {
            try {
                out.writeObject(partida);
                int col = Integer.parseInt(inputClient.readLine());
                boolean resultado = partida.realizarMovimiento(col, colorFicha);

                if (resultado) {
                    outputClient.println("Se ha realizado correctamente el movimiento.");
                    oponente.sendMessageToClient("Tu contrincante ha realizado un movimiento");
                } else {
                    outputClient.println("No se ha realizado correctamente el movimiento.");
                }

                outputClient.println("Estado actual de la partida: EN JUEGO");
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }


        System.out.println("Se ha terminado la partida.");
        System.out.println("GANADOR: " + colorFicha.toString());
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public ColorFicha getColorFicha() {
        return colorFicha;
    }

    public void setColorFicha(ColorFicha colorFicha) {
        this.colorFicha = colorFicha;
    }

    public ClientHandler getOponente() {
        return oponente;
    }

    public ClientID getClientID() {
        return clientID;
    }

    public void setClientID(ClientID clientID) {
        this.clientID = clientID;
    }
}
