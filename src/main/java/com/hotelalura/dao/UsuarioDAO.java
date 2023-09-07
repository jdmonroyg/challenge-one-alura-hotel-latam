package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.models.Usuario;

import java.sql.*;

/**
 * @author jdmon on 6/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class UsuarioDAO {
    private final Connection con;

    public UsuarioDAO() {
        this.con=new ConnectionFactory().conectarDataBase();
    }

    public boolean consultar(Usuario usuario) {
        boolean resultado=false;
        String query="Select * from usuarios where usuario = ? and clave = ?";
        try (con;
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             ) {
            preparedStatement.setString(1,usuario.getUsuario());
            preparedStatement.setString(2,usuario.getClave());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    resultado=true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
