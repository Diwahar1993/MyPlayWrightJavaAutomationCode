package com.qa.ulearn.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.ulearn.base.BaseTest;
import com.qa.ulearn.constants.AppConstants;

public class HomePageTest extends BaseTest {
	

	
	
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		
		
	}
	@Test
	public void homePageUrlTest() {
		String actualUrl = homePage.getHomePageUrl();
		Assert.assertEquals(actualUrl,prop.getProperty("url").trim());
		
		
	}
	
	@Test
	public void loginTest() {
		homePage.navigateToDashBoardPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	
	
}
