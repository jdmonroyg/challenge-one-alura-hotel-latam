package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.models.Huesped;

import java.sql.*;

/**
 * @author jdmon on 9/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class HuespedDAO {
    private final Connection con;

    public HuespedDAO() {
        this.con = new ConnectionFactory().conectarDataBase();
    }

    public void guardar(Huesped huesped) {
        String query = "insert into huespedes (nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva) " +
                "values (?,?,?,?,?,?)";
        try (con;
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,huesped.getNombre());
            preparedStatement.setString(2,huesped.getApellido());
            preparedStatement.setDate(3,huesped.getFechaNacimiento());
            preparedStatement.setString(4,huesped.getNacionalidad());
            preparedStatement.setString(5,huesped.getTelefono());
            preparedStatement.setInt(6,huesped.getIdReserva());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet=preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()){
                    huesped.setId(resultSet.getInt(1));
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
