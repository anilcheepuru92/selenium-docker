package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindFlightPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(name="reserveFlights")
	private WebElement continueBtn;
	
	@FindBy(name="buyFlights")
	private WebElement purchaseBtn;
	
	public FindFlightPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public void submitFindFlightPage() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
	}
	
	public void goToFlightConfirmationPage() {
		wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn));
		purchaseBtn.click();
	}
}
