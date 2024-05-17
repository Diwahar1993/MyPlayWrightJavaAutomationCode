package com.qa.ulearn.pages;

import com.microsoft.playwright.Page;

public class DashBoardPage {

	private Page page;
	
	//1. String Locator - OR
	private String requiredTrainingLabel ="//h5[text()='Required Training']";
	private String requiredViewAll ="//span[@class='required_viewall']";
	private String requiredNextButton = "//div[@id='requiredtraining']//div[text()='next']";
	private String profileIcon ="//span[@class='avatar current']";
	private String browseCourses = "//span[@id='actionmenuaction-6']";
	
	//2. Page Constructor:
	public DashBoardPage(Page page) {
		this.page=page;
	}
	//3. page actions/methods:
	public String getDashboardPageTitle() {
		String pageTitle = page.title();
		System.out.println("Page title is "+pageTitle);
		return pageTitle;
	}
	public boolean isRequiredTrainingLabelExist() {
		return page.isVisible(requiredTrainingLabel);
	}
	public boolean isRequiredViewAllButtonVisible() {
		return page.isVisible(requiredViewAll);
	}
	public boolean isRequiredNextButtonVissible() {
		return page.isVisible(requiredNextButton);
	}
	
	public boolean isProfileIconVisible() {
		return page.isVisible(profileIcon);
	}
	public void clickOnProfileIcon() {
		page.click(profileIcon);
	}
	public CourseAndcategoryManagementPage navigateToBrowseCourse() {
		page.click(browseCourses);
		page.waitForLoadState();
		System.out.println("page title after clicking on browseCourse is : "+page.title());
		return new CourseAndcategoryManagementPage(page);
		
	}
}
