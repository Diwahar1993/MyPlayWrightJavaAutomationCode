package com.qa.ulearn.pages;

import java.io.IOException;

import com.microsoft.playwright.Page;
import com.qa.ulearn.factory.PlaywrightFactory;
import com.qa.ulearn.listeners.ExtentReportListener;

public class CourseAndcategoryManagementPage {
	
	private Page page;
	
	//1. String Locator - OR
	private String courseAndCategoryMgmtLabel ="//h2[normalize-space()='Course and category management']";
	private String courseCategory ="//a[contains(text(),'{categoryName}')]";
	private String categouryLabel ="(//h3[normalize-space()='{categoryName}'])[1]";
	private String createNewCourseButton = "//a[normalize-space()='Create new course']";
	
	//2. Page Constructor:
		public CourseAndcategoryManagementPage(Page page) {
			this.page=page;
		}
		
	//3. page actions/methods:
		public String getCourseAndCategoryManageMentPageTitle() {
            String pageTitle = page.title();
			System.out.println("Page title is "+pageTitle);
			return pageTitle;
		}
		
		public String getCourseAndCategoryManageMentPageUrl() {
			String pageUrl = page.url();
			System.out.println("Page Url is "+pageUrl);
			return pageUrl;
		}

		public String getCourseAndCategoryMgmtLabel(){
			page.waitForLoadState();
			System.out.println("Course Category Label is "+page.textContent(courseAndCategoryMgmtLabel));
			return page.textContent(courseAndCategoryMgmtLabel);
		}

		public void clickonCourseCategory(String categoryName){
			page.click(courseCategory.replace("{categoryName}",categoryName));
		}

	public String getCategoryLabel(String categoryLabel) {
			page.click(categouryLabel.replace("{categoryName}",categoryLabel));
			return page.textContent(categouryLabel.replace("{categoryName}",categoryLabel));
	}

	public CreateNewCoursePage clickOnCreateNewCourseButton() {
		page.click(createNewCourseButton);
		page.waitForLoadState();
		System.out.println("page title after clicking on CreateNewCoursePage is : "+page.title());
		return new CreateNewCoursePage(page);

	}
}
