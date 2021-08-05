package TestLab;


import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Automation.BrowserObjects;
import Automation.Extras;
import ReadAndWriteInputData.Configuration;
import databaseConnection.*;

public class TestCases {
	
	static String cls_testCaseID;
	public static WebDriver driver;
	public static ExtentTest test;
	public static String runID;
	public static String testCaseName;
	
	public static void main(String[] args) throws Exception
	{
		runTestCase();
	}
	
	public static HashMap getTestCaseList()
	{
		
		return FetchDatabase.getDBValues("automation_qa", "testcases");
			
	}
	
	public static void runTestCase() throws Exception
	{
		
			
		HashMap allTestCases = getTestCaseList();
		String delimiter = Configuration.getDelimiter();
		runID = (new Extras()).getCurrentTimeStamp();
		ExtentReports report = new ExtentReports(Configuration.getResultPath()+"\\"+runID+".html");
		for(int i=0;i<allTestCases.size();i++) {
			
			String testCaseID = (String) allTestCases.keySet().toArray()[i]; 
			cls_testCaseID = testCaseID;
			String testCaseConfiguration = (String) allTestCases.get(testCaseID);
			
			String getTestCaseName = testCaseConfiguration.split(delimiter)[0];
			testCaseName=getTestCaseName;
			String getTestCaseBrowser = testCaseConfiguration.split(delimiter)[1];
			String getTestCaseRunStatus = testCaseConfiguration.split(delimiter)[2];
			
			if(getTestCaseRunStatus.trim().equalsIgnoreCase("RUN"))
			{
				//System.out.println("");System.out.println(Integer.toString(i+1) + " ) Running Test case Name " + getTestCaseName);System.out.println("");
				HashMap testCaseFlows = TestDesign.getTestFlowList(testCaseID);
				//System.out.println("testCaseFlows " + testCaseFlows);
				
				test = report.startTest(getTestCaseName);
				BrowserObjects.initiateDriver(getTestCaseBrowser);
					executeTestCaseFlows(testCaseFlows);
				BrowserObjects.destroyDriver();
				report.endTest(test);
				
			}
			
			//System.out.println(getTestCaseName + getTestCaseBrowser + getTestCaseRunStatus);
			
		}
		
		report.flush();
	}

	private static void executeTestCaseFlows(HashMap testCaseFlows) throws Exception {
		
		for(int i=0;i<testCaseFlows.size();i++) {
			//System.out.print(testCaseFlows.keySet().toArray()[i] + ":");
			//System.out.println(testCaseFlows.get(testCaseFlows.keySet().toArray()[i]));
			String[] flows = testCaseFlows.get(testCaseFlows.keySet().toArray()[i]).toString().split(Configuration.getDelimiter());
			// Start coding from here. Take flow name , fetch operations and execute
			for(int j=2; j<flows.length;j++) {
				//System.out.println("Flows are " + flows[j]);
				HashMap Operations = FlowDesign.getOperationList(flows[j]);
				//System.out.println(Operations);
				//Execute Operations
				String[] OperationList = Operations.get(flows[j]).toString().split(Configuration.getDelimiter());
				
				for(int k=2; k<OperationList.length; k++) {
					//System.out.print("Operation is ");
					//System.out.print("Operation is " + OperationList[k]);
					//String strMethod = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", OperationList[k], "METHOD");
					//System.out.println(", Method is " + strMethod + " .... ");
					TestMethod.fetchMethod(OperationList[k]);
				}
				//System.out.println("");
				
			}
			
			
			
		}
		
		
	}

}
