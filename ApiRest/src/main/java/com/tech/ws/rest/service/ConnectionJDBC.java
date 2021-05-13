package com.tech.ws.rest.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;


public class ConnectionJDBC {
	private static String db_ = "royaltex1" ;
	private static String login_ = "informix" ;
	private static String password_ = "Informix.2007" ;
	private static String url_ = "jdbc:informix-sqli://192.168.2.1:1526/" + db_ ;
	private static Connection connection_ ;
//	private static Statement st_ = null ;
	
	public Connection connectionJDBC() {
	
	try {
		Class.forName("com.informix.jdbc.IfxDriver" ) ;
		connection_ = DriverManager.getConnection ( url_,login_,password_) ;
		if ( connection_ != null ) {
			return connection_; //st_ = connection_.createStatement() ;
		}
		} catch ( SQLException e ) 
			{  e.toString(); 
			}
			
		catch ( ClassNotFoundException e ) 
			{ e.toString();
			}
			
	catch ( Exception e ) 
		{ e.toString();
		}
	return connection_;
	}
	
}