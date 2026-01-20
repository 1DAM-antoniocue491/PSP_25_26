package org.mm.UD3.Entregable2425;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Partida implements Serializable {
    private Character[][] tablero = new Character[6][7];
    private ColorFicha turnoActual;
    private boolean partidaTerminada;
    private int idPartida;
    private static int idPartidaGeneral = 1;
    private ColorFicha ganador = null;

    public Partida() {
        this.idPartida = idPartidaGeneral;
        idPartidaGeneral++;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = '.';
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean verificarVictoria(int fila, int col, char ficha) {
        int verticalArriba = contarEnDireccion(fila, col, -1, 0, ficha);
        int verticalAbajo = contarEnDireccion(fila, col, 1, 0, ficha);
        int horizontalIzquierdo = contarEnDireccion(fila, col, 0, -1, ficha);
        int horizontalDerecho = contarEnDireccion(fila, col, 0, 1, ficha);
        int diagonalArribaIzquierda = contarEnDireccion(fila, col, -1, -1, ficha);
        int diagonalAbajoDerecha = contarEnDireccion(fila, col, 1, 1, ficha);
        int diagonalAbajoIzquierda = contarEnDireccion(fila, col, 1, -1, ficha);
        int diagonalArribaDerecha = contarEnDireccion(fila, col, -1, -1, ficha);
        List<Integer> direcciones = new ArrayList<>();

        direcciones.add(verticalArriba);
        direcciones.add(verticalAbajo);
        direcciones.add(horizontalIzquierdo);
        direcciones.add(horizontalDerecho);
        direcciones.add(diagonalArribaIzquierda);
        direcciones.add(diagonalAbajoDerecha);
        direcciones.add(diagonalAbajoIzquierda);
        direcciones.add(diagonalArribaDerecha);

        if (direcciones.contains(4))
            return true;

        return false;
    }

    private int contarEnDireccion(int fila, int col, int df, int dc, char ficha) {
        int contador = 0;

        List<Character> linea = new ArrayList<>();

        if (df == -1 && dc == 0) {
            // vertical arriba
            while (fila > 0) {
                linea.add(tablero[fila][col]);
                fila--;
            }
        } else if (df == 1 && dc == 0) {
            // vertical abajo
            while (fila < tablero.length) {
                linea.add(tablero[fila][col]);
                fila++;
            }
        } else if (df == 0 && dc == -1) {
            // horizontal izquierda
            while (col > 0) {
                linea.add(tablero[fila][col]);
                col--;
            }
        } else if (df == 0 && dc == 1) {
            // horizontal derecha
            while (col < tablero[0].length) {
                linea.add(tablero[fila][col]);
                col++;
            }
        } else if (df == -1 && dc == -1) {
            // diagonal arriba ezquierda
            while (fila > 0 && col > 0) {
                linea.add(tablero[fila][col]);
                fila--;
                col--;
            }
        } else if (df == 1 && dc == 1) {
            // diagonal abajo derecha
            while (fila < tablero.length && col < tablero[0].length) {
                linea.add(tablero[fila][col]);
                fila++;
                col++;
            }
        } else if (df == 1 && dc == -1) {
            // diagonal abajo izquierda
            while (fila < tablero.length && col > 0) {
                linea.add(tablero[fila][col]);
                fila++;
                col--;
            }
        } else if (df == -1 && dc == 1) {
            // diagonal arriba derecha
            while (fila > 0 && col < tablero[0].length) {
                linea.add(tablero[fila][col]);
                fila--;
                col++;
            }
        } else {
            return 0;
        }

        boolean sumar = true;
        for (Character pos : linea) {
            if (pos == ficha && sumar) {
                contador++;
            } else
                sumar = false;
        }

        return contador;
    }

    public boolean realizarMovimiento (int col, ColorFicha fichaRecibida) {
        int fila = 0;
        if (tablero[fila][col] == '.') {
            while (fila < tablero.length && tablero[fila][col] == '.') {
                fila++;
            }
        } else
            return false;

        fila--;

        char ficha;
        if (fichaRecibida == ColorFicha.ROJO)
            ficha = 'X';
        else
            ficha = 'O';

        tablero[fila][col] = ficha;

        if (verificarVictoria(fila, col, ficha)) {
            ganador = fichaRecibida;
        }

        if (turnoActual == ColorFicha.ROJO)
            turnoActual = ColorFicha.VERDE;
        else
            turnoActual = ColorFicha.ROJO;

        return true;
    }

    public Character[][] getTablero() {
        return tablero;
    }

    public void setTablero(Character[][] tablero) {
        this.tablero = tablero;
    }

    public ColorFicha getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(ColorFicha turnoActual) {
        this.turnoActual = turnoActual;
    }

    public boolean isPartidaTerminada() {
        return partidaTerminada;
    }

    public void setPartidaTerminada(boolean partidaTerminada) {
        this.partidaTerminada = partidaTerminada;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public ColorFicha getGanador() {
        return ganador;
    }

    public void setGanador(ColorFicha ganador) {
        this.ganador = ganador;
    }
}
