package BaseTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
//import java.net.URL;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {

	/*
	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		// loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		// this fuction for GRID execution for os 

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capability = new DesiredCapabilities();
			
			if (os.equalsIgnoreCase("windows")) {
				
				capability.setPlatform(Platform.WIN11);
				
			} else if (os.equalsIgnoreCase("mac")) {
				
				capability.setPlatform(Platform.WIN11);
			
		} else 
		{
			System.out.println("no matching os");
			return;
		}
			
		
			// this is for browseName
		switch(br.toLowerCase())
		{
		case "chrome":capability.setBrowserName("chrome"); break;
		case "edge" :capability.setBrowserName("MicrosoftEdge");break;
		default : System.out.println("no matching browser"); return;
		}
		
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capability);
		driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), capability);
		
		

		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			// this is local broowsers
			switch (br.toLowerCase()) 
			{
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			}
			}
		}
		
		
//		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		driver.get("https://tutorialsninja.com/demo");
		driver.get(p.getProperty("appUrl"));

		driver.manage().window().maximize();

	}
	


	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}
	
	

	// this function is used to generated the randon String
	public String generatedString() {
		String generated = RandomStringUtils.insecure().nextAlphabetic(5);
		return generated;

	}

	// this function is used to generate the random number
	public String genetratedNumber() {
		String genrratedNo = RandomStringUtils.insecure().nextAlphanumeric(10);
		return genrratedNo;
	}

	// generate the alphaNumeric password

	public String genetratedAlphaNum() {
		String generated = RandomStringUtils.insecure().nextAlphabetic(3);
		String genrratedNo = RandomStringUtils.insecure().nextAlphanumeric(3);
		return (generated + "#" + genrratedNo);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		/*
		 * targetFile.getParentFile().mkdirs(); // creates the folder if missing
		 * java.nio.file.Files.copy(sourceFile.toPath(), targetFile.toPath(),
		 * java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		 */
//		return targetFilePath;
//		}
	
	
	
	
	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		// loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		String env = p.getProperty("execution_env");
		if (env == null) {
			throw new IllegalStateException("'execution_env' missing. Keys loaded: " + p.stringPropertyNames());
		}
		env = env.trim();
		System.out.println("execution_env = [" + env + "]");

		if (env.equalsIgnoreCase("remote")) {

			DesiredCapabilities capability = new DesiredCapabilities();

			switch (os.toLowerCase()) {
			case "windows":
				capability.setPlatform(Platform.WIN11);
				break;
			case "mac":
				capability.setPlatform(Platform.MAC);
				break;
			default:
				throw new IllegalArgumentException("no matching os: " + os);
			}

			switch (br.toLowerCase()) {
			case "chrome":
				capability.setBrowserName("chrome");
				break;
			case "edge":
				capability.setBrowserName("MicrosoftEdge");
				break;
			default:
				throw new IllegalArgumentException("no matching browser: " + br);
			}

			driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), capability);

		} else if (env.equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("no matching browser: " + br);
			}

		} else {
			throw new IllegalArgumentException("Invalid execution_env: [" + env + "]");
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// generates a random alphabetic string
	public String generatedString() {
		return RandomStringUtils.insecure().nextAlphabetic(5);
	}

	// generates a random alphanumeric string
	public String genetratedNumber() {
		return RandomStringUtils.insecure().nextAlphanumeric(10);
	}

	// generates an alphanumeric password
	public String genetratedAlphaNum() {
		String generated = RandomStringUtils.insecure().nextAlphabetic(3);
		String genrratedNo = RandomStringUtils.insecure().nextAlphanumeric(3);
		return (generated + "#" + genrratedNo);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

//		targetFile.getParentFile().mkdirs(); // creates the folder if missing
//		Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		return targetFilePath;
	}

	}




