package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.tesBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="logintest")
	public void logintest(String user, String pwd,String exp) throws InterruptedException
	{
		logger.info("********login started********");
		driver.get(configpropobj.getProperty("baseurl"));
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.info("******* login test passed**********");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(true);
			}
			else if (exp.equals("Fail"))
			{
				logger.info("********* login test Failed********");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(false);
			}
		}
		else if(!exp_title.equals(act_title))
		{
			if(exp.equals("Pass"))
			{
				logger.warn("**********login test failed*********");
				Assert.assertTrue(false);				
			}
			else if(exp.equals("Fail"))
			{
				logger.info("*******login test passed***********");
				Assert.assertTrue(true);
			}
		}
		logger.info("*********login test finished***********");
		
	}
	
	@DataProvider(name= "logintest")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\Testdata\\LoginData .xlsx";
		int totalrows = XLUtils.getRowCount(path, "Sheet1");
		int totalcols = XLUtils.getCellCount(path, "Sheet1",1);
		
		String logindata[][] = new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
			
		}
		
		return logindata;
		
		
	}

}
