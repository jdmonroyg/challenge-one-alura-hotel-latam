package com.hotelalura.dao;

import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.models.Huesped;
import com.hotelalura.models.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Huesped> listar() {
        String query="select * from huespedes";
        List <Huesped> huespedList= new ArrayList<>();
        try(con;
            PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.execute();
            agregarHuespedList(huespedList, preparedStatement);

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return huespedList;
    }

    public List<Huesped> buscarId(String id) {
        String query="select * from huespedes where idhuespedes = ?";
        List <Huesped> huespedList= new ArrayList<>();
        try(con;
            PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1,Integer.parseInt(id));
            preparedStatement.execute();
            agregarHuespedList(huespedList, preparedStatement);

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return huespedList;
    }

    private static void agregarHuespedList(List<Huesped> huespedList, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet=preparedStatement.getResultSet()){
            while (resultSet.next()){
                Huesped huesped= new Huesped(resultSet.getInt("idHuespedes"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getDate("fechaNacimiento"),
                        resultSet.getString("nacionalidad"),
                        resultSet.getString("telefono"),
                        resultSet.getInt("idReserva"));
                huespedList.add(huesped);
            }
        }
    }


}
