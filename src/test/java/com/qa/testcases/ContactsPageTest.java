package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	public static HomePage oHomePage;
	public static LoginPage oLoginPage;
	public static ContactsPage oContactsPage;
	String sSheetName = "contacts";
	
	public ContactsPageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialize();
		oLoginPage = new LoginPage();
		oHomePage = oLoginPage.login(oDriverProperties.getProperty("username"), oDriverProperties.getProperty("password"));
		oCommonDriver.switchToFrame("mainpanel");
		oContactsPage = oHomePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest(){
        boolean flag = oContactsPage.verifyContactsLabel();
        Assert.assertTrue(flag, "Contacts Label is missing on the page");
	}	
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		oContactsPage.selectContactsByName("Aditya Jain");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		oContactsPage.selectContactsByName("Aditya Jain");
		oContactsPage.selectContactsByName("Amit Satyam");

	}
	
	@Test(priority=4)
	public void selectAllContactsTest(){
		oContactsPage.selectAllContactsByName();

	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		
		Object data[][] = TestUtil.getTestData(oDriverProperties.getProperty("TestDataFile"),sSheetName);
		return data;
	}
	
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
		oHomePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		oContactsPage.createNewContact(title, firstName, lastName, company);
		
	}
	
	@AfterMethod
	public void tearDown(){
		oCommonDriver.closeBrowser();
	}
	
	

}
