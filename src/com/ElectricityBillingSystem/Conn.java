package com.ElectricityBillingSystem;
import java.sql.*;
import java.sql.DriverManager;


/* Steps: Register the driver 
 * 			Creating a connection where we gave our information as root(Signup.java line:115)
 * 			Creating Statements
 * 			Executing mySql Queries
 * 			5th step is to close the connections which is automatically done by Java itself
 *   
 * */
public class Conn {
	
	Connection c;
	Statement s;
	Conn() {
		try {
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "Rahul@123");
		s = c.createStatement();
		} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
