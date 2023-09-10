package com.hotelalura.controller;

import com.hotelalura.dao.ReservaDAO;
import com.hotelalura.models.Reserva;

/**
 * @author jdmon on 7/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class ReservaController {
    public void guardar(Reserva nuevaReserva) {
         new ReservaDAO().guardar(nuevaReserva);
    }
}
