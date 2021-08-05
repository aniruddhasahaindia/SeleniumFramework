package TestLab;

import java.util.HashMap;

import ReadAndWriteInputData.Configuration;
import databaseConnection.FetchDatabase;

public class FlowDesign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getOperationList("VerifyPremium"));

	}
	
	public static HashMap getOperationList(String flowName)
	{
		HashMap flowOperations = new HashMap<>();
		HashMap flowOperationsUnFormatted=new HashMap<>();
		String t3 = "";
		
			try {
				flowOperationsUnFormatted = FetchDatabase.getDBValues("automation_qa", "flow_design","FLOW_NAMES",flowName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(testCaseFlowsUnFormatted);	
		try {
			String t1 = flowOperationsUnFormatted.get(flowName).toString();
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
		flowOperations.put(flowName, t3);	
		return flowOperations;
			
	}

}
