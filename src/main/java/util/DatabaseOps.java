package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOps {
	
	private static ResultSet rs;
	
	public ResultSet getSqlResultInMap(String sql) {  

        try {
			
			Connection connection;
				connection = DriverManager.getConnection(ConfigReader.getProperties("url"), ConfigReader.getProperties("DBUsername"), ConfigReader.getProperties("DBPassword"));
			Statement statement =  connection.createStatement();
	     
			ResultSet rs=statement.executeQuery(sql);  
			//ResultSetMetaData md  = rs.getMetaData();

             return rs ;
        
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return rs;
	
	}
}


