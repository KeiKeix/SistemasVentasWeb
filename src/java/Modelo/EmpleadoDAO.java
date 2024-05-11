package Modelo;

import config.Conexion;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    Conexion conexion = new Conexion();

    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    int respuesta;

    public EmpleadoDTO validar(String user, String dni) {

        EmpleadoDTO em = new EmpleadoDTO();

        String sql = "SELECT * FROM empleado WHERE User=? AND Dni=?";

        try {

            con = conexion.Conexion();

            pst = con.prepareStatement(sql);

            pst.setString(1, user);

            pst.setString(2, dni);

            rs = pst.executeQuery();

            while (rs.next()) {

                em.setIdEmpleado(rs.getInt("IdEmpleado"));

                em.setUser(rs.getString("User"));

                em.setDni(rs.getString("Dni"));

                em.setNombre(rs.getString("Nombres"));

            }

        } catch (Exception e) {
        }

        return em;
    }

    //op CRUD
    public List listar() {

        String sql = "SELECT * FROM empleado";
        List<EmpleadoDTO> lista = new ArrayList<>();
        try {
            con = conexion.Conexion();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                EmpleadoDTO em = new EmpleadoDTO();
                em.setIdEmpleado(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNombre(rs.getString(3));
                em.setTelefono(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);

            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(EmpleadoDTO em) {

        String sql = "INSERT INTO empleado(Dni, Nombres, Telefono, Estado, User) VALUES(?,?,?,?,?)";
        try {
            con = conexion.Conexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, em.getDni());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getTelefono());
            pst.setString(4, em.getEstado());
            pst.setString(5, em.getUser());
            pst.executeUpdate();

        } catch (Exception e) {
        }
        return respuesta;
    }

    public EmpleadoDTO listarId(int id) {
        EmpleadoDTO emp = new EmpleadoDTO();
        String sql = "SELECT * FROM empleado WHERE IdEmpleado=" + id;

        try {
            con = conexion.Conexion();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                emp.setDni(rs.getString(2));
                emp.setNombre(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));

            }
        } catch (Exception e) {
        }
        return emp;
    }

    public int actualizar(EmpleadoDTO em) {

        String sql = "UPDATE empleado SET Dni=?, Nombres=?, Telefono=?, Estado=?, User=? WHERE IdEmpleado=?";
        try {
            con = conexion.Conexion();
            pst = con.prepareStatement(sql);
            pst.setString(1, em.getDni());
            pst.setString(2, em.getNombre());
            pst.setString(3, em.getTelefono());
            pst.setString(4, em.getEstado());
            pst.setString(5, em.getUser());
            pst.setInt(6, em.getIdEmpleado());
            pst.executeUpdate();

        } catch (Exception e) {
        }
        return respuesta;
    }

    public void delete(int id) {
        String sql = "DELETE FROM empleado WHERE IdEmpleado=" + id;
        try {
            con = conexion.Conexion();
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
}
