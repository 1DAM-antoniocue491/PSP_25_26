package org.mm.UD3.Entregable_24_25_Netflix;

import java.text.Collator;
import java.util.*;

public class Usuario {
    private String nombre;
    private List<String> peliculasVistas = new ArrayList<>();
    private List<String> peliculasNoVistas;

    public Usuario(String nombre, List<String> peliculasNoVistas) {
        this.nombre = nombre;
        this.peliculasNoVistas = peliculasNoVistas;
    }

    public void updatePeliculas(List<String> peliculas) {
        for (String pelicula : peliculas) {
            if (!peliculasNoVistas.contains(pelicula) && !peliculasVistas.contains(pelicula)) {
                peliculasNoVistas.add(pelicula);
            }
        }
    }

    public String verPelicula(String pelicula) {
        if (!peliculasNoVistas.contains(pelicula) && !peliculasVistas.contains(pelicula)) {
            return "EROR: La película " + pelicula + " no está actualmente en la cartelera";
        } else if (peliculasNoVistas.contains(pelicula)) {
            peliculasVistas.add(pelicula);
            peliculasNoVistas.remove(pelicula);
            return "Usted ha seleccionado para ver " + pelicula;
        } else {
            return "La película indicada ya ha sido vista";
        }
    }

    public String printPeliculas() {
        StringBuilder resultado = new StringBuilder("PELÍCULAS:\n");

        List<String> noVistas = new ArrayList<>(peliculasNoVistas);
        List<String> vistas = new ArrayList<>(peliculasVistas);

        Collator collator = Collator.getInstance(new Locale("es", "ES"));
        noVistas.sort(collator);
        vistas.sort(collator);

        for (String pelicula : noVistas) {
            resultado.append(pelicula).append(" -> No vista\n");
        }

        for (String pelicula : vistas) {
            resultado.append(pelicula).append(" -> Vista\n");
        }

        return resultado.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getPeliculasVistas() {
        return peliculasVistas;
    }

    public void setPeliculasVistas(List<String> peliculasVistas) {
        this.peliculasVistas = peliculasVistas;
    }

    public List<String> getPeliculasNoVistas() {
        return peliculasNoVistas;
    }

    public void setPeliculasNoVistas(List<String> peliculasNoVistas) {
        this.peliculasNoVistas = peliculasNoVistas;
    }

}
