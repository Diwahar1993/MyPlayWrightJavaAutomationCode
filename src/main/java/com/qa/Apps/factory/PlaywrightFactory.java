package com.qa.ulearn.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestContext;


public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
	private static  ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private  static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static  ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();



	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	public static Page getPage() {
		return tlPage.get();
	}
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}



	public Page initBrowser(Properties prop) {
	String browserName  = prop.getProperty("browser").trim();
		System.out.println("Browser name is :"+browserName);

		//playwright=Playwright.create();
		tlPlaywright.set(Playwright.create());

		switch(browserName.toLowerCase()) {
		case "chromium":
			//browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			//tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(
					Boolean.parseBoolean(prop.getProperty("headless").trim()
					)))
			);

			break;
		case "firefox":
			//browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			//tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(
					Boolean.parseBoolean(prop.getProperty("headless").trim())
							)
					)
			);


		break;
		case "safari":
			//browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			//tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(
					Boolean.parseBoolean(prop.getProperty("headless").trim())
			)));
		break;
		case "chrome":
			//browser=playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			//tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(
					Boolean.parseBoolean(prop.getProperty("headless").trim())
			)));
		break;

		default:
			System.out.println("Please pass the right browser name......");
			break;
		}
		//browserContext= browser.newContext();
		/*tlBrowserContext.set(getBrowser().newContext());*/

		// to maximize the window
		//tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1920,1080)));
		//tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1920,2157)));
		//tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(3868,2548)));
		//tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1920,2548)));
		tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions()
				.setViewportSize(1920, 2548)
				.setRecordVideoDir(Paths.get("myvideos/"))
				.setRecordVideoSize(1920, 1080)));




		//page =browserContext.newPage();
		tlPage.set(getBrowserContext().newPage());

		//page.navigate(prop.getProperty("url").trim());

		getPage().navigate(prop.getProperty("url").trim());



		return getPage();

	}



	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 *
	 */

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}

	public static String takeScreenshotFile() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//String path =  "/screenshot/" + System.currentTimeMillis() + ".png";
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path.replace(System.getProperty("user.dir"),"");
	}
//
//	public static String captureScreenShot(Page page) {
//        String imageName = "IMG_" + System.currentTimeMillis() + ".png";
//        String baseDir = System.getProperty("user.dir");
//        String filePath = baseDir + "/Report/";
//        File targetFile = new File(filePath, imageName);
//
//        try {
//            // Capture screenshot using Playwright
//            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
//
//            // Write the screenshot bytes to the target file
//            FileUtils.writeByteArrayToFile(targetFile, screenshotBytes);
//            return targetFile.getName();
//        } catch (IOException e) {
//            System.err.println("Failed to capture screenshot: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }

}
