package Automation;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import ReadAndWriteInputData.Configuration;
import TestLab.TestCases;

public class BrowserObjects {

	//static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static WebElement makeElement(String strXpath)
	{
		//System.out.println("Timeout is " + Configuration.getTimeout());
		try {
			WebDriverWait wait = new WebDriverWait(TestCases.driver,Configuration.getTimeout());
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strXpath)));
			Extras ex = new Extras();
			ex.highlightElement(TestCases.driver, element);
		    return element;
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		
	}
	
	public static void initiateDriver(String strBrowser)
	{
				
		switch (strBrowser.toUpperCase())	{
		
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Configuration.getChromeDriverPath());
			TestCases.driver = new ChromeDriver();
			TestCases.driver.manage().window().maximize();
			break;
			
		default:
			System.out.println("No Browser found");
		}
		
	}
	
	public static boolean destroyDriver()
	{
		try {
		TestCases.driver.close();
		TestCases.driver.quit();
		return true;
		}
		catch(Exception e) {
			System.out.println("Error message is destryDriver method is " + e.getLocalizedMessage());
			return false;
		}
		
	}
	
	public static boolean LaunchBrowser(String url) {
		
		try {
		if(url.isBlank() || url.isEmpty() || url.toLowerCase().equals("null") || url.equals("")) {
			url= "https://google.com";
		}
		TestCases.driver.get(url);
		return true;
		}
		catch(Exception e) {
			System.out.println("Error message is LaunchBrowser method is " + e.getLocalizedMessage());
			return false;
		}
	}

	public static String CaptureScreenShot() {//throws IOException {
		try {
		File scrFile = ((TakesScreenshot) TestCases.driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(Configuration.getResultPath()+"\\Screenshots\\ss_" + System.currentTimeMillis()+ ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		}
		catch(Exception e) {
			return "";
		}
		}
	

}
