package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import ReadAndWriteInputData.Configuration;
import TestLab.TestCases;

public class StoreDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreDatabase.setDBValues("automation_qa", "tr_teststepresult","123", "2021fdffr666444r55667", "TC345", "Validate app", "Validate open URL", "PASS", "URL is opened", "", "");
		

	}

	public static boolean setDBValues(String database,String tableName,String resultID,String runID,String testCaseId,String StestCaseName,String stepValidation,
			String tcStatus,String tcActualRes,String tcTechnicalError,String ScreenShotpath)
	{
		Connection con = null;
	      Statement statement = null;
	      String sql=null;
	      
	      try {
	         
		        Class.forName("com.mysql.jdbc.Driver");
		        //con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		        con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?useSSL=false", "aniruddha", "aniruddha");
		        statement = (Statement) con.createStatement();
		        
		        		        		        
		        sql = "INSERT INTO "+tableName+ " VALUES ('"+resultID+"','"+runID+"','"+testCaseId+"','"+StestCaseName+"','"+stepValidation+
		        		"','"+tcStatus+"','"+tcActualRes+"','"+tcTechnicalError+"','"+ScreenShotpath+"')";
		        //System.out.println("Sql query for inserting value is " + sql);
		        statement.executeUpdate(sql);
		         try {
		             if (statement != null) {
		            	 con.close();
		             }
		          } catch (Exception se) {
		        	  System.out.println("Error in closing database  is : " + se.getLocalizedMessage());
		          }
		      return true;
	      }
	      catch(Exception e) {
	    	  System.out.println("Error in inserting values in database is : " + e.getLocalizedMessage());
	    	  return false;
	      }
	         
	         
	}
	
	
	public static boolean setDBValues(String database,String tableName,String[] recordsToUpdate)
	{
			Connection con = null;
			Statement statement = null;
			String sql="";
			String sqlSubString="";
	      
	      try {
	         
		        Class.forName("com.mysql.jdbc.Driver");
		        //con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		        con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?useSSL=false", "aniruddha", "aniruddha");
		        statement = (Statement) con.createStatement();
		        
		        for(int x=0; x<recordsToUpdate.length;x++) {
		        	sqlSubString = sqlSubString + "'" + recordsToUpdate[x] + "'";
		        	if(x<recordsToUpdate.length-1) {
		        		sqlSubString = sqlSubString + ",";
		        	}
		        }
		        sql = "INSERT INTO "+tableName+ " VALUES ("+sqlSubString+")";
		        System.out.println("Sql Query is " + sql);		        		        
		        
		        //System.out.println("Sql query for inserting value is " + sql);
		        statement.executeUpdate(sql);
		         try {
		             if (statement != null) {
		            	 con.close();
		             }
		          } catch (Exception se) {
		        	  System.out.println("Error in closing database  is : " + se.getLocalizedMessage());
		          }
		      return true;
	      }
	      catch(Exception e) {
	    	  System.out.println("Error in inserting values in database is : " + e.getLocalizedMessage());
	    	  return false;
	      }
	         
	         
	}
	
	
	public static boolean truncateDBValues(String database,String tableName)
	{
			Connection con = null;
			Statement statement = null;
			String sql="";
			String sqlSubString="";
	      
	      try {
	         
		        Class.forName("com.mysql.jdbc.Driver");
		        //con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		        con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?useSSL=false", "aniruddha", "aniruddha");
		        statement = (Statement) con.createStatement();
		        
		        
		        sql = "TRUNCATE TABLE "+tableName;
		        System.out.println("Sql Query is " + sql);		        		        
		        
		        //System.out.println("Sql query for inserting value is " + sql);
		        statement.executeUpdate(sql);
		         try {
		             if (statement != null) {
		            	 con.close();
		             }
		          } catch (Exception se) {
		        	  System.out.println("Error in closing database  is : " + se.getLocalizedMessage());
		          }
		      return true;
	      }
	      catch(Exception e) {
	    	  System.out.println("Error in inserting values in database is : " + e.getLocalizedMessage());
	    	  return false;
	      }
	         
	         
	}
	
	
	public static boolean addDBColumn(String database,String tableName,String ColumnName)
	{
			Connection con = null;
			Statement statement = null;
			String sql="";
			String sqlSubString="";
	      
	      try {
	         
		        Class.forName("com.mysql.jdbc.Driver");
		        //con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		        con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?useSSL=false", "aniruddha", "aniruddha");
		        statement = (Statement) con.createStatement();
		        
		        
		        sql = "ALTER TABLE " +tableName+ " ADD COLUMN "+ColumnName+" LONGTEXT NULL DEFAULT NULL";
		        System.out.println("Sql Query is " + sql);		        		        
		        
		        //System.out.println("Sql query for inserting value is " + sql);
		        statement.executeUpdate(sql);
		         try {
		             if (statement != null) {
		            	 con.close();
		             }
		          } catch (Exception se) {
		        	  System.out.println("Error in closing database  is : " + se.getLocalizedMessage());
		          }
		      return true;
	      }
	      catch(Exception e) {
	    	  System.out.println("Error in inserting values in database is : " + e.getLocalizedMessage());
	    	  return false;
	      }
	         
	         
	}
}
