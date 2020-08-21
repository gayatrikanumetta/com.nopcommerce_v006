package com.nopcommerce.pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage
{
	WebDriver driver;
	WebElement listitem;
	public AddCustomerPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}
	
	@FindBy (xpath="//a[@href='#']//span[contains(text(),'Customers')]")
	@CacheLookup
	WebElement lnkCustomermenu;
	
	@FindBy (xpath="//span[@class='menu-item-title'][contains(text(),'Customers')]")
	@CacheLookup
	WebElement lnkCustomer;
	
	@FindBy (xpath="//i[@class='fa fa-plus-square']")
	@CacheLookup
	WebElement btnAddnew;
	
	@FindBy (xpath="//input[@id='Email']")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (xpath="//input[@id='Password']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy (xpath="//input[@id='FirstName']")
	@CacheLookup
	WebElement txtFirstname;
	
	@FindBy (xpath="//input[@id='LastName']")
	@CacheLookup
	WebElement txtLastname;
	
	@FindBy (xpath="//input[@id='Gender_Female']")
	@CacheLookup
	WebElement rdFemale;
	
	@FindBy (xpath="//input[@id='Gender_Male']")
	@CacheLookup
	WebElement rdMale;
	
	@FindBy (xpath="//input[@id='DateOfBirth']")
	@CacheLookup
	WebElement txtDob;
	
	@FindBy (xpath="//input[@id='Company']")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy (xpath="//input[@id='IsTaxExempt']")
	@CacheLookup
	WebElement Chktax;
	
	@FindBy (xpath="//div[9]//div[2]//div[1]//div[1]//div[1]")
	@CacheLookup
	WebElement txtNewsletter;
	
	@FindBy (xpath="//div[10]//div[2]//div[1]//div[1]//div[1]")
	@CacheLookup
	WebElement drpCostomerrole;
	
	@FindBy (xpath="//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstAdminstrator;
	
	@FindBy (xpath="//li[@class='k-item k-state-hover']")
	@CacheLookup
	WebElement lstForum;
	
	@FindBy (xpath="//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstGuest;
	
	@FindBy (xpath="//li[@id='5e6fc467-09df-4d55-ab40-d0240a0a62a7']")
	@CacheLookup
	WebElement lstRegestered;
	
	@FindBy (xpath="//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement lstVendor;
	
	@FindBy (xpath="//select[@id='VendorId']")
	@CacheLookup
	WebElement drpMgrvendor;
	
	@FindBy (xpath="//textarea[@id='AdminComment']")
	@CacheLookup
	WebElement txtAdmcomment;
	
	@FindBy (xpath="//button[@name='save']//i[@class='fa fa-floppy-o']")
	@CacheLookup
	WebElement btnSave;
	
	@FindBy (xpath="//div[@class='alert alert-success alert-dismissable']")
	@CacheLookup
	WebElement txtMsg;
	
	public void clickOnCustomersMenu()
	{
		lnkCustomermenu.click();
	}
	public void clickOnCustomersMenuItem() 
	{
		lnkCustomer.click();
	}
	public void clickOnAddnew()
	{
		btnAddnew.click();
	}
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			rdMale.click();
		}
		else if(gender.equals("Female"))
		{
			rdFemale.click();
		}
		else
		{
			rdMale.click();//Default
		}
	}
	public void setDob(String dob)
	{
		txtDob.sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		txtCompany.sendKeys(comname);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException 
		{
		
		drpCostomerrole.click();
		
			Thread.sleep(3000);
			
			if(role.equals("Registered"))
			{
				listitem=lstRegestered; 
			}
			else if(role.equals("Administrators"))
			{
				listitem=lstAdminstrator; 
			}
			else if(role.equals("Guests"))
			{
				// Here user can be Registered (or) Guest, only one
				lstRegestered.click(); //delete registered
				listitem=lstGuest;
			}
			else if(role.equals("Vendors"))
			{
				listitem=lstVendor;
			}
			else
			{
				listitem=lstGuest;
			}
					
			//listitem.click();  //Not working
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", listitem);
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(drpMgrvendor);
		drp.selectByVisibleText(value);
	}
	

	public void setAdminContent(String content)
	{
		txtAdmcomment.sendKeys(content);
	}
	
	public void clickOnSave()
	{
		btnSave.click();
	}
	
	public boolean verifyConfirmationMsg()
	{
		String msg=txtMsg.getText();
		if (msg.contains("The new customer has been added successfully"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
