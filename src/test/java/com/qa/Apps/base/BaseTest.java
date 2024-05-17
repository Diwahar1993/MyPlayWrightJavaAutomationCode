package com.qa.ulearn.base;

import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.BrowserContext;
import com.qa.ulearn.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.qa.ulearn.factory.PlaywrightFactory;

public class BaseTest {

	PlaywrightFactory pf;
	Page page;
	protected Properties prop;
	protected HomePage homePage;
	protected DashBoardPage dashboardPage;
	protected CourseAndcategoryManagementPage courseAndcategoryManagementPage;
	protected CreateNewCoursePage createNewCoursePage;
	protected ParticipantsPage participantsPage;
	protected AddNewURLPage addNewURLPage;
	protected CourseLandingPage courseLandingPage;

	protected EnrollmentMethodPage enrollmentMethodPage;

	protected ManualEnrollmentPage manualEnrollmentPage;
	protected CourseCompletionPage courseCompletionPage;

	// IRCTC
	protected IRCTCLoginPage irctcLoginPage;

	@BeforeSuite
	public void beforeSuite(){
//Delete all report and Screenshots
		String screenShotPath = System.getProperty("user.dir")+"/screenshot";
		String screenRecordingPath = System.getProperty("user.dir")+"/myvideos";
		System.out.println("Screenshot folder path is "+screenShotPath);
		System.out.println("ScreenRecording folder path is "+screenRecordingPath);

		// Create a File object for the specified folder
		File folderScreenshot = new File(screenShotPath);
		File folderScreenRecording = new File(screenRecordingPath);

		// to include any actions that needs to be performed before suite
		deleteFolderContents(folderScreenshot);
		deleteFolderContents(folderScreenRecording);

	}

	@BeforeTest
	public void setup() {
		pf= new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		irctcLoginPage = new IRCTCLoginPage(page);
		// Start tracing before creating / navigating a page.
				page.context().tracing().start(new Tracing.StartOptions()
						   .setScreenshots(true)
						   .setSnapshots(true)
						   .setSources(true));

// start recording



	}
	@AfterTest
	public void tearDown() {
		// Stop tracing and export it into a zip archive.
				page.context().tracing().stop(new Tracing.StopOptions()
				  .setPath(Paths.get("trace"+System.currentTimeMillis()+".zip")));
				page.context().browser().close();




	}

	public static void deleteFolderContents(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					// If it's a subdirectory, recursively delete its contents
					deleteFolderContents(file);
				} else {
					// If it's a file, delete it
					if (file.delete()) {
						System.out.println("Deleted file: " + file.getAbsolutePath());
					} else {
						System.err.println("Failed to delete file: " + file.getAbsolutePath());
					}
				}
			}
		}
	}


}
