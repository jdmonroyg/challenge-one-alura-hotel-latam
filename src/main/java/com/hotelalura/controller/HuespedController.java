package com.hotelalura.controller;

import com.hotelalura.dao.HuespedDAO;
import com.hotelalura.models.Huesped;

import java.util.List;

/**
 * @author jdmon on 9/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class HuespedController {
    public void guardar(Huesped huesped) {
        new HuespedDAO().guardar(huesped);
    }
    public List<Huesped> listar(){
        return new HuespedDAO().listar();
    }
    public List<Huesped> buscarId(String id){
        return new HuespedDAO().buscarId(id);
    }

    public List<Object> buscarbuscarNombreOrApellido(String nombreOrApellido){
        return new HuespedDAO().buscarbuscarNombreOrApellido(nombreOrApellido);
    }
    public void modificar(Huesped huesped){
        new HuespedDAO().modificar(huesped);
    }
    /*public void eliminar(int id){
        new HuespedDAO().eliminar(id);
    }*/
}
