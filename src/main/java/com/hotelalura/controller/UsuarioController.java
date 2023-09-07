package com.hotelalura.controller;

import com.hotelalura.dao.UsuarioDAO;
import com.hotelalura.models.Usuario;

/**
 * @author jdmon on 6/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class UsuarioController {
    public boolean consultar(Usuario usuario){
        return new UsuarioDAO().consultar(usuario);
    }
}
