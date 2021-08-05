package TestLab;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Automation.BasicOperations;
import Automation.BrowserObjects;
import Automation.Extras;
import databaseConnection.FetchDatabase;
import databaseConnection.StoreDatabase;

public class TestMethod {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void fetchMethod(String strOperation) throws InterruptedException, InvocationTargetException, IllegalAccessException, ClassNotFoundException
	{
		boolean stepStatus = false;
		String strMethod = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "METHOD");
		String strXPath = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "XPATH");
		String strTableRef = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "TESTDATA_TABLE_REF");
		String strColumnRef = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "TESTDATA_COLUMN_REF");
		
		String strSTEP_VALIDATION = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "STEP_VALIDATION");
		String strPOS_RESULT = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "POS_RESULT");
		String strNEG_RESULT = FetchDatabase.getDBValues("automation_qa", "operation", "OPERATION", strOperation, "NEG_RESULT");
		
		String strTestData = getTestDataMethod(strTableRef,strColumnRef);
		WebElement wbElement = BrowserObjects.makeElement(strXPath);
		
		try {
			stepStatus = executeMethods(strMethod,wbElement,strTestData);
			String screenShotPath = BrowserObjects.CaptureScreenShot();
			if(stepStatus) {
				TestCases.test.log(LogStatus.PASS, TestCases.test.addScreenCapture(screenShotPath),strPOS_RESULT);
				StoreDatabase.setDBValues("automation_qa", "tr_teststepresult",(new Extras()).getCurrentTimeStamp(), TestCases.runID, TestCases.cls_testCaseID, TestCases.testCaseName, strSTEP_VALIDATION, "PASS", strPOS_RESULT, "", screenShotPath);
			}
			else {
				TestCases.test.log(LogStatus.FAIL, TestCases.test.addScreenCapture(BrowserObjects.CaptureScreenShot()),strNEG_RESULT);
				StoreDatabase.setDBValues("automation_qa", "tr_teststepresult", (new Extras()).getCurrentTimeStamp(),TestCases.runID, TestCases.cls_testCaseID, TestCases.testCaseName, strSTEP_VALIDATION, "FAIL", strNEG_RESULT, "", screenShotPath);
			}
		} catch (SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static boolean executeMethods(String strMethod, WebElement wbElement, String strTestData) {
			//throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		boolean stepStatus = false;
		switch(strMethod) {
		
		case "LaunchBrowser":
			stepStatus=BrowserObjects.LaunchBrowser(strTestData);
			break;
			
		case "Click":
			stepStatus=BasicOperations.Click(wbElement);
			break;
		
		case "setText":
			stepStatus=BasicOperations.setText(wbElement,strTestData);
			break;
			
		default:
			System.out.println(strMethod + " : Method not found/defined/invoked");
		}
		/*
		Class c = Class.forName("BrowserObjects");
	      Object obj = c.newInstance();
	      Method method = c.getDeclaredMethod(strMethod, String.class);
	      method.setAccessible(true);
	      method.invoke(obj, wbElement,strTestData);
	      */
		
		return stepStatus;
	}

	public static String getTestDataMethod(String strTableRef, String strColumnRef) {
		
		String TestCaseId = TestCases.cls_testCaseID; System.out.println("Test Case " + TestCaseId);
		String testData = FetchDatabase.getDBValues("automation_qa", strTableRef, "idTESTCASES", TestCaseId, strColumnRef);
		return testData;
	}

}
