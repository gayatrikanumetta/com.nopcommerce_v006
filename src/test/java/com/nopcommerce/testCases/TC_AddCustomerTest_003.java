package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddCustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.tesBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("<<<<<<<<test case started>>>>>>>>>>");
		driver.get(configpropobj.getProperty("baseurl"));
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configpropobj.getProperty("useremail"));
		lp.setPassword(configpropobj.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);
		
		logger.info("<<<<<<<<<Adding new customer>>>>>>>>>");
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
		Thread.sleep(3000);
		
		logger.info("<<<<<<<<<<<adding customer details>>>>>>>>>>>>");
		String email = randomstring()+"@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("admin123");
		addcust.setFirstName("Jonatahn");
		addcust.setLastName("john");
		addcust.setGender("Male");
		addcust.setDob("7/05/1985"); // Format: MM/DD/YYY
		addcust.setCompanyName("busyQA");
		addcust.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("This is for testing.........");
		addcust.clickOnSave();
		Thread.sleep(3000);
		//validation...
		if(addcust.verifyConfirmationMsg())
		{
			logger.info("<<<<<<<<<<<Customer added successfully>>>>>>>>");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("<<<<<<<<<<customer not added successfully>>>>>>>>>>>");
			capturescreen(driver,"addNewCustomer");	
			Assert.assertTrue(false);
		}
		logger.info("<<<<<<<<<<TC_AddCustomerTest_003 completed successfully>>>>>>>>>>");
}
}