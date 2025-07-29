  package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Properties p;
	
	@BeforeClass(groups={"Regression","smoke"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		FileReader file=new FileReader("./src//test//resources//Config.properties");
		
		p=new Properties();
		p.load(file); 
		System.out.println("test"+p.getProperty("appURL"));
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			
			if(os.equalsIgnoreCase("wondows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("linux")){
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No matching os");
				return;
			}
			
				
			switch(br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default:System.out.println("no matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		

		
		//loading cofig.properties file
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome":driver =new ChromeDriver();break;
			case "edge":driver =new EdgeDriver();break;
			case "firefox":driver =new FirefoxDriver();break;
			default:System.out.println("invalid");return;
			
			
			}
		}
			
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	
	//capture screenshot
	
	public String captureScreen(String tname) {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
		
	}
	
	@AfterClass(groups={"Regression","smoke"})
	public void tearDown() {
		driver.quit();
		System.out.println("close");
	}
}
