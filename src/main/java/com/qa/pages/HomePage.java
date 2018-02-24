package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.contants.Enums_provider;

public class HomePage extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//td[contains(text(),'User: Nandkishor')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//*[@id='crmcalendar']/table/tbody/tr[1]/td/select[@name='slctMonth']")
	WebElement monthPicker;
	
	@FindBy(xpath="//*[@id='crmcalendar']/table/tbody/tr[1]/td/select[@name='slctYear']")
	WebElement yearPicker;
	
	@FindBy(xpath="//table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/table/tbody/tr/td/strong")
	WebElement dateSelected; 


	public HomePage() {
		PageFactory.initElements(oBaseUtil.getDriver(), this);

	}

	public String validateHomePageTitle() {
		return oBaseUtil.getTitle();
	}
	

	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}

	public void clickOnNewContactLink() {
		oBaseUtil.mouseHover(contactsLink);
		oBaseUtil.clickElementUsingJSript(newContactLink);
		
		
	}
	/**
	 * 
	 * @param sdateValue
	 */
	
	public void selectSpecificDate(String sdateValue) {
		
		
		String date_dd_MM_yyyy[] = sdateValue.split("/");
		String date = date_dd_MM_yyyy[0];
		String month = date_dd_MM_yyyy[1];
		String year = date_dd_MM_yyyy[2];
		int flag=0;
		oBaseUtil.selectItemByValueInListBox(monthPicker, String.valueOf(Integer.parseInt(month)));
		oBaseUtil.selectItemByValueInListBox(yearPicker, year);
		
		List<WebElement> rowList1 = oBaseUtil.getDriver().findElements(By.xpath("//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr"));
		List<WebElement> rowList2 = oBaseUtil.getDriver().findElements(By.xpath("//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr/td"));
		for(WebElement row:rowList1){
			for(WebElement date1:rowList2){
				if(date1.getText().equalsIgnoreCase(date)){
					date1.click();
					flag=1;
					break;
				}
			}
			if(flag==1)
				break;
		}
		
		
		oBaseUtil.verifyTitle(oBaseUtil.getTitle(), "CRMPRO");
		System.out.println(dateSelected.getText());
		
		if(dateSelected.getText().equals(Enums_provider.Jan)){
			
		}
		
	}

}
