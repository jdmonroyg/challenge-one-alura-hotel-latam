package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.models.Reserva;

import java.sql.*;
import java.util.Optional;

/**
 * @author jdmon on 9/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class ReservaDAO {
    private final Connection con;

    public ReservaDAO() {
        this.con=new ConnectionFactory().conectarDataBase();
    }
    public void guardar(Reserva reserva) {
        String query = "insert into reservas (fechaEntrada, fechaSalida, valor, formaPago) " +
                "values (?, ?, ?, ?)";
        try (con;
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setDate(1,reserva.getFechaEntrada());
            preparedStatement.setDate(2,reserva.getFechaSalida());
            preparedStatement.setDouble(3,reserva.getValor());
            preparedStatement.setString(4,reserva.getFormaPago());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet=preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    reserva.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
}
