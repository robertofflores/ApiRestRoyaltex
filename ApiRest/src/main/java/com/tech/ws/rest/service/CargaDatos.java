package com.tech.ws.rest.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CargaDatos {
	
	ConnectionJDBC con = new ConnectionJDBC();
	

	public boolean cargarDatos(String sql){
		boolean ingreso=false;
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		System.out.println(sql);
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			
			ingreso = true;
			
			/*while (rs.next()) {
				VOModulo vo = new VOModulo();
				vo.setNombre_modulo(rs.getString(1));
				listaModulo.add(vo);
				
			}*/
			stm.close();
			rs.close();
			co.close();
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	
		return ingreso;
	}
	
}
