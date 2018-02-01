package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptExecutorConcept {
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.get("https:\\www.freecrm.com");
		driver.findElement(By.name("username")).sendKeys("Nkperiwal1");
		driver.findElement(By.name("password")).sendKeys("nkp@1231");
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Login']"));
		flash(loginBtn, driver);
		drawBorder(driver, loginBtn);
		takeScreenShot(driver, System.getProperty("user.dir")+"//element.png");
		generateAlert(driver, "Issue with this login button");
		clickElementByJS(driver, loginBtn);
		refreshBrowserByJS(driver);
		System.out.println(getTitleByJS(driver));
		System.out.println(getPageInnerTxtByJS(driver));
		WebElement forgotPwdLink =driver.findElement(By.xpath("//a[contains(text(),'Forgot Password?')]"));
		scrollIntoView(driver, forgotPwdLink);
	}
	
	

	public static void flash(WebElement element, WebDriver driver){
		String bgColor = element.getCssValue("backgroundColor");
		for (int i =0; i<10; i++){
			changeColor("rgb(255,255,0",element, driver);
			changeColor(bgColor,element,driver);
		}
		
	}
	
	public static void changeColor(String color, WebElement element, WebDriver driver){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void drawBorder(WebDriver driver, WebElement element) {
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("arguments[0].style.border = '3px solid red'", element);
		
	}
	
	private static void takeScreenShot(WebDriver driver, String destFile) {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void generateAlert(WebDriver driver, String message){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("alert('"+message+"')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
		
		
	}
	
	public static void clickElementByJS(WebDriver driver, WebElement element){
		
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("arguments[0].click();", element);
	}
	
	public static void refreshBrowserByJS(WebDriver driver){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("history.go(0)");
		
	}
	
	public static String getTitleByJS(WebDriver driver){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		String title = JS.executeScript("return document.title;").toString();
		return title;
	}
	
	public static String getPageInnerTxtByJS(WebDriver driver){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		String text = JS.executeScript("return document.documentElement.innerText;").toString();
		return text;
		
	}
	
	public static void scrollDown(WebDriver driver){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("window.scrollTo(0,document.body.scrollHeight");
		
	}
	
	public static void scrollIntoView(WebDriver driver,WebElement element){
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
}
