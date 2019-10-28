package com.base.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected WebDriver driver;
	
	@BeforeTest
	public void setUpDriver(ITestContext context) throws MalformedURLException {
		String host="localhost";
		DesiredCapabilities cap;
		
		if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
			cap = DesiredCapabilities.firefox();
		}
		else
			cap = DesiredCapabilities.chrome();
		
		if(System.getProperty("HUB_HOST")!=null) {
			host = System.getProperty("HUB_HOST");
		}
		
		String testName = context.getCurrentXmlTest().getName();
		
		String completeUrl = "http://"+host+":4444/wd/hub";
		cap.setCapability("name", testName);
		this.driver = new RemoteWebDriver(new URL(completeUrl),cap);
		this.driver.manage().deleteAllCookies();
		this.driver.manage().window().maximize();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
