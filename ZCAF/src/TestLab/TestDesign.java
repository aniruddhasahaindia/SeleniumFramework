package TestLab;

import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;

import ReadAndWriteInputData.Configuration;
import databaseConnection.FetchDatabase;

public class TestDesign {

	public static void main(String[] args) {
		HashMap testCaseFlows = getTestFlowList("TC2");
		System.out.println("line 13 : " + testCaseFlows);
	}

	public static HashMap getTestFlowList(String testCaseID)
	{
		HashMap testCaseFlows = new HashMap<>();
		HashMap testCaseFlowsUnFormatted=new HashMap<>();
		String t3 = "";
		
			try {
				testCaseFlowsUnFormatted = FetchDatabase.getDBValues("automation_qa", "test_design","idTESTCASES",testCaseID);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(testCaseFlowsUnFormatted);	
		try {
			String t1 = testCaseFlowsUnFormatted.get(testCaseID).toString();
			//System.out.println(testCaseFlowsUnFormatted);
			String[] t2 = t1.split(Configuration.getDelimiter());
			
			
			for(int i=0; i<t2.length;i++) {
				//System.out.println(testCaseID + ";"+t2[i]);
				//if(!(t2[i].isEmpty() && t2[i].isBlank() && (!t2[i].equals("null") ))){
				if(!(t2[i].trim().equals("null") || t2[i].trim().equals(""))){
					//System.out.println(testCaseID + ";"+t2[i]);
					t3 = t3 + t2[i].toString();
					if(!(t2[i+1].trim().equals("null") || t2[i+1].trim().equals(""))){	
						t3 = t3 + Configuration.getDelimiter();
					}
				}
			}
		}
		catch(Exception e)
		{
			;
		}
		testCaseFlows.put(testCaseID, t3);	
		return testCaseFlows;
			
	}
	
	
	

}
