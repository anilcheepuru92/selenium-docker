package com.newtours.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightConfirmationPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//font[contains(text(),'Confirmation')]")
	private WebElement confirmationText;
	
	@FindBy(xpath="//font[contains(text(),'USD')]")
	private List<WebElement> prices;
	
	@FindBy(linkText="SIGN-OFF")
	private WebElement signOffLink;
	
	public FlightConfirmationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public String getPrice() {
		wait.until(ExpectedConditions.visibilityOf(confirmationText));
		System.out.println(confirmationText.getText());
		System.out.println(prices.get(0).getText());
		String price = prices.get(0).getText();
		signOffLink.click();
		return price;
	}
	
}
