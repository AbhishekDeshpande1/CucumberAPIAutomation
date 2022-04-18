package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;


public class DB {
	
	
	
	DatabaseOps abc = new DatabaseOps();
	
	@Test
	public void sqlQuesry() throws SQLException
	
	{
		String xyz = "select Top 1   ChartID,Filenames from chartsupplementalfiles where Filenames = 'Test8.docx'";
	
		ResultSet rs= abc.getSqlResultInMap(xyz);  
		
		//ResultSetMetaData  p =rs.getMetaData();
		
	//	String q = p.getColumnName();
	      while (rs.next()) {
	    	  
	          String output = rs.getString("Filenames");
		
		System.out.println(output);
		
    }
         
	}
		
		
		
	
		
	}


