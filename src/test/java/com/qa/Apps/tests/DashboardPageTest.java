package com.qa.ulearn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ulearn.base.BaseTest;
import com.qa.ulearn.constants.AppConstants;

public class DashboardPageTest extends BaseTest {
	
	
	@Test
	public void dashBoardPageNavigationTest() {
		dashboardPage = homePage.navigateToDashBoardPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String actualDashBoardPageTitle = dashboardPage.getDashboardPageTitle();
		Assert.assertEquals(actualDashBoardPageTitle, AppConstants.DASHBOARD_PAGE_TITLE);
		boolean isRequiredTrainingLabelVisible = dashboardPage.isRequiredTrainingLabelExist();
		Assert.assertEquals(isRequiredTrainingLabelVisible, true);
		boolean isRequiredViewAllVisible =dashboardPage.isRequiredViewAllButtonVisible();
		Assert.assertEquals(isRequiredViewAllVisible, true);
		boolean isRequiredNextButton =dashboardPage.isRequiredNextButtonVissible();
		Assert.assertEquals(isRequiredNextButton, true);
	}
	
	
	

}
