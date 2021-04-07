package com.java.project.checkinfront.reports;

import java.sql.Connection;
import java.sql.DriverManager;


import java.util.logging.Logger;

public class ReportConnection {

	public static Logger logger = Logger.getLogger(ReportConnection.class.getName());
	
	Connection connect;

	public Connection conexion() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:33061/checkincontrol?useSSL=false", "root", "mysql");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkincontrol?useSSL=false", "root", "mysql");
			System.out.println("Conexion exitosa: "+connect);
		} catch (Exception e) {
			logger.warning("Connection Report Exception: "+e.getMessage());
		}
		return connect;
	}

	public void desconectar() {
		connect = null;
	}

	public static void main(String[] args) {
		ReportConnection o = new ReportConnection();
		o.conexion();
	}
}

