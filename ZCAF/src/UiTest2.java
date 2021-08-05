

import org.openqa.selenium.chrome.ChromeDriver;

import ReadAndWriteInputData.ReadFromExcel;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UiTest2 {

	public UiTest2() throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				System.out.println("Hello World!!");
				ChromeDriver driver=null;
				initializeDriver InitializeDriver = new initializeDriver(driver);
				driver = InitializeDriver.driver;
				//driver.get("https://www.calculator.net/");
				
				ReadFromExcel ReadDataFromExcel = new ReadFromExcel();
				
				for(int i=1; i<4;i++)
				{
					String url1 = ReadDataFromExcel.readExcel("Inputs", "URL.xls", "Sheet1", i, 0);
					driver.get(url1);
				}
				
				
				driver.findElement(By.xpath("//a[text()='Interest Calculator']")).click();
				
				
				driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).sendKeys(Integer.toString(Float.floatToRawIntBits((float) (Math.random()*10000))));
				driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).clear();				
				driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).sendKeys("2000");
				driver.findElement(By.xpath("//input[@value='Calculate']")).click();
				System.out.print("Final output by autoted script is : ");
				System.out.println(driver.findElement(By.xpath("//*[@id=\"content\"]/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]")).getText());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.close();
				driver.quit();

	}

}
