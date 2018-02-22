package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	public static HomePage oHomePage;
	public static LoginPage oLoginPage;
	
	public LoginPageTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialize();
		oLoginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String sActualLoginPageTitle = oLoginPage.validateLoginPageTitle();
		String expectedLoginPageTitle = "#1 Free CRM for Any Business: Online Customer Relationship Software";
		oBaseUtil.verifyTitle(sActualLoginPageTitle, expectedLoginPageTitle);
	}
	
	@Test(priority=2)
	public void crmLogoImageTest(){
		boolean flag = oLoginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest(){
		oHomePage = oLoginPage.login(freeCRMUserName, freeCRMPassword);
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		oBaseUtil.closeBrowser();
	}
	
	

}
