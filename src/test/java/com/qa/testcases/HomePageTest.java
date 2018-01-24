package com.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	
	public static HomePage oHomePage;
	public static LoginPage oLoginPage;
	
	public HomePageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialize();
		oLoginPage = new LoginPage();
		oHomePage = oLoginPage.login(oDriverProperties.getProperty("username"), oDriverProperties.getProperty("password"));
		oCommonDriver.switchToFrame("mainpanel");

	}
	
	@Test(priority=1, enabled=false)
	public void homePageTitleTest(){
		String sActualLoginPageTitle = oHomePage.validateHomePageTitle();
		String expectedLoginPageTitle = "CRMPRO";
		oCommonDriver.verifyTitle(sActualLoginPageTitle, expectedLoginPageTitle);
	}	
	
	@Test(priority=2)
	public void verifyCorrectUserNameTest(){
		oHomePage.verifyCorrectUserName();
	}
	
	@Test(priority=3)
	public void clickOnContactsLinkTest(){
		oHomePage.clickOnContactsLink();
	}
	
	@Test(priority=4)
	public void clickOnDealsLinkTest(){
		oHomePage.clickOnContactsLink();
	}
	
	@Test(priority=5)
	public void clickOnTasksLinkTest(){
		oHomePage.clickOnTasksLink();
	}
	
	@AfterMethod
	public void tearDown(){
		oCommonDriver.closeBrowser();
	}
	
	

}
