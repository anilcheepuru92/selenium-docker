package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//input[@name='firstName']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@name='lastName']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@name='confirmPassword']")
	private WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='register']")
	private WebElement submitBtn;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo() {
		driver.get("http://newtours.demoaut.com/mercuryregister.php");
		wait.until(ExpectedConditions.visibilityOf(firstName));
		PageFactory.initElements(driver, this);
	}
	
	public void enterDetails(String fiName, String laName) {
		firstName.sendKeys(fiName);
		lastName.sendKeys(laName);
	}
	
	public void enterUserCredentials(String user, String pass) {
		userName.sendKeys(user);
		password.sendKeys(pass);
		confirmPassword.sendKeys(pass);
	}
	
	public void clickSubmit() {
		submitBtn.click();
	}

}
