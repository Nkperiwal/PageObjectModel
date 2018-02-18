package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {
	// Page Factory - OR:
	
	@FindBy(id = "userNameInput")
	WebElement zscalerUserName;
	
	@FindBy(id = "passwordInput")
	WebElement zscalerPassword;
	
	@FindBy(id = "submitButton")
	WebElement zscalerSignInBtn;

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;

	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

	public LoginPage() {
		PageFactory.initElements(oCommonDriver.getDriver(), this);

	}

	public void proxySetting()
	{
		zscalerUserName.sendKeys("M1032438@mindtree.com");
		zscalerPassword.sendKeys("2018@Jan");
		zscalerSignInBtn.click();
	}
	public String validateLoginPageTitle() {
		return oCommonDriver.getTitle();
	}

	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}

	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
