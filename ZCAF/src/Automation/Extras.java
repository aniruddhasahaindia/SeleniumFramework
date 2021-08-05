package Automation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Extras {
	
	public static void main(String[] args)
	{
		//System.out.println(getCurrentTimeStamp());
		System.out.println("test");
	}

	public boolean highlightElement(WebDriver driver,WebElement wl) {
		try {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", wl);
		return true;
		}
		catch(Exception e) {
			System.out.println("Error message is highlightElement method is " + e.getLocalizedMessage());
			return false;
		}
	}
	
	public String getCurrentTimeStamp()
	{
		
		//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//return sdf1.format(timestamp);
		return timestamp.toString().replace(" ", ".").replace(":",".").replace("-", ".");
	}
}
