package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.tesBase.BaseClass;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_LoginTest_001 extends BaseClass 
{
	
	
	@Test
	public void logintest() throws IOException
	{
		logger.info("*********Starting TC_LoginTest_001 *********");
		driver.get(configpropobj.getProperty("baseurl"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("*********login details**********");
		lp.setUserName(configpropobj.getProperty("useremail"));
		lp.setPassword(configpropobj.getProperty("password"));
		lp.clickLogin();
		
		String act_title = driver.getTitle();
		String exp_title = "Dashboard / nopCommerce administration";
		if(act_title.equals(exp_title))
		{
			logger.info("************login test passed**********");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("************login test failed**********");
			capturescreen(driver,"logintest");
			Assert.assertTrue(false);
			
		}	
		
		logger.info("************login test finished**********");
	}
	  

}
