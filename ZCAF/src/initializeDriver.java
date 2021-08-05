import org.openqa.selenium.chrome.ChromeDriver;

public class initializeDriver {
	
	public ChromeDriver driver = null;
	
	public initializeDriver(ChromeDriver driver) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\aniru\\OneDrive\\My Work\\Automation\\selenium Java\\chromedriver.exe");
		
		driver = new ChromeDriver();
		this.driver = driver;
	}

}
