package com.nopcommerce.tesBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j2
import org.apache.logging.log4j.Logger;//log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public WebDriver driver;
	public Properties configpropobj;
	public Logger logger=LogManager.getLogger(this.getClass());
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException, InterruptedException
	{
		//loading congig.properties file
		configpropobj=new Properties();
		//FileInputStream configfile= new FileInputStream(".\\Resources\\config.properties");
		FileInputStream configfile= new FileInputStream(System.getProperty("user.dir")+"\\Resources\\config.properties");
		configpropobj.load(configfile);
		//finished loading config file...
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		}
		else if (br.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", ".\\Drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
		
			//WebDriverManager.edgedriver().setup();
			//driver= new EdgeDriver();
			Thread.sleep(4000);
			
		}
		else if (br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	public void capturescreen(WebDriver driver , String tname) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File act_screen=ts.getScreenshotAs(OutputType.FILE);
		File exp_screen= new File(".\\Screenshots\\" +tname+".png");
		FileUtils.copyFile(act_screen, exp_screen);
		System.out.println("Screenshot taken...");
	}
	
	public String randomstring()
	{
		String genetatedstring1 = RandomStringUtils.randomAlphabetic(6);
		return (genetatedstring1);
	}
	
	public int randomnumber()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(5);
		return(Integer.parseInt(generatedstring2));
	}
		
	
}
