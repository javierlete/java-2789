package com.ipartek.bases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCEjemplo {
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver");
		}
	}
	
	private static final String URL = "jdbc:sqlite:bases.db";
	private static final String USER = null;
	private static final String PASS = null;

	private static final String SQL_SELECT = "SELECT * FROM productos";
	private static final String SQL_INSERT = "INSERT INTO productos (nombre, precio, fecha_caducidad) VALUES (?,?,?)";

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		PreparedStatement pst = con.prepareStatement(SQL_SELECT);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			java.sql.Date fecha = rs.getDate("fecha_caducidad");
			LocalDate fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;
			
			System.out.printf("%5s %-20s %10s %-10s\n", id, nombre, precio, fechaCaducidad);
		}
		
		pst = con.prepareStatement(SQL_INSERT);
		
		pst.setString(1, "Nuevo'); DROP TABLE productos; --");
		pst.setBigDecimal(2, new BigDecimal("12345.12"));
		pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
		
		// int numeroRegistrosAlterados = pst.executeUpdate();
		
		// System.out.println(numeroRegistrosAlterados);
	}
}
