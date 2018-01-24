package com.qa.base;

/*
 * 
 * @author = Nandkishor Periwal
 * 
 */


import java.util.Properties;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.qa.util.CommonDriver;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

public class TestBase {
	private static String sPropertiesFile = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\qa\\config\\config.properties";
	public static Properties oDriverProperties;
	public static CommonDriver oCommonDriver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener oWebEventListener;

	public TestBase() {
		oCommonDriver = new CommonDriver();
		oDriverProperties = TestUtil.getProperties(sPropertiesFile);
	}

	public static void initialize() {
		String sBrowserType = oDriverProperties.getProperty("browser");
		String sUrl = oDriverProperties.getProperty("url");
		oCommonDriver.openBrowser(sBrowserType, sUrl);
		CommonDriver.oDriver = oCommonDriver.getDriver();
		e_driver = new EventFiringWebDriver(oCommonDriver.getDriver());
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		oWebEventListener = new WebEventListener();
		e_driver.register(oWebEventListener);
		CommonDriver.oDriver = e_driver;
		
	}

}
