package co.edu.icesi.innlab.cacaoapp.models;

/**
 * Created by 1130613425 on 26/09/2016.
 */

public class Grupo {

    public int cantidadCacaos;
    public int cantidadPuntos;
    public String nombre;

    // Requerido para serializacion
    public Grupo(){}

    // Para la creación de grupos
    public Grupo(String nombre){
        this.nombre = nombre;
        this.cantidadCacaos = 0;
        this.cantidadPuntos = 0;
    }
}

