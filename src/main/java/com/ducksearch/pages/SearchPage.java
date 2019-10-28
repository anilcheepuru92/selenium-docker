package com.ducksearch.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id="search_form_input_homepage")
	private WebElement searchText;
	
	@FindBy(xpath="//input[@id='search_button_homepage']")
	private WebElement searchBtn;
	
	@FindBy(xpath="//a[contains(text(),'Videos')]")
	private WebElement videosLink;
	
	@FindBy(xpath="//div[contains(@class,'tile--vid')]")
	private List<WebElement> allVideos;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo() {
		driver.get("https://duckduckgo.com");
	}
	
	public void search(String searchString) {
		wait.until(ExpectedConditions.visibilityOf(searchText));
		searchText.sendKeys(searchString);
		searchBtn.click();	
	}
	
	public void goToVideos() {
		wait.until(ExpectedConditions.visibilityOf(videosLink));
		videosLink.click();
	}
	
	public int getResults() {
		By by = By.xpath("//div[contains(@class,'tile--vid')]");
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
		System.out.println(allVideos.size());
		return allVideos.size();
	}

}
