package com.qa.ulearn.tests;

import com.qa.ulearn.factory.PlaywrightFactory;
import com.qa.ulearn.listeners.ExtentReportListener;
import com.qa.ulearn.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.ulearn.base.BaseTest;
import com.qa.ulearn.constants.AppConstants;
import com.qa.ulearn.pages.CourseAndcategoryManagementPage;

public class CourseCreationAndCompletionURLTest extends BaseTest {
	
	
	/**
	 * login to urlearn stage us with username & password
	 * land on dashboard
	 * verify you are on dashboard page
	 * click on profile
	 * click browse courses ( new page "Course and category management"- page class to be created)
	 * verify page title "Course and category management"
	 * click on walmart inc category
	 * verify "WAL-MART STORES INC" label is present
	 * click on create course button
	 * this will route to "LMS : Add a new course
	 */
	private String courseName ="";
	@Test
	public void VerifyCourseCreationURL() {
		dashboardPage = homePage.navigateToDashBoardPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String actualDashBoardPageTitle = dashboardPage.getDashboardPageTitle();
		Assert.assertEquals(actualDashBoardPageTitle, AppConstants.DASHBOARD_PAGE_TITLE);
		
		// click on profile icon from dashboard page
		Assert.assertEquals(dashboardPage.isProfileIconVisible(), true);
		if(dashboardPage.isProfileIconVisible()) {
			dashboardPage.clickOnProfileIcon();
			courseAndcategoryManagementPage = dashboardPage.navigateToBrowseCourse();
			String coursePageTitle = courseAndcategoryManagementPage.getCourseAndCategoryManageMentPageTitle();
			Assert.assertEquals(coursePageTitle, AppConstants.BROWSECOURSE_PAGE_TITLE);
			String courseUrl = courseAndcategoryManagementPage.getCourseAndCategoryManageMentPageUrl();
			Assert.assertEquals(courseUrl, AppConstants.BROWSECOURSE_PAGE_URL);
			ExtentReportListener.logInfo("Expected Course URL : "+AppConstants.BROWSECOURSE_PAGE_URL +" | Actual Course url :"+courseUrl);

			String courseLabel = courseAndcategoryManagementPage.getCourseAndCategoryMgmtLabel();
			Assert.assertEquals(courseLabel, AppConstants.BROWSECOURSE_PAGE_LABEL,"Expected Course Label : "+AppConstants.BROWSECOURSE_PAGE_LABEL +" | Actual Course Label :"+courseLabel);
			ExtentReportListener.logInfo("Expected Course Label : "+AppConstants.BROWSECOURSE_PAGE_LABEL +" | Actual Course Label :"+courseLabel);

		}


	}
	@Test(dependsOnMethods ="VerifyCourseCreationURL" )
	public void verifyCourseCreationPageIsLoadedSuccessFullyAfterClickingOnCreateNewCourse(){
    /*
    click on WAL-MART STORES INC category
    verify WAL-MART STORES INC categorylabel is visible
    click on create new course
     */
		courseAndcategoryManagementPage.clickonCourseCategory(AppConstants.CATEGORY_WAL_MART_STORES_INC);
		String categoryLabel = courseAndcategoryManagementPage.getCategoryLabel(AppConstants.CATEGORY_WAL_MART_STORES_INC);
		Assert.assertEquals(categoryLabel,AppConstants.CATEGORY_WAL_MART_STORES_INC);
		ExtentReportListener.logInfo("Expected Category Label : "+AppConstants.CATEGORY_WAL_MART_STORES_INC +" | Actual Category Label :"+categoryLabel);
		createNewCoursePage= courseAndcategoryManagementPage.clickOnCreateNewCourseButton();
		String createNewCourseLabel = createNewCoursePage.getCourseLabel();
		Assert.assertEquals(createNewCourseLabel, AppConstants.CREATE_NEW_COURSE_LABEL);
		ExtentReportListener.logInfo("Expected Label : "+AppConstants.CREATE_NEW_COURSE_LABEL +" | Actual Label :"+createNewCourseLabel);


	}

	@Test(dependsOnMethods  ="verifyCourseCreationPageIsLoadedSuccessFullyAfterClickingOnCreateNewCourse")
		public void VerifyUserisAbleToFillAddDetailsAndSave(){

		// Fill General Details
		String courseName = FakerUtils.getRandomCourseName();
		createNewCoursePage.fillCourseFullName(courseName);
		ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"CourseName Filled");
		createNewCoursePage.fillCourseShortName(courseName);
		createNewCoursePage.selectCourseCategory("WAL-MART STORES INC");
		createNewCoursePage.selectCourseVisibility("Show");
		createNewCoursePage.fillCourseIDNumber(FakerUtils.getRandomNumber());
		ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"GeneralDetails Filled");

		//Fill Course Summary
		createNewCoursePage.fillCourseSummary(FakerUtils.getRandomDescriptions());
		boolean isCourseFormatCollapsedByDefault = createNewCoursePage.isCourseFormatCollapsedByDefault();
		Assert.assertEquals(isCourseFormatCollapsedByDefault,true);
		if(isCourseFormatCollapsedByDefault){
			ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"CourseFormat Collapsed");
			createNewCoursePage.clickCourseFormat();
			boolean isCourseFormatExpandedAfterClick = createNewCoursePage.isCourseFormatCollapsedByDefault();
			Assert.assertEquals(isCourseFormatExpandedAfterClick,false);
			ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"CourseFormat Expanded");

			// fill course format
			createNewCoursePage.selectTopicFormat("Single activity format");
           //verify if single activity format is selected ie., verify if type of activity is displayed
			boolean isTypeOfActivityDisplayed = createNewCoursePage.isTypeOfActivityDisplayed();
			Assert.assertEquals(isTypeOfActivityDisplayed,true,"Type of Activity not displayed");
            //select type of activity URL
			createNewCoursePage.selectTypeOfActivity("URL");
			ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"Activity URL is set");



			//Save and Display
			participantsPage =createNewCoursePage.clickSaveAndDisplayButton();
			participantsPage.getPageTitle();

//			//enroll user
//			participantsPage.clickEnrollUserButton();
//			ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"Enroll user button is clicked");
//
//			//verify enroll user pop up window is opened
//			boolean enrollUsersVisibility = participantsPage.isEnrollUserLabelVisible();
//			Assert.assertEquals(enrollUsersVisibility,true,"enroll User Label is not visible/ enroll user window not opened");
//			//verify if enrollment option is visible ?
//			boolean enrollmentOptionVisiblity = participantsPage.iselementinEnrollmentOptionsVisible();
//			Assert.assertEquals(enrollmentOptionVisiblity,true,"enrollment option not visible");


			// click on proceed with new course and land on
			addNewURLPage =participantsPage.clickOnProceedToCourseButton();
			String newPageURLTitle = addNewURLPage.getPageTitle();
			Assert.assertEquals(newPageURLTitle,AppConstants.NEW_URL_ADD_PAGE_TITLE,"page url does not match");
			String newPageURLLabel = addNewURLPage.getAddNewURLPageLabel();
			Assert.assertEquals(newPageURLLabel,AppConstants.NEW_URL_ADD_PAGE_LABEL,"page label does not match");

			//Fill Details and Hit Save
			addNewURLPage.enterName(FakerUtils.getRandomCourseName()+"url");
			addNewURLPage.enterURL("https://www."+FakerUtils.getRandomCourseName().replace(" ","")+".com");
			addNewURLPage.enterDescription(FakerUtils.getRandomDescriptions());
			boolean isAppearanceSectionExpanded =addNewURLPage.isAppearanceSectionExpanded();
			if(isAppearanceSectionExpanded==false){
				addNewURLPage.clickAppearanceSection();
				Assert.assertEquals(addNewURLPage.isAppearanceSectionExpanded(),true,"AppearancePage is not Expanded");


			}
				addNewURLPage.setSelectDisplay("Automatic");
				boolean isDisplayDescriptionChecked = addNewURLPage.isDisplayURLDescriptionChecked();
				if(isDisplayDescriptionChecked==false) {
					addNewURLPage.clickDisplayURLDescriptioncheckBox();
					Assert.assertEquals(isDisplayDescriptionChecked,true,"DisplayDescription is not checked, ie., HTML dom value is not changed from 1 to 0");

				}
			boolean isModuleRatingExapanded = addNewURLPage.isModuleRatingSectionExpanded();
				if(!isModuleRatingExapanded){
					addNewURLPage.clickModuleRatingSection();
					Assert.assertEquals(addNewURLPage.isModuleRatingSectionExpanded(),true,"ModuleRatingSection is not expanded");


				}

			courseLandingPage = addNewURLPage.clickSaveAndDisplay();
			String courseLabel = courseLandingPage.getCourseLabel();
			System.out.println("Actual label is "+courseLabel);
			System.out.println("expected label is "+AppConstants.COURSE_LANDING_LABEL);
			Assert.assertEquals(courseLabel,AppConstants.COURSE_LANDING_LABEL);
			courseLandingPage.clickCourseAdminSection();
			courseLandingPage.clickUserSection();
			enrollmentMethodPage = courseLandingPage.clickEnrolmentMethod();

			Assert.assertEquals(enrollmentMethodPage.getPageTitle(),"Enrollment methods");
			manualEnrollmentPage = enrollmentMethodPage.clickEnrollUserButton();
			manualEnrollmentPage.searchUser("diwahar");
			String matchingContent = manualEnrollmentPage.selectMatchingUserWithNameAndEmailId("Diwahar","Pandian","Diwahar.Pandian_updated@walmart.com");
ExtentReportListener.logInfo("matching content is "+matchingContent);
			ExtentReportListener.logPass("URL course "+courseName+" is enrolled successfully ");

			ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"User "+matchingContent+" is enrolled successfully");

//course completion
			courseCompletionPage = manualEnrollmentPage.clickOnCourseCompletion();
			courseCompletionPage.setSelectCompletionRequirement("Course is complete when ANY of the conditions are met");
			courseCompletionPage.selectAllCourseForCompletion();
			courseCompletionPage.clickSaveAndDisplay();
			ExtentReportListener.logPass("URL course "+courseName+" completion configuration is SET successfully");
			 this.courseName=courseName;






		}




		}

	@Test(dependsOnMethods ="VerifyUserisAbleToFillAddDetailsAndSave" )
	public void verifyStudentUserIsAbleToViewAndCompleteTheCourse(){
		homePage = courseCompletionPage.clicklogOut(prop.getProperty("url"));
		ExtentReportListener.logPass("Course to be checked "+courseName);
		String[]usernamePassword = prop.getProperty("student").split(";");
		dashboardPage = homePage.navigateToDashBoardPage(usernamePassword[0], usernamePassword[1]);
		ExtentReportListener.logScreenshot(PlaywrightFactory.takeScreenshotFile(),"STUDENT profile login credentials are provided and logged in");
		String actualDashBoardPageTitle = dashboardPage.getDashboardPageTitle();
		Assert.assertEquals(actualDashBoardPageTitle, AppConstants.DASHBOARD_PAGE_TITLE);
		//click on view all of required training
		//if see more is present, click on it until it is not visible
		// verify the course availability
		// start the course
		// verify url details are apprpriate and same what is provided
		// verify completed status before
		// after viewing the url, verify if the status is moved to completed
	}
	}

	
	


