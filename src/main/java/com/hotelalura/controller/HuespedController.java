package com.hotelalura.controller;

import com.hotelalura.dao.HuespedDAO;
import com.hotelalura.dao.ReservaDAO;
import com.hotelalura.models.Huesped;
import com.hotelalura.models.Reserva;

/**
 * @author jdmon on 9/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class HuespedController {
    public void guardar(Huesped huesped) {
        new HuespedDAO().guardar(huesped);
    }
}
