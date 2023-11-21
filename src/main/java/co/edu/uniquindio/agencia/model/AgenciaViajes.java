package co.edu.uniquindio.agencia.model;

import co.edu.uniquindio.agencia.exceptions.CampoObligatorioException;
import java.util.ArrayList;

public class AgenciaViajes {

    private ArrayList<Destino> destinos;

    public AgenciaViajes(){
        this.destinos = new ArrayList<>();
    }

    public void crearDestino(Destino destino) throws CampoObligatorioException {

        if(destino.getNombre() == null || destino.getNombre().isEmpty()){
            throw new CampoObligatorioException("El nombre del destino es obligatorio");
        }

        //Demás validaciones

        destinos.add(destino);
    }

}
