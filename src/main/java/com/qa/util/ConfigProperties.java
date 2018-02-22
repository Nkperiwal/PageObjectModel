package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;


public class ConfigProperties {
	

	public static String application = "";
	public static String freeCRMURL = "";
	public static String freeCRMUserName = "";
	public static String freeCRMPassword = "";
	public static String browserName = "";
	
	public static String chromeDriverLocation = "";
	public static String ieDriverLocation = "";
	public static String firefoxprofilename = "";
	public static String testDataFile = "";
	
	/**
	 * This method loads properties from configuration file
	 * 
	 * @return configProperties Class Object
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	static {

		Properties propertiesObj = new Properties();
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties";
		System.out.println("Properties file path ..." + path);

		try {
			propertiesObj.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Reporter.log("FileNotFoundException found : " + e);
		} catch (IOException e) {
			e.printStackTrace();
			Reporter.log("IOException found : " + e);
		}
		firefoxprofilename = propertiesObj.getProperty("firefoxprofilename");
		chromeDriverLocation = propertiesObj.getProperty("chromeDriverLocation");
		ieDriverLocation = propertiesObj.getProperty("ieDriverLocation");
		freeCRMURL = propertiesObj.getProperty("freeCRMURL");
		application = propertiesObj.getProperty("application");
		browserName = propertiesObj.getProperty("browserName");
		testDataFile = propertiesObj.getProperty(application + ".testDataFile");
		freeCRMUserName = propertiesObj.getProperty(application + ".freeCRMUserName");
		freeCRMPassword = propertiesObj.getProperty(application + ".freeCRMPassword");
	}
}


	

