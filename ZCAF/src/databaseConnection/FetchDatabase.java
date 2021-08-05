package databaseConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import ReadAndWriteInputData.Configuration;

public class FetchDatabase {
   public static void main(String[] args) {
	   
	   HashMap hm1 = getDBValues("automation_qa","test_design","idTESTCASES","TC2");
	   System.out.println(hm1);
	 
	   
	   HashMap hm3 = getDBValues("database123","table1");
	   //System.out.println(hm3);
   }
   
    public static HashMap getDBValues(String database,String tableName,String where,String condition)
   {
	   
		      Connection con = null;
		      Statement statement = null;
		      HashMap hm = new HashMap<>();
		      String sql=null,sql2;
		      
		      try {
		         
		         //System.out.println("1");
		         Class.forName("com.mysql.jdbc.Driver");
		         //System.out.println("2");
		         con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		         //System.out.println("3");
		         statement = (Statement) con.createStatement();
		        
		         sql = "select * from "+tableName + " where " + where + " = '" + condition+"'";
		         //sql = "select * from "+tableName;
		         
		         //System.out.println("Query is : " + sql);
		         ResultSet resultSet = statement.executeQuery(sql);
		       
				//Retrieving the ResultSetMetaData object
		         ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

		         //getting the column type
		         int column_count = rsmd.getColumnCount();
		         //System.out.println("Number of columns in the table : "+column_count);
		         
		         
		         while (resultSet.next()) {
		        	 String allColums = "";
		        	 for(int i=1; i<=column_count;i++)
		        	 {
		        		 //System.out.print("  Column values : " + resultSet.getString(i));
		        		 
		        		 allColums = allColums + resultSet.getString(i);
		        		 if(i<column_count) {
		        			 allColums = allColums + Configuration.getDelimiter();
		        		 }
		        	 }
		        	 hm.put(condition, allColums);
		      }
		      ////System.out.println(hm);

		      } catch (Exception e) {
		    	  System.out.println("Query is " + sql);
		    	  System.out.println(e.getLocalizedMessage());
		      }
			return hm;
		   
   }
    
    public static String getDBValues(String database,String tableName,String where,String condition,String strColumn)
    {
 	   
 		      Connection con = null;
 		      Statement statement = null;
 		      String strColumnReturnValue="";
 		     String sql=null,sql2;
 		      
 		      try {
 		         
 		         //System.out.println("1");
 		         Class.forName("com.mysql.jdbc.Driver");
 		         //System.out.println("2");
 		         con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
 		         //System.out.println("3");
 		         statement = (Statement) con.createStatement();
 		         
 		         sql = "select " + strColumn + " from "+tableName + " where " + where + " = '" + condition+"'";
 		         //sql = "select * from "+tableName;
 		         
 		         //System.out.println("Query is : " + sql);
 		         ResultSet resultSet = statement.executeQuery(sql);
 		       
 				//Retrieving the ResultSetMetaData object
 		         ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

 		         //getting the column type
 		         int column_count = rsmd.getColumnCount();
 		         //System.out.println("Number of columns in the table : "+column_count);
 		         
 		         
 		         if (resultSet.next()) {
 		        	 
 		        	 
 		        		 
 		        	strColumnReturnValue =  resultSet.getString(strColumn);
 		        		 
 		      }
 		      ////System.out.println(hm);

 		      } catch (Exception e) {
 		    	 System.out.println("Query is " + sql);
 		         System.out.println(e.getLocalizedMessage());
 		      }
 			return strColumnReturnValue;
 		   
    }
   
  
    public static HashMap getDBValues(String database,String tableName)
   {
	   
		      Connection con = null;
		      Statement statement = null;
		      HashMap hm = new HashMap<>();
		      String sql=null,sql2;
		      
		      try {
		         
		         //System.out.println("1");
		         Class.forName("com.mysql.jdbc.Driver");
		         //System.out.println("2");
		         con = DriverManager.getConnection(Configuration.getConnectionStringMySQL()+database+"?verifyServerCertificate=false&useSSL=true", "aniruddha", "aniruddha");
		         //System.out.println("3");
		         statement = (Statement) con.createStatement();
		         
		         sql = "select * from "+tableName;
		         //sql = "select * from "+tableName;
		         
		         //System.out.println("Query is : " + sql);
		         ResultSet resultSet = statement.executeQuery(sql);
		       
				//Retrieving the ResultSetMetaData object
		         ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

		         //getting the column type
		         int column_count = rsmd.getColumnCount();
		         //System.out.println("Number of columns in the table : "+column_count);
		         
		         
		         while (resultSet.next()) {
		        	 String remaingColums = "";
		        	 for(int i=2; i<=column_count;i++)
		        	 {
		        		 //System.out.print("  Column values : " + resultSet.getString(i));
		        		 
		        		 remaingColums = remaingColums + resultSet.getString(i);
		        		 if(i<column_count) {
		        			 remaingColums = remaingColums + Configuration.getDelimiter();
		        		 }
		        	 }
		         hm.put(resultSet.getString(1), remaingColums);
		         //System.out.println();
		      }
		      ////System.out.println(hm);

		      } catch (Exception e) {
		    	  System.out.println("Query is " + sql);
		    	  System.out.println(e.getLocalizedMessage());
		      }
			return hm;
		   
   }
   
}