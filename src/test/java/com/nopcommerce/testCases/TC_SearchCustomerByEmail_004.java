package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddCustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.tesBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass
{
	@Test 
	public void searchCustomerbyEmail() throws IOException, InterruptedException
	{
		logger.info("******Search customer by email Test case started******");
		driver.get(configpropobj.getProperty("baseurl"));
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configpropobj.getProperty("useremail"));
		lp.setPassword(configpropobj.getProperty("password"));
		lp.clickLogin();
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();

		SearchCustomerPage searchcust = new SearchCustomerPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		searchcust.clickSearch();
		Thread.sleep(10000);
		
		boolean status = searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		System.out.println("status--------------->>>>>>>>>>>>>>>>>>>"+status);
		if(status==true)
		{
			logger.info("**********Search successful**********");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("**********Search not successful**********");
			capturescreen(driver,"searchCustomerbyEmail");
			Assert.assertTrue(false);
		}
		
		logger.info("************Search customer by email test case finished**************");
	}
}
