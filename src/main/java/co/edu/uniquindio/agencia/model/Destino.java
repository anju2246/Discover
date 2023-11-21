package co.edu.uniquindio.agencia.model;

import lombok.*;

import java.util.ArrayList;

@Data
public class Destino {

    private String nombre;

    private String descripcion;

    private ArrayList<String> rutasImagenes;

    private Clima clima;

}
