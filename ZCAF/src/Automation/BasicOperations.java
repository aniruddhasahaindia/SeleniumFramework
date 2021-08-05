package Automation;

import org.openqa.selenium.WebElement;

public class BasicOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean Click(WebElement wbElement)  {
		// TODO Auto-generated method stub
		try {
		//Thread.sleep(3000);
		wbElement.click();
		return true;
		}
		catch(Exception e) {
			System.out.println("Error message is Click method is " + e.getLocalizedMessage());
			return false;
		}
		
	}
	
	public static boolean setText(WebElement wbElement, String strValue) {// throws InterruptedException {
		// TODO Auto-generated method stub
		try {
		wbElement.clear();
		wbElement.sendKeys(strValue);
		return true;
		}
		catch(Exception e) {
			System.out.println("Error message is highlightElement method is " + e.getLocalizedMessage());
			return false;
		}
		
	}

}
