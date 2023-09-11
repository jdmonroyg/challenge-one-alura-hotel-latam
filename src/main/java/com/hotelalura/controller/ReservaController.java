package com.hotelalura.controller;

import com.hotelalura.dao.ReservaDAO;
import com.hotelalura.models.Reserva;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jdmon on 7/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class ReservaController {
    public void guardar(Reserva nuevaReserva) {
         new ReservaDAO().guardar(nuevaReserva);
    }
    public List<Reserva> listar(){
        return new ReservaDAO().listar();
    }

    public List<Reserva> buscarId(String id) {
        return new ReservaDAO().buscarId(id);
    }

}
