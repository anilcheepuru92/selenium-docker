package com.newtours.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.test.BaseTest;
import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistrationConfirmationPage;
import com.newtours.pages.RegistrationPage;

public class BookFlightTest extends BaseTest{
	private String passengerCount;
	private String expectedPrice;
	
	@BeforeTest
	@Parameters({"passengerCount","expectedPrice"})
	public void setUpParameters(String passengerCount, String expectedPrice) {
		this.passengerCount = passengerCount;
		this.expectedPrice = expectedPrice;
	}
	
	@Test
	public void registrationPageTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.enterDetails("selenium", "Docker");
		registrationPage.enterUserCredentials("jenkins", "maven");
		registrationPage.clickSubmit();
	}
	
	@Test(dependsOnMethods = "registrationPageTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		registrationConfirmationPage.goToFlightDetailsPage();
	}

	@Test(dependsOnMethods="registrationConfirmationTest")
	public void flightDetailsPageTest() {
		FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
		flightDetailsPage.selectPassengers(passengerCount);
		flightDetailsPage.findFlightsPage();
	}
	
	@Test(dependsOnMethods="flightDetailsPageTest")
	public void findFlightPageTest() {
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
	}
	
	@Test(dependsOnMethods="findFlightPageTest")
	public void flightConfirmationPageTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		String actualPrice = flightConfirmationPage.getPrice();
		Assert.assertEquals(actualPrice, expectedPrice);
	}
}
