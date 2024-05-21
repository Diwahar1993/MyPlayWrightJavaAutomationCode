package com.qa.Apps.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class IRCTCLoginPage {

    private String captchaPath = "D:\\MyPlayWrightJava\\screenshot\\capcha.png";

    private Page page;

    //1. String Locators - OR (Object Repository)
    private String loginButton = "//a[normalize-space()='LOGIN']";
    private String userName = "//input[@formcontrolname ='userid']";
    private String password = "//input[@formcontrolname ='password']";

    private String fillCapcha = "//input[@formcontrolname ='captcha']";

    private String captcha = "//img[@class ='captcha-img']";

    private String signinButton = "//button[normalize-space()='SIGN IN']";

    private String loginTitle = "//span[normalize-space()='Welcome Diwahar Pandian (Diwahar93)']";
    private String origin ="//p-autocomplete[@id='origin']//input[contains(@class,'ng-tns-c57')]";
    private String destination ="//p-autocomplete[@id='destination']//input[contains(@class,'ng-tns-c57')]";
    private String typeOfTicket ="//p-dropdown[@id='journeyQuota']";


    public IRCTCLoginPage(Page page) {
        this.page = page;
    }


    //2. Page Constructor:

    //3. page actions/methods:
    public String getHomePageTitle() {
        String title = page.title();
        System.out.println("page title is " + title);
        return title;
    }

    public void clickLoginButton() {
        page.click(loginButton);

    }

    public String loginAsUser(String usr, String pwd) throws IOException, TesseractException {
        page.fill(userName, usr);
        page.fill(password, pwd);
        page.locator(captcha).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(captchaPath)));

        // Now extract text from captcha
        File screenshotFile = new File(captchaPath);

        BufferedImage screenshot = ImageIO.read(screenshotFile);
        System.setProperty("TESSDATA_PREFIX", "D:\\MyPlayWrightJava\\TesseractTrainedData\\");

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\MyPlayWrightJava\\TesseractTrainedData\\");
        String captchaText = tesseract.doOCR(screenshot);

        System.out.println(captchaText);
        page.fill(fillCapcha, captchaText);
        page.click(signinButton);

        page.waitForLoadState();
        System.out.println(page.url());
        return page.textContent(loginTitle);


    }

}
