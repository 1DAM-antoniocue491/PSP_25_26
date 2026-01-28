package org.mm.UD3.Recuperación_HundirLaFlota;

import java.util.HashMap;
import java.util.List;

public class ManejadorPartida {
    private static int idGeneral = 1;
    private int idPartida;
    private HashMap<ClienteHandler, ClienteHandler> jugadores = new HashMap<>();

    public ManejadorPartida() {
        idPartida = idGeneral;
        idGeneral++;
    }

    public void setJugadores(ClienteHandler jugador1, ClienteHandler jugador2) {
        jugadores.put(jugador1, jugador2);
    }

    public void realizarAtaque(Tablero tablero, Posicion p) {
        List<Barco> barcos = tablero.getBarcos();
        Resultado resultado = Resultado.AGUA;
        for (Barco barco : barcos) {
            if (barco.checkImpacto(p) == Resultado.TOCADO) {
                resultado = Resultado.TOCADO;
            } else if (barco.checkImpacto(p) == Resultado.HUNDIDO) {
                resultado = Resultado.HUNDIDO;
            }
        }
    }
}