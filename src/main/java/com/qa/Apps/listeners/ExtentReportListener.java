package com.qa.ulearn.listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import static com.qa.ulearn.factory.PlaywrightFactory.takeScreenshot;
import static com.qa.ulearn.factory.PlaywrightFactory.takeScreenshotFile;

public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "UlearnTestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;

	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		//prop load
		Properties prop = new Properties();

		// Load custom properties from a properties file
		try (FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("Ulearn Automation Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "MAC");
		extentReports.setSystemInfo("Author", "Diwahar Pandian");
		extentReports.setSystemInfo("Environment#", prop.getProperty("env"));
		extentReports.setSystemInfo("Team", "ULEARN");
		extentReports.setSystemInfo("Browser#", prop.getProperty("browser"));
		extentReports.setSystemInfo("Headless#", prop.getProperty("headless"));
		extentReports.setSystemInfo("URL",prop.getProperty("url"));



		//extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

		return extentReports;
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
		
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),result.getMethod().getMethodName()).build());
		test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotFile(),result.getMethod().getMethodName()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotFile(),result.getMethod().getMethodName()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		//test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(), result.getMethod().getMethodName()).build());
		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshotFile(),result.getMethod().getMethodName()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	/**
     * Log a screenshot with a description in the report.
     */
    public static synchronized void logScreenshot(String screenshotPath, String description)  {
    	//test.get().addScreenCaptureFromBase64String(screenshotPath,description);
		test.get().addScreenCaptureFromPath(screenshotPath,description);


	}
    public static synchronized void logInfo(String message) {
        test.get().info(message);
    }

    /**
     * Log a pass status and message in the report.
     */
    public static synchronized void logPass(String message) {
        test.get().pass(message);
    }

    /**
     * Log a fail status and message in the report.
     */
    public static synchronized void logFail(String message) {
        test.get().fail(message);
    }



}