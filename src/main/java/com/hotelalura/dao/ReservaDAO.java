package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.models.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Reserva> listar(){
        String query= "select * from reservas";
        List<Reserva> reservaList= new ArrayList<>();
        try(con;
            PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.execute();
            agregarReservaList(reservaList,preparedStatement);

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return reservaList;
    }

    public List<Reserva> buscarId(String id) {
        String query= "select * from reservas where id=?";
        List<Reserva> reservaList= new ArrayList<>();
        try(con;
            PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1,Integer.parseInt(id));
            preparedStatement.execute();
            agregarReservaList(reservaList,preparedStatement);

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return reservaList;
    }
    private static void agregarReservaList(List<Reserva> reservaList, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet=preparedStatement.getResultSet()){
            while (resultSet.next()){
                Reserva reserva= new Reserva(resultSet.getInt("id"),
                        resultSet.getDate("fechaEntrada"),
                        resultSet.getDate("fechaSalida"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("formaPago"));
                reservaList.add(reserva);
            }
        }
    }
}
