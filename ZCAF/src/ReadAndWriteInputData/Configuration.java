package ReadAndWriteInputData;

import databaseConnection.FetchDatabase;

public class Configuration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getDelimiter()
	{
		return (String) FetchDatabase.getDBValues("automation_qa", "configuration").get("delimiter");
	}
	
	public static int getTimeout()
	{
		try {
				return Integer.parseInt((String) FetchDatabase.getDBValues("automation_qa", "configuration").get("timeOut"));
		}
		catch(Exception e)
		{
			return 5000;
		}
	}
	
	public static String getConnectionStringMySQL()
	{
		return "jdbc:mysql://127.0.0.1:3306/";
	}
	
	public static String getChromeDriverPath()
	{
		try {
				return (String) FetchDatabase.getDBValues("automation_qa", "configuration").get("chromeDriverPath");
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public static String getResultPath()
	{
		try {
				return (String) FetchDatabase.getDBValues("automation_qa", "configuration").get("ResultFolder");
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	

}
