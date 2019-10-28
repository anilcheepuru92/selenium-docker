package com.ducksearch.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.test.BaseTest;
import com.ducksearch.pages.SearchPage;

public class SearchPageTest extends BaseTest{
	
	
	@Test
	@Parameters({"searchString"})
	public void searchTest(String searchString) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.goTo();
		searchPage.search(searchString);
		searchPage.goToVideos();
		int resultSize = searchPage.getResults();		
		Assert.assertTrue(resultSize>0);
	}

}
