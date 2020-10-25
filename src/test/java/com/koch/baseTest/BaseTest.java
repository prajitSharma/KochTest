package com.koch.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.koch.utilities.ChromeOptionUtil;
import com.koch.utilities.DynamicUtil;
import com.koch.utilities.SeleniumUtil;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	public static ChromeOptions options;
	
	static {
		String filePath = System.getProperty("user.dir") + File.separator + "src//test//resources//config//config.properties";
		File f = new File(filePath);
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(f);
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	@BeforeTest(alwaysRun = true)
	public static void initialiseBrowser() {
		
		String browser = prop.getProperty("BROWSER");
		
		if(browser.equals("chrome")) {
			String path = SeleniumUtil.getClassPath() + "drivers" + File.separator + "chromedriver.exe";
			System.out.println(path);
			System.setProperty("webdriver.chrome.driver", path);
			setCapabilities();
			driver = new ChromeDriver(options);
			
			
		}else if(browser.equals("firefox")) {
			System.out.println("Use Firefox Driver here");
			
		} 
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(DynamicUtil.pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DynamicUtil.implicitTimeOut, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("URL"));
	}
	
	
	
	
	public static void setCapabilities() {
		
		if(prop.getProperty("BROWSER").equals("chrome")) {
		options = new ChromeOptions();
		options.addArguments(ChromeOptionUtil.LOCATION_DISABLE);
		
		}
		
	}
	
	
	
	
	
	
}
