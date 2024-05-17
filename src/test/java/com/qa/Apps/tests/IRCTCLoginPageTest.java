package com.qa.ulearn.tests;

import com.qa.ulearn.base.BaseTest;
import com.qa.ulearn.constants.AppConstants;
import net.sourceforge.tess4j.TesseractException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class IRCTCLoginPageTest extends BaseTest {
    @Test
    public void homePageTitleTest() throws TesseractException, IOException {
        String actualTitle = irctcLoginPage.getHomePageTitle();
        irctcLoginPage.clickLoginButton();
        Assert.assertEquals(actualTitle, "IRCTC Next Generation eTicketing System");
        String loginName = irctcLoginPage.loginAsUser("Diwahar93","@Diwa1993");

        Assert.assertEquals(loginName, "Welcome Diwahar Pandian (Diwahar93)");



    }
}
